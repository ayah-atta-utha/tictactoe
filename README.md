# TicTacToe Spring Boot Application

This is a simple TicTacToe game built using Spring Boot. It provides a backend API to manage the game state, and can be deployed using Kubernetes.

## Features

- **Start Game**: Start a new TicTacToe game with a customizable board size.
- **Make Move**: Make a move by specifying the row and column on the board.
- **Game Over**: The game ends when there is a winner or a draw.
- **Player Names**: Players can provide their names at the start of the game.
  
## Prerequisites

Before you start, ensure that you have the following tools installed on your machine:

- **Java 21+** (JDK)
- **Maven** (for building the project)
- **Docker** (for containerization)
- **Kubernetes** (for deployment)
- **Docker Hub** account (for pushing and pulling Docker images)

## Setting Up the Project

### Clone the repository

```bash
git clone https://github.com/ayah-atta-utha/tictactoe.git
cd tictactoe
```
### Build the war package

```bash
mvn clean package
```
### Run the app locally 

```bash
java -jar target/tictactoe-app.war
```
### Optional Build Docker Image and Run it in Docker

```bash
docker build -t tictactoe-app .
docker run -p 8080:8080 tictactoe-app
```
  #### Open your browser 
  ```bash
  http://localhost:8080
  ```

