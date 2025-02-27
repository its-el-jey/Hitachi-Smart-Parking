<!-- 
Author: Loida Jane AnfONE
Modified by: 
Last Updated: January 22, 2025
-->
# Spring Boot Project Setup

## Hitachi Smart Parking

This is a Java Spring Boot application for managing smart parking systems.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Git

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/its-el-jey/Hitachi-Smart-Parking.git
   cd Hitachi-Smart-Parking

2. Open the Project in IntelliJ IDEA
   Open IntelliJ IDEA.
   Click on File > Open....
   Select the cloned project directory.
   IntelliJ will automatically detect the project as a Maven project and import it.

3. Configure Java 21
   Go to File > Project Structure....
   Under Project Settings, select Project.
   Set the Project SDK to Java 21. If Java 21 is not listed, click New... and select the JDK installation directory.
   Set the Project language level to 21 - Patterns for switch.

4. Build the Project
   Open the Terminal in IntelliJ IDEA.
   Run the following command to build the project:

   ```bash
   mvn clean install

5. Run the Application: After building the project, run the application using:

   ```bash
   mvn spring-boot:run

### Additional Steps if Maven Wrapper is Not Included
If the .mvn folder is not included in the repository, ensure that Maven is installed on your system. You can download and install Maven from the official website.

<br> <a href=https://maven.apache.org/install.html> Installation instructions for the latest Maven version </a> <br>

### Usage
Once the application is running, you can access it at http://localhost:8080.

### Testing with Postman

1. Import the Postman Collection:
- Open Postman.
- Click on Import in the top left corner.
- Select the Postman collection file provided in the repository (if available) or create your own collection.

2. Run the Requests:
- Use the imported Postman collection to test the various endpoints of the application.

### Example Postman Request
To test the check-in-vehicle endpoint, you can create a POST request in Postman with the following details:

URL: http://localhost:8080/check-in-vehicle
-Method: POST
-Params:
-lotId: The UUID of the parking lot.
-licensePlate: The license plate of the vehicle.
Example:

POST /check-in-vehicle?lotId=123e4567-e89b-12d3-a456-426614174000&licensePlate=ABC123

<b>Note: Make sure to get the correct lotId and licensePlate from the URL: GET /parking-lot/all and GET /vehicle/all</b>

