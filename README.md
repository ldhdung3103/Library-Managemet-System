# Library-WebApp

# Library Management System

## Introduction

Library Management System is a web-based application developed using Java Servlet, JSP, JDBC, and MySQL. The system helps manage library operations such as book management, borrowing and returning books, penalty management, user management, and activity monitoring.

## Features

### Student

* Login to the system
* View available books
* Borrow books
* View borrowed books
* Check borrowing status (Pending, Borrowed, Returned, Rejected, Overdue)
* Logout

### Librarian

* Manage books (Add, Delete)
* Approve or reject borrowing requests
* Confirm returned books
* Manage penalties
* View all borrow records
* Logout

### Manager

* View system dashboard
* View library reports
* Add new users
* Activate or suspend accounts
* Delete user accounts
* View activity logs
* Logout

## System Architecture

The project follows the MVC (Model – View – Controller) architecture:

* Model: Java Beans, DAO classes, Database
* View: JSP pages
* Controller: Servlet classes

## Technologies Used

* Java
* Jakarta Servlet
* JSP
* JDBC
* MySQL
* Apache Tomcat
* Maven
* HTML/CSS/JavaScript

## Database

Main tables:

* users
* books
* borrow_records
* penalties
* activity_logs

## Project Structure

src/main/java

* controller
* dao
* model
* utils

src/main/webapp

* librarian
* manager
* student

frontend

* css
* js
* librarian
* manager
* student

## Installation

1. Clone the repository

```bash
git clone <repository-url>
```

2. Create database

```sql
CREATE DATABASE library_management;
```

3. Import

```sql
library_management.sql
```

4. Configure database connection

Update database information in:

```java
DBConnection.java
```

5. Run the project

```bash
mvn clean install
```

Deploy to Apache Tomcat and access:

```text
http://localhost:8080/Libraries/frontend/index.html
```

## Sample Accounts

Manager:

* Username: manager1
* Password: 123456

Librarian:

* Username: librarian1
* Password: 123456

Student:

* Username: student1
* Password: 123456

## Authors

Group Project – Library Management System

## License

This project is licensed under the MIT License - see the LICENSE file for details.
