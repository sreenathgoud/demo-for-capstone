# Employee Demo — Spring Boot, Docker, and AWS

A small REST API for creating employees, listing them, and looking them up by ID. Employee data is stored in MySQL. The app can run locally or in Docker and is prepared for deployment to an existing EC2 instance with an existing RDS MySQL database.

## Requirements

- Java 21
- Maven 3.9+ (or use the Maven wrapper after generating it)
- Docker Desktop (optional, for container workflow)
- MySQL 8 (or Docker Compose)

## Run locally

Start local MySQL with Docker Compose:

```powershell
docker compose up -d mysql
```

Set database credentials if needed (defaults are for local development only):

```powershell
$env:DB_URL = 'jdbc:mysql://localhost:3306/employee_demo'
$env:DB_USERNAME = 'employee'
$env:DB_PASSWORD = 'employee'
mvn spring-boot:run
```

The API listens on `http://localhost:8080`.

## API

| Method | Path | Description |
|---|---|---|
| `POST` | `/api/employees` | Create an employee |
| `GET` | `/api/employees` | List all employees |
| `GET` | `/api/employees/{id}` | Find an employee by ID (404 if absent) |
| `PUT` | `/api/employees/{id}` | Update an employee |
| `DELETE` | `/api/employees/{id}` | Delete an employee |

Example request:

```json
{
  "name": "Asha Kumar",
  "email": "asha@example.com",
  "department": "Engineering",
  "salary": 85000
}
```

## Docker

```powershell
docker compose up --build
```

This starts the API and local MySQL. For a remote RDS database, set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` in the environment of the container. Do not commit production credentials.

## AWS deployment outline

1. Build the image and push it to an ECR repository.
2. On the existing EC2 host, authenticate Docker to ECR, pull the image, and run it with RDS connection environment variables.
3. Permit EC2-to-RDS traffic on MySQL port 3306 and ensure RDS is not publicly exposed unless you have a specific need.
4. Add GitHub repository secrets `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_REGION`, `ECR_REPOSITORY`, `EC2_HOST`, `EC2_USER`, and `EC2_SSH_KEY`. Configure the workflow's `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` secrets as well.

The GitHub Actions workflow builds and pushes to ECR, then connects to EC2 over SSH to replace the running container. AWS IAM permissions and security-group rules must be configured for the existing resources.
