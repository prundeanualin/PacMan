package board;

import java.io.IOException;

import level.MapParser;

public class Board {

    private final transient int width;
    private final transient int height;
    private char[][] map;

    /**.
     * Creating the game board, according to the current level and dimensions
     * @param lvl current level
     * @param width1 width of the canvas screen on which we paint the parsed map
     * @param height1 height of the canvas screen
     */
    public Board(int lvl, int width1, int height1) {
        width = width1;
        height = height1;
        map = parseMap(lvl);
    }

    /**.
     * Uses the mapParser to parse the input txt file into a 2d array of chars, each one
     * corresponding to one sprite in our game
     * @param lvlCount the current level
     * @return 2d array of chars, being the game map
     */
    @SuppressWarnings("PMD")
    public char[][] parseMap(int lvlCount) {
        MapParser mapParser = new MapParser();
        String mapName = "/Level" + lvlCount + ".txt";
        char[][] map = new char[height][width];
        try {
            map = mapParser.parseMap(mapName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
}
