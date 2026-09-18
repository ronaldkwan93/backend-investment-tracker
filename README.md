# Investment Property Tracker — Backend

A REST API for tracking real estate investment properties, with an AI chat
assistant that can query and manage property data on the user's behalf.

## Tech Stack

| Technology | Why it's used |
|---|---|
| **Java 21 + Spring Boot 3** | Industry-standard for production-grade REST APIs; strong typing, mature ecosystem, easy to test and deploy. |
| **Spring Web (MVC)** | Exposes the property CRUD API as REST endpoints. |
| **Spring Data JPA + Hibernate** | Maps `Property` records to the database without hand-written SQL, while `PropertyRepository` still supports custom queries (e.g. address search). |
| **MySQL** | Relational database for persisting property data — a natural fit for structured, related financial records. |
| **Spring AI** | Abstraction layer for wiring an LLM (OpenAI) into the app — handles prompts, tool-calling, and message history without hand-rolling API calls. |
| **Model Context Protocol (MCP) server** | Exposes the property tools (list, search, create, update, estimate yield) as a standard interface any MCP-compatible client (VS Code Copilot, Claude Desktop, etc.) can call — not just the built-in chatbot. |
| **OpenAI (via Spring AI)** | Powers the `/api/chat` endpoint: a natural-language assistant scoped to investment-property questions that can call the tools above to answer with real data. |
| **Maven** | Dependency management and build tool. |

## Running locally

**Requirements:** Java 21, Maven, MySQL running locally.

1. Create a database in MySQL:

   ```sql
   CREATE DATABASE investment_tracker;
   ```

2. Copy `.env.example` to `.env` and fill in your values:

   ```bash
   cp .env.example .env
   ```

3. Run the app:

   ```bash
   mvn spring-boot:run
   ```

The API will start on `http://localhost:8080`.

## Chat endpoint

`POST /api/chat` — send a message and get a reply from OpenAI, with access to the
property tools (list/find/create/update properties, estimate yield).

```json
{
  "message": "What's the gross yield on my property at 12 Smith St?",
  "history": [
    { "role": "user", "content": "hi" },
    { "role": "assistant", "content": "Hi! Ask me about your investment properties." }
  ]
}
```

`history` is optional — the frontend is responsible for keeping and resending prior
turns to maintain conversation context.

## Stopping the app

If it's running in your terminal, press `Ctrl+C`.

If it's stuck running in the background on port 8080, free the port:

```powershell
netstat -ano | findstr :8080
taskkill /PID <pid> /F
```
