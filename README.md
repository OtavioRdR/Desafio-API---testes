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
   git clone https://github.com/SEU_USUARIO/desafio-api-cep.git
   cd desafio-api-cep
