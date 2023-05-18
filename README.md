# Portfolio

As part of my portfolio, I have created this example that demonstrates my abilities in software development.

This application is designed to schedule routes for trade ships. Users can add elements to a product shipping route, and once all items have been added, a schedule can be created. The application then uses speed and availability to determine the warehouse and ship that will fulfill and deliver the order on time.

# Running the application from the jar file:
Download the jar from:
https://github.com/henrico/Portfolio/releases/tag/r0.0.1
Then start the jar file, and navigate to http:localhost:8080 in the browser.

# Running the code:

## Setting up the Backend

### Prerequisites

The following are required to set up the backend:

- Java 17
- Maven

### Installation

1. Clone the repository to your local machine.
2. Run `mvn install` to install the required dependencies.
3. Run `mvn exec:java` to start the backend on port 8080.

### Running the Backend

To start the backend, run `mvn exec:java` in the root directory of the backend using the command line.

### Database

The backend currently runs on an in-memory database.

## Setting up the Frontend

### Prerequisites

The following is required to set up the frontend:

- Node version 16.0.0

### Installation

1. Navigate to the root directory(/html) of the frontend using the command line.
2. Run `npm install` to install the required dependencies.

### Running the Frontend

To start the frontend service, run `npm start` in the root directory of the frontend using the command line.

