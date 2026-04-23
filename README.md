# Smart Campus REST API

## Project Overview
Smart Campus REST API is a simple Java/JAX-RS service for managing campus rooms, sensors, and sensor readings. It uses in-memory data structures only (no database) and runs on an embedded Grizzly server. All responses are JSON.

## Technologies Used
- Java 17
- Maven
- JAX-RS (Jersey)
- Grizzly HTTP server (embedded)
- Jackson (JSON)

## Folder Structure (Summary)
```
smart-campus-rest-api/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/smartcampus/
        │       ├── config/
        │       ├── exception/
        │       ├── filter/
        │       ├── main/
        │       ├── model/
        │       ├── resource/
        │       └── store/
        └── resources/
```

## Run in IntelliJ (Recommended)
1. Open the project folder in IntelliJ.
2. Open Main.java.
3. Click Run.
4. Server starts at http://localhost:8080/api/v1

## Run with Maven
1. Open a terminal in the project root.
2. Build:
```
mvn clean compile
```
3. Run:
```
mvn exec:java
```

## Base URL
http://localhost:8080/api/v1

## Endpoint List
- GET /api/v1
- GET /api/v1/rooms
- POST /api/v1/rooms
- GET /api/v1/rooms/{roomId}
- DELETE /api/v1/rooms/{roomId}
- GET /api/v1/sensors
- GET /api/v1/sensors?type=CO2
- POST /api/v1/sensors
- GET /api/v1/sensors/{sensorId}/readings
- POST /api/v1/sensors/{sensorId}/readings

## Sample curl Commands
```
curl http://localhost:8080/api/v1
```
```
curl http://localhost:8080/api/v1/rooms
```
```
curl http://localhost:8080/api/v1/rooms/LIB-301
```
```
curl -X POST http://localhost:8080/api/v1/rooms \
  -H "Content-Type: application/json" \
  -d "{\"id\":\"SCI-101\",\"name\":\"Science Seminar Room\",\"capacity\":18}"
```
```
curl -X POST http://localhost:8080/api/v1/sensors \
  -H "Content-Type: application/json" \
  -d "{\"id\":\"TEMP-010\",\"type\":\"TEMP\",\"status\":\"ACTIVE\",\"currentValue\":20.1,\"roomId\":\"SCI-101\"}"
```
```
curl "http://localhost:8080/api/v1/sensors?type=CO2"
```
```
curl -X POST http://localhost:8080/api/v1/sensors/TEMP-010/readings \
  -H "Content-Type: application/json" \
  -d "{\"value\":21.3}"
```
```
curl http://localhost:8080/api/v1/sensors/TEMP-010/readings
```
```
curl -X DELETE http://localhost:8080/api/v1/rooms/SCI-101
```

## Coursework Report Answers (Concise)
- JAX-RS resource lifecycle: Resources are request-scoped by default; a shared static DataStore keeps state across requests.
- Discovery endpoint value: Single entry point that advertises resources (simple hypermedia).
- IDs vs full room objects: IDs keep links lightweight; full objects reduce extra calls depending on use case.
- DELETE idempotence: Repeating DELETE should not create new effects; a second delete can return 404.
- @Consumes mismatch: Non-JSON payloads typically yield 415 Unsupported Media Type or 400 Bad Request.
- Query params vs path filtering: Filters are optional and combinable, so query parameters fit.
- Sub-resource locator benefits: Keeps nested context (sensorId) in one place and avoids duplicated lookup logic.
- Why 422 for missing linked resource: Endpoint and payload are valid, but a referenced entity is missing.
- Stack trace risk: Exposes internal details and can aid attackers.
- Why filters help logging: They apply to all requests/responses without repeating code.
