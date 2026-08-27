# E-Commerce GraphQL API

Java 21 + Spring Boot 3 + Spring GraphQL + PostgreSQL.

## Run

1. Start PostgreSQL:
   docker compose up -d

2. Start the app:
   mvn clean spring-boot:run

3. Open GraphiQL:
   http://localhost:8080/graphiql

## Example query

query {
  products {
    id
    name
    price
    stock
    category { id name }
  }
}
