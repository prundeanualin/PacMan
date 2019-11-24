## Use Case Descriptions
#### Authenticate:
- The Player can fill in his username and password to try authenticate with the database.
- If authentication does not fail, according to the 'Fail Authentication' use case, the player is shown the main menu.

#### Fail Authentication:
- If the Player tries to authenticate, but the username & password combination he gives were not registered in the database, the player is notified he failed to authenticate.

#### Start Game:
- If the player is in the main menu, he can press the start game button. This will show him the board with the ghosts, spellets, walls and pacman.
- The ghosts (4 of them) will move around the board according to the 'Ghost behavioral definitions' below.

#### Move:
- If the player has started the game, he can use his keyboard (a,w,s,d) to move pacman around the board (left,up,down,right).
- This can only be done if the square he tries to move to is not a wall.
- If he tries to move outside the board, wraparound is used, which means it is treated as if he tries to move to the tile on the other side of the board.

#### Collide with Pellet:
- If the player moves to a tile where a pellet is present, the Pellet is removed (eaten), and the 'Increase Score' use case is applicable.
- If this is the last pellet, the 'Win Game' case is also applicable.

#### Increase Score:
If the 'Collide with Pellet' use case is applicable, the players score is increased by a predefined amount (eg. 10).

#### Win Game:
- If the last Pellet was collided with by the player, the game is won and the player is notified of this, and the 'End Game' use case is applicable.

#### End Game:
- If the game is either lost or won, the player can no longer move, nor do the ghosts, and the 'View Leaderboard' use case is applicable.

#### View Leaderboard:
- If the game is ended, the player is shown the top 5 scores that were achieved, along with a name.
- The 'Save Score' use case is applicable

#### Save Score
- The player can save his score to the database, along with a name of his choosing.

#### Collide with Ghosts
- If the player moves onto a ghost (or the reverse) pacman is removed ('killed') and the game is ended, according to the 'End Game' use case.

## Ghost behavioral definitions
- The ghosts have 3 modes of behavior, which are listed below.
### Chase mode:
#### Blinky:
- Blinky tries to collide with pacman (shortest path).
#### Inky:
- Inky tries to go to the tile that is exacly opposite from him, of the tile 2 tiles ahead of pacman.
#### Pinky:
- Pinky tries to move to the tile a few tiles(4) ahead of pacman.
#### Clyde:
- if clyde is more than 8 tiles away from pacman, he uses pinkys behavior.
- if clyde is closer, he uses his scatter behavior.
### Scatter mode:
- the ghosts each try to move to 'their corner' of the maze, circling around there.
### Frightened mode;
- the ghosts keep moving forward, and choose random directions at intersections.