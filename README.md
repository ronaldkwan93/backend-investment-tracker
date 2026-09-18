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
   ```

3. Run the app:

   ```bash
   mvn spring-boot:run
   ```

The API will start on `http://localhost:8080`.

## Stopping the app

If it's running in your terminal, press `Ctrl+C`.

If it's stuck running in the background on port 8080, free the port:

```powershell
netstat -ano | findstr :8080
taskkill /PID <pid> /F
```
