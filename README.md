# Suits

Suits - Interview Management System

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
./gradlew clean build
```

### Running

Run application
```
./gradlew bootRun
```

Run keycloak
```
docker run -p 8081:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -e KEYCLOAK_IMPORT=/tmp/suits-ims-realm.json -v /tmp/suits-ims-realm.json:/tmp/suits-ims-realm.json jboss/keycloak
```

## Authors

* **Siarhei Blashuk**