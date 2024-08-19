# Test Case Management Tool

This project is a Test Case Management Tool with a React frontend, a Spring Boot backend, and a MySQL database. The tool allows users to create, manage, and track test cases for various projects.

## Prerequisites

Ensure that your system has the following installed:

- **Java JDK 17 or later**
    - [Download Link](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **Maven**
    - [Download Link](https://maven.apache.org/install.html)
- **Node.js and npm (Node Package Manager)**
    - [Download Link](https://nodejs.org/)
- **MySQL**
    - [Download Link](https://dev.mysql.com/downloads/installer/)


## Setup Instructions

[Download Frontend by cloning this project](https://github.com/SaurabhPethani/test-case-management-tool-fe/tree/master)

[Download backend by cloning this project](https://github.com/SaurabhPethani/test-case-management-tool/tree/master)

### Backend Setup (Spring Boot)

1. **Navigate to the `test-case-management-tool` directory:**

   ```
    cd test-case-management-tool
    mvn clean install

    spring.application.name=Test Case Management
    spring.datasource.url=jdbc:mysql://localhost:3306/test_case_management?useSSL=false&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=admin
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    
    allowed.users=saurabh,admin,test
    
    jwt.secret.key=thisissecret,NotToExposeAnyone,#SECURE
    spring.security.user.name=abc
    spring.security.user.password=1234
   
    mvn package

### Database Setup (MySQL)
##### Install MySQL:

Ensure that MySQL is installed on your machine. You can download it from the [MySQL official website](https://dev.mysql.com/downloads/installer/).

##### Start MySQL Server:

Start your MySQL server. This varies depending on your operating system:

```
# On Windows
net start MySQL

# On Linux
sudo service mysql start
```
#### Create the Database:

Log in to the MySQL shell and create a new database:

```
mysql -u root -p

CREATE DATABASE test_case_management;

CREATE TABLE `test_case_management`.users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role VARCHAR(255) NOT NULL
);


CREATE TABLE test_case_management.projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    active BOOLEAN DEFAULT TRUE,
    name VARCHAR(255) NOT NULL,
    jira_ticket_id VARCHAR(255) NOT NULL
);

CREATE TABLE test_case_management.test_cases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    project_id BIGINT NOT NULL,
    test_case_id VARCHAR(255) NOT NULL,
    test_case_name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    input_data TEXT NOT NULL,
    expected_result TEXT NOT NULL,
    actual_result TEXT NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

```
### Frontend Setup (React)
#### Navigate to the test-case-management-tool-fe directory:
```
cd test-case-management-tool-fe
```
### Install Node.js dependencies:

Ensure that Node.js and npm are installed. Run the following command to install the dependencies:

```
npm install
```
### Configure Environment Variables:

Create a .env file in the frontend directory and set the environment variables:

```
REACT_APP_API_URL=http://localhost:8080/api
```
Build the Project:

You can build the React project for production using:
```
npm run build
```
### Running the Application
#### Backend (Spring Boot)
#### Start the Backend:

Run the Spring Boot application:
```
cd test-case-management-tool
mvn spring-boot:run
```
#### The backend should now be running on http://localhost:8080.

### Frontend (React)
#### Start the Frontend:

- Run the React development server:
```
cd test-case-management-tool-fe
npm start
```
The frontend should now be running on http://localhost:3000.

### Useful Commands
#### Backend

#### Build the Project:
```
mvn clean install

### Frontend
#### Run Development Server:
```
npm start
#### Build for Production:
```
npm run build


