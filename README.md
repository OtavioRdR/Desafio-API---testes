# Desafio API de Validação de CEP 🌍📬

## Descrição do Projeto 📝

Este projeto tem como objetivo automatizar a validação de CEPs utilizando a API pública do ViaCEP. A automação de testes foi realizada utilizando **RestAssured** para fazer requisições HTTP e **JUnit 5** para garantir a qualidade do código por meio de testes unitários.

Este desafio testa vários cenários de uso, como a consulta de CEPs válidos, inválidos e com formatos diversos, além de verificar o tempo de resposta da API.

## Tecnologias Utilizadas 🚀

- **Java** 🟨
- **RestAssured** (Biblioteca para testes de APIs) ⚙️
- **JUnit 5** (Framework de testes) ✅

## Cenários de Teste 🧪

Os seguintes cenários são cobertos pelos testes da API:

1. **Cenário 1: CEP Válido** 📦
   - Valida se o retorno da API é correto para um CEP válido.
2. **Cenário 2: CEP Inválido** ❌
   - Verifica se a API retorna um erro quando um CEP inválido é fornecido.
3. **Cenário 3: Resposta Vazia** ❓
   - Confirma se a resposta da API não está vazia para um CEP válido.
4. **Cenário 4: Formato de CEP Incorreto** 🔴
   - Testa a resposta da API quando o formato do CEP não é válido.
5. **Cenário 5: Vários CEPs Válidos** 🌍
   - Valida a resposta para múltiplos CEPs válidos.
6. **Cenário 6: Diversos Formatos de CEP** 🔄
   - Testa diferentes formatos de um CEP (com ou sem hífen, ponto, etc.).
7. **Cenário 7: Tempo de Resposta** ⏱️
   - Verifica se o tempo de resposta da API para um CEP válido é inferior a 2 segundos.

## Como Executar o Projeto 🏃‍♂️

### Pré-requisitos 🔧

1. **Java 17+** instalado.
2. **Maven** ou **Gradle** para gerenciamento de dependências (recomendado Maven).
3. Acesso à **Internet** para realizar as requisições à API do ViaCEP.

### Passos para Execução 🚀

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/OtavioRdR/Desafio-API---testes
   cd desafio
Instale as dependências:

Se você estiver usando Maven, execute o seguinte comando:

bash
mvn clean install
Execute os testes:

Execute o comando abaixo para rodar os testes utilizando o JUnit 5:

bash
mvn test
Se estiver usando uma IDE como IntelliJ ou Eclipse, você pode executar os testes diretamente pela interface da IDE.

Estrutura do Projeto 🗂️
plaintext
├── src
│   └── test
│       └── java
│           └── com
│               └── db
│                   └── desafioApi
│                       └── ApiDeCep.java  <-- Arquivo de testes
└── pom.xml  <-- Arquivo de configuração do Maven
Explicação do Código 💻
Teste de CEP Válido
No teste cepEValido, o código verifica se a resposta da API para um CEP válido (exemplo: 18085847) retorna com o código de status 200 OK, o CEP correto e os campos de localidade e UF não nulos.

java
@Test
public void cepEValido() {
    String valido = "18085847";
    Response response = RestAssured
            .given()
            .get("https://viacep.com.br/ws/" + valido + "/json");

    assertThat(response.getStatusCode(), is(200));  // Verifica o status code 200
    String cepRetorna = response.jsonPath().getString("cep").replace("-", "");
    assertThat(cepRetorna, is(valido));  // Verifica se o CEP retornado é igual ao solicitado
    assertThat(response.jsonPath().getString("localidade"), is(notNullValue()));  // Verifica se a localidade está presente
    assertThat(response.jsonPath().getString("uf"), is(notNullValue()));  // Verifica se o UF está presente
}
Teste de CEP Inválido
No teste testCepInvalido, a API é chamada com um CEP inválido (00000000) e é verificado se a resposta retorna o campo "erro": "true".

java
@Test
public void testCepInvalido() {
    String cepInvalido = "00000000";
    Response response = RestAssured
            .given()
            .get("https://viacep.com.br/ws/" + cepInvalido + "/json");

    assertThat(response.getStatusCode(), is(200));  // Verifica o status code 200
    assertThat(response.jsonPath().getString("erro"), is("true"));  // Verifica se o erro é "true"
}
Teste de Tempo de Resposta
No teste testTempoDeResposta, o tempo de resposta da API é verificado, garantindo que ele seja inferior a 2 segundos.

java
@Test
public void testTempoDeResposta() {
    String valido = "18085847";
    Response response = RestAssured
            .given()
            .get("https://viacep.com.br/ws/" + valido + "/json");

    assertThat(response.getStatusCode(), is(200));  // Verifica o status code 200
    long tempoResposta = response.getTime();
    assertTrue(tempoResposta < 2000);  // Verifica se o tempo de resposta é inferior a 2 segundos
}
Contribuições 🤝
Este é um projeto open-source! Contribuições são bem-vindas. Para contribuir, siga os seguintes passos:

Fork o repositório.

Crie uma branch para suas alterações (git checkout -b feature-nova).

Faça commit das suas mudanças (git commit -am 'Adicionar nova feature').

Envie suas alterações para o repositório remoto (git push origin feature-nova).

Abra um Pull Request para revisão.

Licença 📜
Distribuído sob a Licença MIT. Veja o arquivo LICENSE para mais informações.
