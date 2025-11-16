# Spring Simple

A simple Spring Boot REST API for personal reference.

## Technologies Used

- Java
- Spring Boot
- Maven
- Cloud SQL
- Spring Data JPA
- Spring Validation

## Configuration

### Application Properties

The application uses Spring profiles for environment-specific configuration.

**`application.properties`**
```properties
spring.application.name=simple-user
spring.profiles.active=dev
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

**`application-dev.properties`**
```properties
DB_URL=YOUR_DB_URL
DB_USERNAME=YOUR_DB_USERNAME
DB_PASSWORD=YOUR_DB_PASSWORD
```

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd simple-user
   ```

2. **Configure MySQL database**
    - Create database: `simple_user_db`
    - Update credentials in `application-dev.properties`

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

## License

MIT