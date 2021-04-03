# P9_patientRisk Backend
This application is a medical web services application, il calculates the risk for a patient : 
It calls the Patient and Note API


## Technical:
1. Framework: Spring Boot v2.2.5
2. Java 8
3. Maven 3.6


## Setup 
1. Install Java: https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
2. Spring : https://spring.io


## Docker
The Docker file has been set, this application is enabled for Docker

Docker commands are (type from root folder P9_patientRisk): 
1. Build the image : "docker build --tag patientRisk ."
2. Run the image : "docker run -p 8052:8052 patientRisk"
3. Stop the image  : "docker stop patientRisk ."
4. Remove the image :  "docker rmi -f patientRisk"


## Unit Test
1. Unit tests are written for Utils, Domain, Services and Controller
2. Integration tests are written for controller


# Maven
1. mvn clean install
2. mvn clean verify  : generate tests and tests reports
3. mvn site  : generate all reportings
4. mvn spring-boot:run (run app)
5. mvn spring-boot:stop (stop app) 


## Run & tests
1. Run P9PatientRiskApplication
2. Open in a browser http://localhost:8052 for test environment


### Other consideration
JAVADOC has been initialized and needs to be completed