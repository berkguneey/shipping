# Fleet Management Backend API

In this application, we want to design a small-scale fleet management system where vehicles make deliveries to predetermined locations along a certain route.

## Description

The system includes two different types of shipments that can be transported in vehicles and unloaded at delivery points. Delivery points, barcode numbers and volumetric weight are specified on the shipments. Vehicles must have license plates to be registered in the system. 
Shipments with delivery points that do not meet the aforementioned criteria may not be unloaded. In such cases, these particular shipments must be skipped and the remaining shipments should be checked if they meet the criteria for unloading.

The system includes three different delivery points.

Branch: Only package-type shipments can be unloaded. Bags and packages in bags may not be unloaded.  
Distribution Center: Bags, packages in bags and packages not assigned to any bags may be unloaded.  
Transfer Center: Only bags and packages in bags may be unloaded.

## Getting Started

### Using

* Spring Boot
* Java 8
* H2 (In-memory database with initial data)
* Maven
* Junit 5
* Docker
* Log4j2 (Rolling File - Console Appender)

### Executing program

* This command executes the application without test values. For this reason, integration tests will fail.
* If you want to work with test data, this command should be commented out. (#)
```
spring.sql.init.mode=never
```

* The application can be run locally or in a docker container, the requirements for each setup are listed below.
* You can execute the program on 8080 port with Docker. Go to the project folder then execute following commands.
```
docker build --tag fleet-management-be-api .
docker run -p 8080:8080 fleet-management-be-api
```
### Requirements

* Docker
* Java 8 SDK
* Maven

## License

Distributed under the MIT License. See [`LICENSE`](https://choosealicense.com/licenses/mit/) for more information.

## Contact

Berk Can Güney - [Github](https://github.com/berkguneey)
