# Country Information Microservice

This project implements a microservice that provides a list of countries and detailed information for each country. The microservice is built using Spring Boot and follows best practices for microservice architecture.

## Features

- **REST API**:
  - `GET /countries/`: Fetches a list of all countries.
  - `GET /countries/{name}`: Fetches detailed information about a specific country.
- **External Service Integration**: The country service fetches relevant information from an external service, for example, [CountriesNow](https://countriesnow.space/).

## Bonus Features

- **Reactor Core**: Utilizes Reactor for reactive programming.
- **Web Application**: A separate web application that utilizes the REST API to display country information in a browser.

## Getting Started

### Prerequisites

- Java 11 or later
- Maven
- Git

### Installation

1. **Clone the repository**:
   ```sh
   git clone https://github.com/Nandalochana/Coding_Assignment.git
   cd Coding_Assignment/assignment


