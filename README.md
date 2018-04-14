# Test task for R company

This repository contains test task implementation that is required as a
part of employment process. The task itself is simple:

- A RESTful service for money transfers
- No Spring
- Tests included
- Compilable to a single fat jar
- Backing storage has constraints of being handmade, run in memory and 
be ready for concurrent access

## Building

The project is built with gradle, so the process is quite 
straightforward (except for not including wrapper in repo):

```bash
gradle wrapper --gradle-version 4.6
./gradlew clean shadowJar
```

## Launching

```bash
bin/console server
```
