package pacman.logic.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class MapParser {

    private File levelDirectory;

    public MapParser(String levelDirectory) {
        this.levelDirectory = new File(levelDirectory);
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
        try (Scanner scanner = new Scanner(new File(levelDirectory + levelName + ".txt"))) {
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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board.setSquare(parseSquare(map[j][i]), i, j);
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

    private @NotNull Square parseSquare(char squareChar) {
        return new Square(); // TODO
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
