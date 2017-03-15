Database (MySQL) ddl file location: src/main/resources/import.sql

Build with:
mvn clean install

Run with:
mvn spring-boot:run

Or use Spring Tool Suite (eclipse) to load, and run as spring boot app

TODOs that were left outstanding (ie: What I couldn't get done in any reasonable amount of time for a small coding exercise, after setting up a project from scratch):

-optional goals (multithreading)
-get SQL ddl file to apply during maven build
-validator to check there's no whitespace in name (guava contains whitespace)
-load testing
-Javascript validation neither box blank
-hibernate query (table) and database name in hibernate config spread out :/
-prettier 404 page
-better erroneous input handling