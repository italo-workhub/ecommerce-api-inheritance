# E-commerce API – Gestão de Produtos

API REST desenvolvida em **Java + Spring Boot** para gestão de produtos em um cenário de e-commerce.

O projeto utiliza **herança e polimorfismo** para modelar diferentes tipos de produtos, com persistência via **JPA/Hibernate** e suporte a **JSON polimórfico** no consumo da API.

O foco é aplicar conceitos de orientação a objetos de forma prática e organizada, simulando um caso real de backend.

---

## Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* Jackson (JSON polimórfico)
* Banco de dados relacional (H2 / MySQL / PostgreSQL)
* Maven

---

## Conceitos Aplicados

* Programação Orientada a Objetos (POO)

    * Herança
    * Polimorfismo
    * Abstração
* API REST
* CRUD completo (Create, Read, Update, Delete)
* Mapeamento JPA com `@Inheritance(strategy = JOINED)`
* Polimorfismo em JSON com `@JsonTypeInfo`
* Regras de negócio encapsuladas nas entidades

---

## Modelagem de Domínio

### Produto (Classe Abstrata)

Classe base do domínio, contendo os atributos comuns a todos os produtos:

* `id`
* `nome`
* `preco`
* `precoFinal`

Também define o comportamento comum de cálculo de preço, executado automaticamente antes de operações de persistência e atualização.

### Tipos de Produto

#### 📘 Livro

* Atributo específico: `genero`
* Preço final sem acréscimo

#### 🔌 Eletronico

* Atributo específico: `voltagem`
* Acréscimo de 10% no preço final

---

## Estratégia de Herança (JPA)

```java
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_produto")
```

Cada tipo de produto possui sua própria tabela, mantendo normalização e clareza no banco de dados.

---

## JSON Polimórfico (Jackson)

```java
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "tipo"
)
```

O campo `tipo` define automaticamente qual subclasse será instanciada no backend.

---

## Endpoints

### ➕ Criar Produto

`POST /api/produtos`

**Livro**

```json
{
  "tipo": "livro",
  "nome": "Clean Code",
  "preco": 120.00,
  "genero": "Tecnologia"
}
```

**Eletrônico**

```json
{
  "tipo": "eletronico",
  "nome": "Monitor Gamer",
  "preco": 1500.00,
  "precoFinal": 1650,
  "voltagem": 220
}
```

---

### 📄 Listar Produtos

`GET /api/produtos`

Retorna todos os produtos, respeitando seus tipos específicos.

---

### ✏️ Atualizar Produto

`PUT /api/produtos/{id}`

Atualiza os dados do produto mantendo seu tipo original.

---

### ❌ Remover Produto

`DELETE /api/produtos/{id}`

Remove um produto pelo ID.

---

## Estratégia de Atualização

A atualização segue boas práticas:

1. Busca o produto existente no banco
2. Atualiza campos comuns
3. Atualiza campos específicos conforme o tipo
4. Recalcula automaticamente o preço final

Isso garante **consistência**, **segurança** e **uso correto do polimorfismo**.

---

## Objetivo

Este projeto tem como objetivo:

* Consolidar conceitos avançados de POO em Java
* Demonstrar uso real de herança em APIs REST
* Servir como material de estudo e portfólio
* Evoluir futuramente para DTOs, validações e testes

---

## Possíveis Evoluções

* PATCH parcial por tipo de produto
* Validações com `@Valid`
* DTOs polimórficos
* Tratamento global de exceções (`@ControllerAdvice`)
* Testes unitários

---

## Autor

Projeto desenvolvido para fins de estudo e consolidação de conceitos de **backend com Java e Spring Boot**, com foco em boas práticas, organização de código e uso consciente de orientação a objetos.
