package level;

import board.BoardFactory;
import ghosts.GhostFactory;

public class LevelFactory {

    int lvlCount;
    private Level level;
    private BoardFactory board;
    private GhostFactory sprites;


    /**.
     * Creating a factory for generating a level, more like an intermediate
     * testable class for separating the initial setup from creating a new level
     * @param bd boardFactory for creating the specific level board
     * @param sp ghostfactory for creating the ghosts andtheir AI
     */
    public LevelFactory(BoardFactory bd, GhostFactory sp) {
        lvlCount = 1;
        board = bd;
        sprites = sp;
        level = new Level(lvlCount, board, sprites);
    }

    public Level getLevel() {
        return level;
    }

    public void increaseLevel() {
        lvlCount = lvlCount + 1;
        level = new Level(lvlCount, board, sprites);
    }

    public int getLvlCount() {
        return lvlCount;
    }

    public void setLvlCount(int lvlCount) {
        this.lvlCount = lvlCount;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public BoardFactory getBoard() {
        return board;
    }

    public void setBoard(BoardFactory board) {
        this.board = board;
    }

    public GhostFactory getSprites() {
        return sprites;
    }

    public void setSprites(GhostFactory sprites) {
        this.sprites = sprites;
    }
}
