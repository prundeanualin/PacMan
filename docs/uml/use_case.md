## Use Case Descriptions.
#### Authenticate:
- The player can fill in a username and password.
- The player can either select log in, or register, which correspond to the use cases of the same names.

#### Log in:
- If the username/password combination was registered in the database, the player succesfully logs in and is shown the main menu.
- If this is not the case, authentication fails, corresponding to the 'Fail Authentication' use case, including a notification of this.

#### Register:
- If the username was already registered in the database, authentication fails, corresponding to the 'Fail Authentication' use case, including a notification of this.
- If either the username or password were left empty, authentication will also fail.
- If the username was not already taken, the username/password combination is registered in the database, allowing for future log ins. The player is notified registering was succesful.

#### Fail Authentication:
- If the player tries to log in, but the username/password combination was not registered in the database, authentication fails.
- If the player tries to register, but the username is already registered in the database, authentication also fails.
- If the player tries to register with empty username or password fields, authentication will also fail.
- The player is notified his log in / register request failed.

#### Start Game:
- If the player is in the main menu, he can press the start game button. This will show him the board with the ghosts, pellets, walls and pacman.
- The 4 ghosts will move around the board according to the 'Ghost behavioral definitions' below.

#### Move:
- If the player has started the game, he can use his keyboard (a,w,s,d) to move pacman around the board (left,up,down,right).
- This can only be done if the square he tries to move to is not a wall.
- If he tries to move outside the board, wraparound is used, which means it is treated as if he tries to move to the tile on the other side of the board.

#### Collide with Pellet:
- If the player moves to a tile where a pellet is present, the Pellet is removed (eaten), and the 'Increase Score' use case is applicable.
- If this is the last pellet, the 'Win Game' case is also applicable.

#### Increase Score:
- If the 'Collide with Pellet' use case is applicable, the player's score is increased by a predefined amount (eg. 10).

#### Win Game:
- If the player collides with the last pellet on the board, the game is won and the player is notified of this, and the 'End Game' use case is applicable.

#### End Game:
- If the game is either lost or won, the player and ghosts can no longer move, and the 'View Leaderboard' use case is applicable.

#### View Leaderboard:
- If the game is ended, the player is shown the top 5 scores that were achieved, along with the names of the user's those scores belong to.
- The 'Save Score' use case is applicable

#### Save Score
- The player can save his score to the database, along with a name of his choosing.

#### Collide with Ghosts
- If the player moves onto a ghost (or the reverse) pacman is removed ('killed') and the game is ended, according to the 'End Game' use case.

---
## Ghost behavioral definitions.
_The ghosts have 3 modes of behavior, which are listed below._
#### Chase mode:
##### _Blinky_:
- Blinky tries to collide with pacman (shortest path).
##### _Inky_:
- Inky tries to go to the tile that is exactly opposite from him, of the tile 2 tiles ahead of pacman.
##### _Pinky_:
- Pinky tries to move to the tile a few tiles(4) ahead of pacman.
##### _Clyde_:
- if clyde is more than 8 tiles away from pacman, he uses pinkys behavior.
- if clyde is closer, he uses his scatter behavior.
#### Scatter mode:
- the ghosts each try to move to 'their corner' of the maze, circling around there.
#### Frightened mode:
- the ghosts keep moving forward, and choose random directions at intersections.