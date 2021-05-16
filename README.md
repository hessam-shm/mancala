# Mancala
##How to run
To run the game simply run the following command from the root of the application
`./mvnw spring-boot:run` then in your browser open `http://localhost:8080`  
In order to change the number of players, pits, and seeds you can change the corresponding values in `application.properties` file.  
Please be aware that while the game theoretically supports any number of players, pits, or seeds (as long as the cumulative number of seeds in a pit or bank does not overflow Java integer max value), but UI only supports up to two players and a reasonably small number of pits.  
##How to play
For a brief description of rules and how to play please refer to [Instruction](./Instruction.md) page.  
If you want to try the game without the convenience of UI(!), you can use the following rest APIs:  
* start a new game with: `http://localhost:8080/mancala/start`
* move with `http://localhost:8080/mancala/move?pitId={id}`
* peek at the game board without making a move: `http://localhost:8080/mancala/peek`  
This is a sample response you get from the above requests:
  ```json
  {"board":
  {"pits":[
    {"index":0,"seeds":6,"player":{"name":"Player 1"}},
    {"index":1,"seeds":6,"player":{"name":"Player 1"}},
    {"index":2,"seeds":6,"player":{"name":"Player 1"}},
    {"index":3,"seeds":6,"player":{"name":"Player 1"}},
    {"index":4,"seeds":6,"player":{"name":"Player 1"}},
    {"index":5,"seeds":6,"player":{"name":"Player 1"}},
    {"index":6,"seeds":0,"player":{"name":"Player 1"}},
    {"index":7,"seeds":6,"player":{"name":"Player 2"}},
    {"index":8,"seeds":6,"player":{"name":"Player 2"}},
    {"index":9,"seeds":6,"player":{"name":"Player 2"}},
    {"index":10,"seeds":6,"player":{"name":"Player 2"}},
    {"index":11,"seeds":6,"player":{"name":"Player 2"}},
    {"index":12,"seeds":6,"player":{"name":"Player 2"}},
    {"index":13,"seeds":0,"player":{"name":"Player 2"}}
  ],
  "players":[
    {"name":"Player 1"},{"name":"Player 2"}
  ]},
  "turn":{"name":"Player 1"},
  "winner":null,
  "message":"Game starts"
  }

##To do
List of features that would be desirable to be added in the future:
* A complete set of tests for more than two-player game.
* Add UI support for more than two players, so the board can form triangle, rectangle, pentagon... based on the number of players.
* Support for non-local multiplayer with websocket
* Some kind of random simulation for testing the game
* User enters the number of players, pits, and seeds in UI before starting the game.
* Support for different variations of the game.
