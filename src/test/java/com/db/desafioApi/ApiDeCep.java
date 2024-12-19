package com.db.desafioApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ApiDeCep {

    @Test
    public void cepEValido(){
        String valido = "18085847";

        Response response = RestAssured
                .given()
                .get("https://viacep.com.br/ws/" + valido + "/json");

        assertThat(response.getStatusCode(), is(200));

        String cepRetorna = response.jsonPath().getString("cep").replace("-", "");
        assertThat(cepRetorna, is(valido));

        assertThat(response.jsonPath().getString("localidade"), is(notNullValue()));
        assertThat(response.jsonPath().getString("uf"), is(notNullValue()));

        }

    @Test
    public void testCepInvalido(){
        String cepInvalido = "00000000";

        Response response = RestAssured
                .given()
                .get("https://viacep.com.br/ws/" + cepInvalido + "/json");

        assertThat(response.getStatusCode(), is(200));

        assertThat(response.jsonPath().getString("erro"), is("true"));

    }
    @Test
    public void testRespostaVazia(){
        String cepValido = "18085847";

        Response response = RestAssured
                .given()
                .get("https://viacep.com.br/ws/" + cepValido + "/json");

        assertThat(response.getStatusCode(), is(200));

        assertThat(response.asString().isEmpty(), is(false));

    }

    @Test
    public void testCepFormatoIncorreto(){
        String cepIncorreto = "18A85#47";

        Response response = RestAssured
                .given()
                .get("https://viacep.com.br/ws/" + cepIncorreto + "/json");

        assertThat(response.getStatusCode(), is(400));

        String contentType = response.getContentType();
        if (contentType.contains("application/json")) {
            String erro = response.jsonPath().getString("erro");
            assertThat(erro, is("true"));
        } else {
            System.out.println("Resposta não é JSON: " + response.asString());
            assertThat(response.asString(), containsString("Http 400"));
        }
    }
    @Test
    public void testVariosCepsValidos(){
        String[] cepsvalidos = {"01001000", "02020000", "03030000"};

        for (String cep : cepsvalidos){
            Response response = RestAssured
                    .given()
                    .get("https://viacep.com.br/ws/" + cep + "/json");

            assertThat(response.getStatusCode(), is(200));

            String cepRetorno = response.jsonPath().getString("cep").replace("-","");
            assertThat(cepRetorno, is(cep));

            assertThat(response.jsonPath().getString("localidade"), is(notNullValue()));
            assertThat(response.jsonPath().getString("uf"), is(notNullValue()));

        }
    }
    @Test
    public void testCepComDiversosFormatos(){
        String[] cepsFormatosDiversos = {"18085-847", "18085 847", "18085.847" , "180085 - 847"};

        for (String cep : cepsFormatosDiversos) {
            Response response = RestAssured
                    .given()
                    .get("https://viacep.com.br/ws/" + cep + "/json");

            int statusCode = response.getStatusCode();
            assertThat(statusCode, is(oneOf(200, 400)));

            String contentType = response.getContentType();
            if (contentType.contains("application/json")) {
                String cepRetorna = response.jsonPath().getString("cep").replace("-", "");
                assertThat(cepRetorna, is("18085847"));
            } else {
                System.out.println("Resposta não é JSON: " + response.asString());
                assertThat(response.asString(), containsString("Http 400"));
            }
        }
    }

    @Test
    public  void testTempoDeResposta(){
        String cepValido = "18085847";

        Response response = RestAssured
                .given()
                .get("https://viacep.com.br/ws/" + cepValido + "/json");

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getTime(), lessThan(2000L));

    }
}

