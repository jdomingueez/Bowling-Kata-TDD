# Bowling Kata TDD

This project is based on the Bowling Game Kata provided as a starting template.

## Objective

The goal was to extend the initial implementation using Test-Driven Development (TDD) and improve the system to be more robust and fault-tolerant.

## Features

The implementation includes:
- scoring for regular frames, spares, strikes, and tenth-frame bonus rolls
- validation of invalid inputs (negative rolls, rolls greater than 10)
- validation of frame constraints (cannot exceed 10 pins per frame)
- prevention of invalid game states (rolling after game is over)

## Test Coverage

Additional custom tests were added, including:
- spare and strike in the tenth frame
- invalid rolls (negative and greater than 10)
- invalid frame pin count
- rolling after the game has finished
- invalid extra rolls in the tenth frame
- mixed game scenario

## Running the project

To run the tests:

```bash
mvn clean test