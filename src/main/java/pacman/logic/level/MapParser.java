package pacman.logic.level;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Blinky;
import pacman.logic.entity.Bottle;
import pacman.logic.entity.PacMan;
import pacman.logic.entity.Pellet;
import pacman.logic.entity.Pinky;
import pacman.logic.entity.PowerPellet;
import pacman.logic.entity.Wall;

/**
 * Parses text to maps ({@link Board}s).
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class MapParser {

    private File levelDirectory;

    /**
     * Creates a map parser that loads a file, reads it and generates a board out of it.
     *
     * @param levelDirectory The directory to read levels from.
     */
    public MapParser(String levelDirectory) {
        try {
            this.levelDirectory = new File(getClass().getResource(levelDirectory).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Platform.exit();
        }

        if (!this.levelDirectory.exists()) {
            throw new IllegalArgumentException("Level Directory does not exist");
        }
        if (!this.levelDirectory.isDirectory()) {
            throw new IllegalArgumentException("Level Directory should be a directory");
        }
    }

    /**
     * Reads the file and parses the map from the contents.
     *
     * @param levelName The name of the level
     * @return A board parsed from the file
     */
    public Board parseMap(@NotNull String levelName) {
        try (Scanner scanner = new Scanner(new File(levelDirectory.getPath() + "/" + levelName
                + ".txt"))) {
            return parseMap(scanner);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Platform.exit();
            return null;
        }
    }

    /**
     * Reads the scanner and parses the map from the contents.
     *
     * @param scanner The scanner to read from
     * @return A board parsed from the scanner
     */
    @NotNull
    public static Board parseMap(@NotNull Scanner scanner) {
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        checkMapCorrectness(lines);

        int width = lines.get(0).length();
        int height = lines.size();
        char[][] map = new char[height][]; // NOPMD redefinition necessary
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray(); // NOPMD redefinition necessary
        }

        Board board = new Board(width, height);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                parseSquare(board, map[j][i], i, j);
            }
        }

        return board;
    }

    /**
     * Parses a map from the given string.
     *
     * @param mapString The string to read from
     * @return A board parsed from the string
     */
    @NotNull
    public static Board parseMapFromString(@NotNull String mapString) {
        return parseMap(new Scanner(mapString));
    }

    private static void parseSquare(@NotNull Board board, char squareChar, int x, int y) {
        Square square = new Square(board, x, y); // NOPMD variable is used
        switch (squareChar) { // NOPMD , default case can not break as it throws an exception.
            case '#':
                new Wall(board, square);
                break;
            case '*':
                new Pellet(board, square);
                break;
            case '+':
                new PowerPellet(board, square);
                break;
            case 'B':
                new Blinky(board, square);
                break;
            case 'b':
                new Bottle(board, square);
                break;
            case 'P':
                new PacMan(board, square);
                break;
            case 'p':
                new Pinky(board, square);
                break;
            case '.':
                break;
            default:
                throw new IllegalArgumentException("Invalid character");
        }
    }

    /**
     * .
     * Check for validity of map, if it has lines of different lengths or if the file doesn't
     * contain any proper lines
     *
     * @param mapText the lines read from the file
     */
    public static void checkMapCorrectness(List<String> mapText) {
        if (mapText == null || mapText.isEmpty()) {
            throw new IllegalArgumentException("Invalid map format: "
                    + "No existing rows inside the map file");
        }
        int width = mapText.get(0).length();
        if (width == 0) {
            throw new IllegalArgumentException("Invalid map format: "
                    + "Input lines have no characters");
        }
        for (int i = 0; i < mapText.size(); i++) {
            String line = mapText.get(i);
            if (line.length() != width) {
                throw new IllegalArgumentException("Invalid map format: "
                        + "Input lines are not of equal width");
            }
        }
    }

}
