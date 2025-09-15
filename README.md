# DevOps Backend - Dev Container Tutorial

This tutorial will guide you through setting up and running this Spring Boot banking API application using Visual Studio Code Dev Containers.

> The backend container must be created before front-end container because he will create the front-end network.

## Prerequisites

Before you begin, ensure you have the following installed on your system:

1. **Docker Desktop** (Windows/macOS) or **Docker Engine** (Linux)
   - [Download Docker Desktop](https://www.docker.com/products/docker-desktop/)
   - Ensure Docker is running before proceeding

2. **Visual Studio Code**
   - [Download VS Code](https://code.visualstudio.com/)

3. **Dev Containers Extension**
   - Install the "Dev Containers" extension by Microsoft
   - Extension ID: `ms-vscode-remote.remote-containers`

## Project Overview

This project is a **Spring Boot Banking API** with the following components:

- **Backend**: Spring Boot 3.5.4 with Java 21
- **Database**: PostgreSQL 17 Alpine
- **Build Tool**: Maven
- **API Documentation**: Swagger/OpenAPI
- **Container Orchestration**: Docker Compose

### Key Features:
- RESTful API for banking operations
- JPA/Hibernate for database operations
- PostgreSQL integration
- Swagger UI for API documentation
- Lombok for reducing boilerplate code
- Validation support

## Getting Started

### Step 1: Clone and Open the Repository

1. Clone this repository to your local machine:
   ```bash
   git clone <your-repo-url>
   cd devops-back
   ```

2. Open the project in Visual Studio Code:
   ```bash
   code .
   ```

3. Create a .env file with the same content of `env-example.env`


### Step 2: Open in Dev Container

1. **Method 1 - Command Palette:**
   - Press `Ctrl+Shift+P` (or `Cmd+Shift+P` on macOS)
   - Type "Dev Containers: Reopen in Container"
   - Select the option and press Enter

2. **Method 2 - Notification:**
   - VS Code should show a notification asking if you want to reopen in container
   - Click "Reopen in Container"

3. **Method 3 - Status Bar:**
   - Click the green remote connection indicator in the bottom-left corner
   - Select "Reopen in Container"

### Step 3: Container Initialization

The dev container will automatically:

1. **Build the Docker images** (Java 21 + Maven/Gradle)
2. **Start PostgreSQL database** with the following credentials:
   - Database: `conta`
   - Username: `postgres`
   - Password: `postgres123`
   - Port: `5432`
3. **Install dependencies** using Maven
4. **Run the application** on port `8080`

This process may take 5-10 minutes on the first run.

## Dev Container Configuration

### Container Services

The dev container uses Docker Compose with two services:

#### Application Container (`app`)
- **Base Image**: `mcr.microsoft.com/devcontainers/java:1-21-bullseye`
- **Tools**: Java 21, Maven, Gradle
- **Port**: 8080 (Spring Boot application)
- **Volume**: Project files mounted at `/workspaces`

#### Database Container (`db`)
- **Image**: `postgres:17-alpine`
- **Port**: 5432
- **Database**: `conta`
- **Persistent Storage**: `postgres-data` volume

### Environment Variables

The application uses these environment variables:
```
DB_URL=jdbc:postgresql://db:5432/conta
DB_USER=postgres
DB_PASSWORD=postgres123
```

### VS Code Extensions

The dev container automatically installs:
- **Java Extension Pack** - Complete Java development support

### Port Forwarding

The following ports are automatically forwarded:
- `5432` - PostgreSQL database
- `8080` - Spring Boot application

## Running the Application

### Automatic Startup

The application starts automatically when the container is created via the `postCreateCommand`:
```bash
mvn clean install && mvn spring-boot:run
```

### Manual Control

If you need to restart or control the application manually:

#### Stop the Application
```bash
# Press Ctrl+C in the terminal where the app is running
```

#### Start the Application
```bash
mvn spring-boot:run
```

#### Build the Application
```bash
mvn clean install
```

#### Run Tests
```bash
mvn test
```

## Accessing the Application

Once the application is running, you can access:

### API Endpoints
- **Base URL**: http://localhost:8080/api/v1

### Swagger UI (API Documentation)
- **URL**: http://localhost:8080/swagger-ui.html

### Database Access

You can connect to the PostgreSQL database using:
- **Host**: `localhost` (from your local machine) or `db` (from within containers)
- **Port**: `5432`
- **Database**: `conta`
- **Username**: `postgres`
- **Password**: `postgres123`

#### Using VS Code PostgreSQL Extension
1. Install the PostgreSQL extension
2. Create a new connection with the above credentials
3. Browse and query the database directly in VS Code

## Docker Production Setup

### Production Build

The project includes production Docker configuration:

#### Multi-stage Dockerfile
```bash
# Build production image
docker build -t devops-back .

# Run production container
docker run -p 8080:8080 devops-back
```

#### Docker Compose Production
```bash
# Start with docker-compose
docker-compose up -d
```
## Troubleshooting

### Common Issues and Solutions

#### Container Won't Start
**Problem**: Dev container fails to build or start
**Solutions**:
- Ensure Docker is running
- Check Docker Desktop has sufficient resources (4GB+ RAM recommended)
- Try rebuilding the container: `Ctrl+Shift+P` → "Dev Containers: Rebuild Container"

#### Port Already in Use
**Problem**: Port 8080 or 5432 is already in use
**Solutions**:
- Stop other applications using these ports
- Modify port mappings in `.devcontainer/docker-compose.yml`
