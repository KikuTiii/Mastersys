# Mastersys

Mastersys é um projeto de estudo de backend em Java. A API simula a gestão de uma academia para praticar o caminho completo de uma requisição HTTP, da validação do JSON até a persistência e a consulta no PostgreSQL.

## O que o projeto exercita

- **API REST com Spring MVC.** Rotas de cadastro e consulta, CRUD de alunos, filtros na URL, validação de requisições e respostas como `201 Created`, `204 No Content`, `400 Bad Request` e `404 Not Found`.
- **Arquitetura em camadas e injeção de dependências.** Controllers recebem requisições, services concentram as regras de negócio e repositories acessam o banco. A injeção pelo construtor explicita as dependências.
- **DTOs com Java records.** Os objetos de entrada e saída definem o contrato da API. Métodos como `toEntity`, `preencher` e `fromEntity` fazem a conversão entre DTOs e entidades.
- **Jakarta Bean Validation.** Uso de `@Valid`, campos obrigatórios, limites de tamanho, e-mail, valores monetários e mensagens de validação em português.
- **Modelagem relacional e JPA/Hibernate.** Entidades com relacionamentos, chaves primárias e estrangeiras, restrições de unicidade e enums de status persistidos como texto. O Hibernate valida o esquema no startup.
- **Spring Data JPA.** Métodos derivados como `findByAluno_Id`, composição de filtros opcionais com Specifications, paginação e ordenação com `Pageable` e transformação de páginas em DTOs.
- **Regras de negócio e transações.** Verificação de duplicidade no catálogo, validação de que plano e graduação pertencem à modalidade escolhida e gravação da matrícula e do vínculo na mesma transação.
- **Tratamento centralizado de erros.** O `@RestControllerAdvice` converte erros de validação, de negócio e de recurso inexistente em respostas HTTP.
- **SQL e relatórios.** Consultas nativas com `JOIN`, agrupamentos, contagem e soma, além de projeções por interface para retornar os campos de cada relatório.
- **Migrations e dados de exemplo.** O Flyway versiona o esquema. Os scripts usam `INSERT ... SELECT`, CTEs, `RETURNING` e datas relativas para montar vínculos e faturas de demonstração.
- **Verificação da aplicação.** Há um teste de inicialização do contexto com `@SpringBootTest` e arquivos `.http` para executar requisições pelo IntelliJ.

## Tecnologias e dependências

| Tecnologia | Uso no projeto |
| --- | --- |
| Java 17 e Maven Wrapper | Compilação e execução do projeto |
| Spring Boot 4.1.2 e Spring Web MVC | Inicialização da aplicação e API HTTP |
| Spring Data JPA e Hibernate | Mapeamento das entidades, repositórios e consultas |
| PostgreSQL e driver JDBC | Banco relacional e conexão da aplicação |
| Flyway e módulo PostgreSQL | Aplicação das migrations versionadas |
| Jakarta Bean Validation | Restrições nos DTOs de entrada |
| Lombok | Geração de métodos em entidades |
| Starters de teste do Spring Boot | Suporte a testes de MVC, JPA, Flyway e validação |

## Como rodar localmente

### 1. Prepare o ambiente

Instale um JDK compatível com Java 17, Git e um servidor PostgreSQL. Confira o Java usado pelo terminal com `java -version`. O repositório inclui o Maven Wrapper, que baixa o Maven na primeira execução.

```bash
git clone https://github.com/KikuTiii/Mastersys.git
cd Mastersys
```

O repositório é privado. Para clonar, use uma conta do GitHub com acesso ao projeto.

### 2. Crie um banco local

No Ubuntu, inicie o PostgreSQL e abra o `psql` como administrador.

```bash
sudo service postgresql start
sudo -u postgres psql
```

No Windows, com o serviço PostgreSQL iniciado, abra o **SQL Shell (psql)** e conecte-se ao banco `postgres` com um usuário administrador.

Se ainda não tiver um banco para o projeto, execute cada comando abaixo separadamente no `psql`. O comando `\password` pede a senha e a confirmação antes de devolver o prompt.

```sql
CREATE ROLE mastersys LOGIN;
\password mastersys
CREATE DATABASE academia OWNER mastersys;
\q
```

### 3. Configure a conexão

Crie `application-local.properties` na raiz do projeto, ao lado do `pom.xml`. Troque a senha de exemplo pela senha definida no PostgreSQL.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/academia
spring.datasource.username=mastersys
spring.datasource.password=SUA_SENHA
```

Esse arquivo é ignorado pelo Git. Outra opção é configurar `DB_URL`, `DB_USERNAME` e `DB_PASSWORD` no ambiente e deixar o arquivo local ausente.

### 4. Inicie a API

No Ubuntu, execute na raiz do projeto:

```bash
bash ./mvnw spring-boot:run
```

No PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

No IntelliJ, abra o projeto pelo `pom.xml`, selecione o JDK do projeto e execute a classe `MastersysApplication`. Use a raiz do projeto como diretório de trabalho para carregar o arquivo local.

A API fica em `http://localhost:8080`. Em um banco vazio, o Flyway executa a `V1` para criar o esquema, a `V2` para popular o catálogo inicial e a `V3` para inserir alunos, matrículas e faturas fictícias. Use um banco de desenvolvimento para receber esses dados.

## Endpoints disponíveis

| Recurso | Operações |
| --- | --- |
| Alunos | `POST /alunos`, `GET /alunos`, `GET /alunos/{id}`, `PUT /alunos/{id}`, `DELETE /alunos/{id}` |
| Modalidades | `POST /modalidades`, `GET /modalidades` |
| Planos | `POST /planos`, `GET /planos`, `GET /planos/{id}` |
| Graduações | `POST /graduacoes`, `GET /graduacoes`, `GET /graduacoes/{id}` |
| Matrículas | `POST /matriculas`, `GET /matriculas`, `GET /matriculas/{id}` |
| Relatórios | `GET /relatorios/faturamento-mensal`, `GET /relatorios/alunos-por-cidade`, `GET /relatorios/faturas-em-aberto` |

As listagens recebem `page`, `size` e `sort`. A primeira página é `0`. Alunos podem ser filtrados por `nome`, `email`, `celular`, `cidade` e `estado`. Planos aceitam `modalidadeId` e `ativo`. Graduações aceitam `modalidadeId` e matrículas aceitam `alunoId`.

Para cadastrar uma matrícula, envie `alunoId`, `modalidadeId`, `planoId`, `graduacaoId` e `diaVencimento`. Busque os IDs no catálogo e escolha um plano e uma graduação da mesma modalidade.

O faturamento mensal soma faturas pagas por **mês de vencimento**. A listagem de faturas em aberto seleciona o status `ABERTA`.

## Como verificar

Com a API rodando, execute as requisições em [alunos.http](alunos.http) e [planos.http](planos.http) pelo HTTP Client do IntelliJ. Ajuste os IDs de acordo com o seu banco. Também é possível verificar a listagem com:

```bash
curl "http://localhost:8080/planos?page=0&size=5&sort=nome,asc"
```

Para rodar o teste de inicialização, mantenha o PostgreSQL em execução e aponte a aplicação para um banco de desenvolvimento ou teste. Use `bash ./mvnw test` no Ubuntu ou `.\mvnw.cmd test` no PowerShell.

## Escopo atual

O projeto está em desenvolvimento para aprendizado. O domínio e o esquema já incluem faturas e assiduidade. A API disponibiliza relatórios financeiros, enquanto a geração de cobranças, o registro de pagamentos e a operação de entrada e saída ainda são etapas futuras.
