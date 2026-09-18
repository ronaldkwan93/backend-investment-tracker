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
