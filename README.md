# Running locally

**Requirements:** Java 21, Maven, MySQL running locally.

1. Create a database in MySQL:

   ```sql
   CREATE DATABASE investment_tracker;
   ```

2. Create a `.env` file in the project root:

   ```env
   DATABASE_NAME=investment_tracker
   SQL_USER=your_mysql_user
   SQL_PASSWORD=your_mysql_password
   OPENAI_API_KEY=your_openai_api_key
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
