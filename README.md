# The-COGIP-Microservice-Architecture
### Intro
Welcome to the COGIP Microservice Architecture Project, a Java based microservice application for managing accounting data. This project includes:
* REST API for data management (BACK-END).
* REST CLI (Command Line Interface) for user interaction (FRONT-END).

Designed for accounting expert Jean Christian Ranu.

## Overview
#### API
* **CRUD Operations**: Fully supported for `company`, `contacts`, `invoices`, and `users`.
* **Role Based Access Control (RBAC)**:
    - **`Admin`**: Full access.
    - **`Accountant`**: No access to users.
    - **`Intern`**: Read only access, excluding users.
* **RESTful Endpoints**: JSON based input and output.
 
#### CLI
* **Non-Interactive Interface**: Script based operation, for `company`, `contacts`, `invoices`, and `users`.
* **Subcommands and Flags**: 
    - **`list`**: Displays a list of all data.
    - **`add`**: Adds a new data to the specified list.
    - **`delete`**: Deletes data by its unique ID.
* **Authentication**: Role based access via username and password.

## Setup Instructions
#### Required:
* Java 21
* Maven 3.9.9

#### Optional:
* SQLite Database

### Installation
1. **Clone the repository**:
```
https://github.com/beekaa6/The-COGIP-Microservice-Architecture.git
```

2. **Navigate to the project directory**:

```
cd REST_API
``` 
or 
```
cd REST_CLI
```
3. **Build the project**:
```
mvn clean install
```

## Usage

### CLI Commands


**List**

1. Get full data list as JSON format: (example company)
```
mvn exec:java -Dexec.mainClass="com.example.App" -Dexec.args="list company"
```

**Add**

2. Create a new entity: (example contact)
```
mvn exec:java -Dexec.mainClass="com.example.App" -Dexec.args="add contact --firstName="Alex" --lastName="Mahone" --phone="0412345678" --email="alex@alex.alex" --contactCompany="1""
```

**Delete**

3. Remove an entity by ID: (example invoice)
```
mvn exec:java -Dexec.mainClass="com.example.App" -Dexec.args="delete invoice 4"
```

## 
> **Note for Windows 11 Terminal (PowerShell)**:
> 
> >If you are running these commands in Windows Terminal (PowerShell), you might encounter issues with argument parsing. To prevent this, you need to use --% after the mvn command. This tells PowerShell to stop interpreting the command and pass the arguments directly to Maven.
>
> **Example**:
> ```
> mvn --% exec:java -Dexec.mainClass="com.example.App" -Dexec.args="list company"
> ```
##

### API Endpoints
| Method  | Endpoint | Description | Role Access |
| --------| -------- | ------------|:-----------:|
| GET | /company | Get all company | ADMIN, ACCOUNTANT, INTERN |
| POST | /company | Create a new company | ADMIN, ACCOUNTANT |
| PUT | /company | Update a company | ADMIN, ACCOUNTANT |
| DELETE | /company/{id} | Delete a company by ID | ADMIN, ACCOUNTANT |

##

# Becode
