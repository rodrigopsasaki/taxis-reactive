# Taxis
This application is an agent-based system created to simulate a world where there are passengers and drivers. This world follows the some rules:

  - The world is represented by a symmetrical grid
  - On this grid there are blockages, which means insurmountable streets
  - This grid is symmetric, so every movement on the grid has the same constant cost
  - A passenger only requests taxis, and only the closest free taxi should attend that call
  - Taxis can only move in 4 directions (up, down, right and left), which means, there is **no** diagonal movement
  - Taxis en route to passengers or carrying passengers cannot be notified of new ride requests



-------------

## System

- The system was developed in the Java platform, using spring-boot fir injection and reactive web Spring for the REST interface
- There is no persistence whatsoever, every processing happens in memory
- The application is stateful
- The shortest path algorithm used was A* (A-Star), ignoring diagonal movements

------------

## API Endpoints

This system provides a REST API for a user to interact with it, it has 5 endpoints:

 - GET /api/state -> prints out the current state of the grid
 - POST /api/step -> moves 1 step in time on all agents
 - POST /api/restart -> restores the simulation's original state
 - POST /api/taxi -> adds a taxi to the world on the specified coordinate
 - POST /api/passenger -> adds a passenger with a pre-defined route (source/destination)

### API Response sample

All 5 endpoints respond with the current state of the system in HTML format.

```
_x____________________________
_x____3_________xxxxxxx______x
_x______________xxxxxxx______x
_x______________xxxxxxx______x
_x____x___P_____x________1___x
_x____x________xxxxxxxxx_____x
_x____x________xx_____x______x
_x____x____2___x_x____x______x
_x____x________x____x__x______
_x____x________x_____x__x_____
______x________x______x__x____
______x________x_______x______
______x________x______________
______xxxxxxxxxxxxxxxxxxxxx___
_____________x_x______________
_____________xxxxxxxxxxxxxx__x
_______________x______________
_______________x______________
xxxxxxxx_______x______________
_______________x______________
_xxxxxxx_______x______________
______________________________
xxxxxxxx______________________
_______x______________________
_______x______________xxxx_xxx
xxxxxxx_xxxxxxxxxxxxxxxxxxxxxx
______________________x_______
______________________x_______
______________________________
x_____________________________
```

- t = time
- _ = empty street
- x = blocked street
- 1 = empty taxi
- 2 = taxi en route to passenger
- 3 = full taxi
- P = passenger

---------------
