# API de Gerenciamento de Reservas de Salas

![Java](https://img.shields.io/badge/Java-21-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-green?logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)
![JUnit 5](https://img.shields.io/badge/JUnit-5-red?logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-4.0-orange?logo=mockito)

Este projeto é uma **API RESTful** construída em **Java com Spring Boot**, destinada ao gerenciamento de reservas de salas. O objetivo é aplicar conceitos de **orientação a objetos**, **Spring Data JPA**, **boas práticas de desenvolvimento**, **validações de negócio**, **versionamento de API** e **testes unitários**.

---

## Funcionalidades

- **CRUD de Salas**
    - Criar, listar, atualizar e deletar salas.
    - Validação de atributos (capacidade positiva, status ativo/inativo).
- **CRUD de Usuários**
    - Criar, listar, atualizar e deletar usuários.
    - Validação de e-mail único.
- **CRUD de Reservas**
    - Criar, listar, atualizar e cancelar reservas.
    - Validações de negócio:
        - Não reservar salas inativas.
        - Datas de início anteriores às de fim.
        - Capacidade máxima respeitada.
        - Não sobreposição de reservas (intervalo semiaberto: início incluído, fim não incluído).
        - Reservas canceladas não entram na checagem de conflito.
- **Versionamento de API**
    - Todas as rotas estão prefixadas com `/api/v1`.
- **Respostas de erro padronizadas**
    - HTTP 400 para validações.
    - HTTP 404 para entidades não encontradas.
    - Mensagens claras para facilitar o consumo da API.

---

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-21-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-green?logo=spring)
![Hibernate](https://img.shields.io/badge/Hibernate-6.3-orange?logo=hibernate)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)
![JUnit 5](https://img.shields.io/badge/JUnit-5-red?logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-4.0-orange?logo=mockito)
![Docker](https://img.shields.io/badge/Docker-24.0-blue?logo=docker)

---

## Modelagem de Entidades

### Sala
- `id` - Identificador único.
- `nome` - Nome da sala (único, não nulo).
- `capacidade` - Número de pessoas permitido (positivo).
- `ativo` - Define se a sala está disponível para reservas.

### Usuário
- `id` - Identificador único.
- `nome` - Nome completo do usuário.
- `email` - E-mail único do usuário.

### Reserva
- `id` - Identificador único.
- `sala` - Referência para a sala reservada.
- `usuario` - Referência para o usuário que fez a reserva.
- `inicio` - Data e hora de início da reserva.
- `fim` - Data e hora de término da reserva.
- `status` - ATIVA ou CANCELADA.

---

---

## Validações de Negócio

1. **Salas inativas não podem ser reservadas**
2. **Datas de início devem ser anteriores às de fim**
3. **Capacidade deve ser positiva**
4. **Não sobreposição de reservas**
    - Intervalo semiaberto `[inicio, fim)`.
    - Exemplo: se uma reserva termina às 10:00, outra pode começar às 10:00.
5. **Reservas canceladas não entram em conflito**
6. **Transições de status de reserva**
    - ATIVA → CANCELADA
    - Cancelamento não altera histórico de conflito

---

## Endpoints REST

### Salas
- `GET /api/v1/salas` - Listar todas as salas
- `GET /api/v1/salas/{id}` - Buscar sala por ID
- `POST /api/v1/salas` - Criar sala
- `PUT /api/v1/salas/{id}` - Atualizar sala
- `DELETE /api/v1/salas/{id}` - Remover sala

### Usuários
- `GET /api/v1/usuarios`
- `GET /api/v1/usuarios/{id}`
- `POST /api/v1/usuarios`
- `PUT /api/v1/usuarios/{id}`
- `DELETE /api/v1/usuarios/{id}`

### Reservas
- `GET /api/v1/reservas`
- `GET /api/v1/reservas/{id}`
- `POST /api/v1/reservas`
- `PUT /api/v1/reservas/{id}`
- `DELETE /api/v1/reservas/{id}` → Cancela a reserva

---

## Persistência com JPA

- Mapeamento de entidades com `@Entity`, `@ManyToOne` e `@JoinColumn`.
- Repositórios estendem `JpaRepository` para CRUD básico.
- Consultas personalizadas para verificar conflito de reservas por sala e período.
- Transações anotadas com `@Transactional` para operações de escrita.

---

## Testes Unitários

- Cobertura de **cenários felizes** e **validações de erro**.
- Testes principais:
    - Criação de reservas válidas
    - Tentativa de reserva em sala inativa
    - Sobreposição de horários
    - Cancelamento de reservas
- Estrutura de teste:


