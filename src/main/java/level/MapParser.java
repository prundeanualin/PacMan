package level;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MapParser {


    /**.
     * Check for validity of map, if it has lines of different lengths or if the file doesn't
     * contain any proper lines
     * @param mapText the lines red from the file
     */
    public void checkMapCorectness(List<String> mapText) {
        if (mapText == null || mapText.isEmpty()) {
            throw new RuntimeException("Invalid map format: No existing "
                    + "rows inside the map file");
        }
        int width = mapText.get(0).length();
        if (width == 0) {
            throw new RuntimeException("Invalid map format: Input lines have no characters");
        }
        for (int i = 0; i < mapText.size(); i++) {
            String line = mapText.get(i);
            if (line.length() != width) {
                throw new RuntimeException("Invalid map format: "
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
    @SuppressWarnings("PMD")
    public char[][] parseMap(String mapName) throws IOException {
        char[][] map;
        InputStream inputStream = MapParser.class.getResourceAsStream(mapName);
        if (inputStream == null) {
            throw new RuntimeException("Could not load map from filename");
        }
        try {
            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
            checkMapCorectness(lines);
            int height = lines.size();
            int width = lines.get(0).length();
            map = new char[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    map[i][j] = lines.get(i).charAt(j);
                }
            }
            return map;
        } catch (UnsupportedEncodingException e) {
            Platform.exit();
            e.printStackTrace();
            return null;
        }
    }
}
