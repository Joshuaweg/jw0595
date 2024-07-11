# Tool Rental Application

This is a tool rental application that runs in Java. It allows users to rent tools, calculate rental agreements, and handle various rental scenarios.

## Getting Started

### Prerequisites

Ensure you have Java installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/javase-downloads.html).

### Directory Structure

- `src/`: Contains the source code of the application.
- `bin/`: Contains the compiled classes.
- `lib/`: Contains the testing libraries (JUnit and Hamcrest).

### Running the Application

#### Running the Main Application

To run the main application, use the following command:

```sh
java -cp ".;bin" RentalApp
```
To run the test cases, use the following command:

```sh
java -cp ".;bin;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore RentalAgreementTest
```
