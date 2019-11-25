package pacman.logic.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

    public @NotNull Board parseMap(@NotNull String levelName) {
        return new Board(0, 0); // TODO
    }

    /**.
     * Check for validity of map, if it has lines of different lengths or if the file doesn't
     * contain any proper lines
     * @param mapText the lines red from the file
     */
    public void checkMapCorectness(List<String> mapText) {
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


    /**.
     * Reading the file representing the map of the game
     * @param mapName the filename
     * @return 2D vector of chars, corresponding to each char in the original file
     * @throws IOException in case the reading goes kaboom
     */
    public char[][] parseToChars(String mapName) throws IOException {
        InputStream inputStream = MapParser.class.getResourceAsStream(mapName);
        if (inputStream == null) {
            throw new RuntimeException("Could not load map from filename");
        }
        try {
            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                    StandardCharsets.UTF_8));
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
            checkMapCorectness(lines);
            int height = lines.size();
            char[][] map = new char[height][]; // NOPMD redefinition necessary
            for (int i = 0; i < height; i++) {
                map[i] = lines.get(i).toCharArray(); // NOPMD redefinition necessary
            }
            return map;
        } catch (UnsupportedEncodingException e) {
            Platform.exit();
            e.printStackTrace();
            return null;
        }
    }
}
