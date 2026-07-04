# Produto API

API REST para gerenciamento de produtos, construída com Spring Boot como projeto de estudo e portfólio. Cobre um CRUD completo com validações, tratamento de erros padronizado, busca dinâmica por filtros combináveis, documentação interativa, testes automatizados, migrations de banco e integração contínua.

![CI](https://github.com/MauroFJ/produto-api/actions/workflows/ci.yaml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.6-6DB33F?logo=springboot&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-12-CC0200?logo=flyway&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?logo=swagger&logoColor=black)
![License](https://img.shields.io/badge/License-MIT-yellow)

## Funcionalidades

- CRUD de produtos (criar, listar, buscar por id, atualizar, remover)
- Validação dos dados de entrada (Bean Validation) com respostas de erro padronizadas
- Busca dinâmica: filtros combináveis por nome, status e faixas de valor e de quantidade (JPA Specifications)
- Endpoints de agregação: valor total em estoque e contagem de produtos
- Tratamento global de exceções, com respostas HTTP consistentes (400, 404, ...)
- Documentação interativa via Swagger UI (OpenAPI)

## Stack

- **Java 17** e **Spring Boot 4.0.6**
- **Spring Web (MVC)** — camada REST
- **Spring Data JPA / Hibernate** — persistência
- **SQLite** — banco de dados (desenvolvimento)
- **Flyway** — versionamento e migração do schema
- **springdoc-openapi (Swagger UI)** — documentação da API
- **JUnit 5, Mockito, AssertJ** — testes automatizados
- **GitHub Actions** — integração contínua (build + testes)
- **Maven** — build e gerenciamento de dependências

## Como rodar

Pré-requisito: **Java 17+**. O projeto inclui o Maven Wrapper, então não é necessário instalar o Maven.

```bash
# clonar o repositório
git clone https://github.com/MauroFJ/produto-api.git
cd produto-api

# rodar (perfil dev por padrão)
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`. Na primeira execução, o Flyway cria o banco e as tabelas automaticamente.

### Perfis (ambientes)

O projeto usa perfis do Spring para separar a configuração por ambiente:

| Perfil          | Uso                    | Banco                     | Observações                          |
| --------------- | ---------------------- | ------------------------- | ------------------------------------ |
| `dev` (padrão)  | desenvolvimento local  | SQLite                    | logs de SQL ativos                   |
| `prod`          | produção               | via variáveis de ambiente | schema apenas validado; logs enxutos |
| `test`          | testes automatizados   | H2 (em memória)           | schema recriado a cada execução      |

## Documentação da API (Swagger)

Com a aplicação no ar:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI (JSON):** http://localhost:8080/v3/api-docs

### Principais endpoints

| Método   | Rota                             | Descrição                          |
| -------- | -------------------------------- | ---------------------------------- |
| `POST`   | `/produtos`                      | cria um produto                    |
| `GET`    | `/produtos`                      | lista/busca produtos (com filtros) |
| `GET`    | `/produtos/{id}`                 | busca um produto por id            |
| `PUT`    | `/produtos/{id}`                 | atualiza um produto                |
| `DELETE` | `/produtos/{id}`                 | remove um produto                  |
| `GET`    | `/produtos/valorTotal`           | soma o valor de todos os produtos  |
| `GET`    | `/produtos/quantidadeDeProdutos` | conta os produtos cadastrados      |

Filtros aceitos em `GET /produtos` (todos opcionais e combináveis): `name`, `startingWith`, `endingWith`, `containing`, `status`, `value`, `valueGreaterThan`, `valueLessThan`, `quantity`, `quantityGreaterThan`, `quantityLessThan`.

Exemplo:

```
GET /produtos?status=DISPONIVEL&valueGreaterThan=2000
```

## Testes

```bash
./mvnw test
```

A suíte cobre três níveis:

- **Unitário** (`@Mock` / `@InjectMocks`) — regras de negócio do service, isoladas
- **Slice de dados** (`@DataJpaTest` + H2) — as Specifications de busca
- **Slice web** (`@WebMvcTest` + MockMvc) — os endpoints do controller

## Banco de dados e migrations

O schema é gerenciado pelo **Flyway**, não pelo Hibernate. Os scripts ficam em `src/main/resources/db/migration/` (ex.: `V1__create_produto.sql`) e são aplicados automaticamente na inicialização. Cada migration roda uma única vez e é registrada na tabela de controle `flyway_schema_history`.

## CI/CD

O repositório usa **GitHub Actions** (`.github/workflows/ci.yaml`): a cada push ou pull request para a `main`, o workflow compila o projeto e roda os testes. A branch `main` é protegida — só aceita merge via pull request com o CI verde.

## Melhorias futuras

- Integração com um frontend
- Paginação e ordenação na listagem
- DTOs de request/response
- Deploy em nuvem (habilitando o perfil `prod` e o CD)

---

Projeto de estudo por [Mauro FJ](https://github.com/MauroFJ).
