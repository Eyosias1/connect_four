# Power 4 Game Project Documentation

## Introduction
This document provides an overview of the implementation of the Power 4 game in Java using Object-Oriented Programming (OOP) principles. The project consists of four main Java classes, each serving a specific role in the game.

## Compilation and Execution
To compile the project, use the following command:
```sh
javac -d build src/*.java
```
To execute the compiled program, use:
```sh
java -cp build src.Prog_princ
```

## Classes

### `Cellule`
The `Cellule` class represents a cell in the game grid. It has attributes for color and neighbors. Enumerations are used for colors and neighbor types. Methods are implemented to set and retrieve the color of a cell and to check for a winning alignment of four colors.

### `Matrice`
The `Matrice` class represents the game grid as a 2D array of `Cellule` objects. It manages the game board and keeps track of available spaces in each column. The class provides methods for initializing the grid, checking if a column is full, decrementing available spaces, and printing the grid.

### `Prog_princ`
The `Prog_princ` class serves as the main program entry point. It manages the game flow, including player turns, input validation, displaying the game grid, and checking for a win or draw condition. The class interacts with the `Matrice` class to access and manipulate the game grid.

### `File_game`
The `File_game` class handles file operations for saving and loading game progress. It allows players to save the game state to a file or load a previously saved game. The class reads and writes game data to a text file, including player names, colors, and the current game board configuration.

## Usage
1. Run the program.
2. Choose whether to start a new game or continue a saved game.
3. Provide player names and select token colors (blue or red).
4. Follow the prompts to take turns placing tokens on the game grid.
5. Save the game progress by entering `999` during gameplay.
6. Continue playing until a player wins or the game ends in a draw.

## Recursion and Optimization
The game implementation utilizes recursion for optimized win-checking algorithms instead of using nested loops. Recursion is employed to traverse the game grid in all possible directions from the last placed token, reducing the need for multiple nested loops and improving performance.

## Conclusion
This Power 4 game implementation demonstrates the use of OOP principles in Java programming. By dividing the functionality into separate classes, the codebase remains modular and easy to maintain. Players can enjoy an interactive gaming experience with features for saving and resuming games.
