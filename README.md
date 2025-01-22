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

### Usage
Once the application is running, you can access it at http://localhost:8080.

### Testing with Postman

1. Import the Postman Collection:
- Open Postman.
- Click on Import in the top left corner.
- Select the Postman collection file provided in the repository (if available) or create your own collection.

2. Run the Requests:
- Use the imported Postman collection to test the various endpoints of the application.
