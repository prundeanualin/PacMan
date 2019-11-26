package pacman.logic.level;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.sprite.PacmanSprite;
import pacman.graphics.sprite.PelletSprite;
import pacman.graphics.sprite.WallSprite;
import pacman.logic.Direction;
import pacman.logic.entity.Entity;
import pacman.logic.entity.PacMan;
import pacman.logic.entity.Pellet;
import pacman.logic.entity.Wall;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class MapParser {

    private File levelDirectory;

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
     * @param scanner The scanner to read from
     * @return A board parsed from the scanner
     */
    public @NotNull Board parseMap(@NotNull Scanner scanner) {
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
                board.addSquare(parseSquare(map[j][i], i, j));
            }
        }
        return board;
    }

    /**
     * Parses a map from the given string.
     * @param mapString The string to read from
     * @return A board parsed from the string
     */
    public @NotNull Board parseMapFromString(@NotNull String mapString) {
        return parseMap(new Scanner(mapString));
    }

    private @NotNull Square parseSquare(char squareChar, int x, int y) {
        switch (squareChar) {
            case '#':
                return new Square(new Wall(x, y, new WallSprite()));
            case 'P':
                return new Square(new PacMan(x, y, new PacmanSprite()));
            case '.':
                return new Square(new Pellet(x, y, new PelletSprite()));
            default:
                return new Square();
        }
    }

    /**.
     * Check for validity of map, if it has lines of different lengths or if the file doesn't
     * contain any proper lines
     * @param mapText the lines read from the file
     */
    public void checkMapCorrectness(List<String> mapText) {
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
