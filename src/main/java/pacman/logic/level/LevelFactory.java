package pacman.logic.level;

import java.util.HashSet;
import java.util.Set;

import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;
import pacman.logic.entity.Ghost;
import pacman.logic.entity.PacMan;
import pacman.logic.entity.Pellet;

/**
 * Factory for creating levels from files.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class LevelFactory {

    /**
     * The default directory where the levels are stored.
     */
    private static final String DEFAULT_LEVEL_DIRECTORY = "/levels/";

    private MapParser mapParser;

    /**
     * Creates a level factory with the default path to the level files.
     */
    public LevelFactory() {
        this(DEFAULT_LEVEL_DIRECTORY);
    }

    /**
     * Creates a level factory where the levels are stored in the specified directory.
     * @param levelDirectory The directory where the levels are stored
     */
    public LevelFactory(String levelDirectory) {
        this.mapParser = new MapParser(levelDirectory);
    }

    /**
     * Reads a level from the file with the specified name.
     * @param levelName The name of the level file
     * @return The level read from the file
     */
    public @NotNull Level createLevel(String levelName) {
        Board board = mapParser.parseMap(levelName);
        return createLevel(board);
    }

    /**
     * Creates a level from the given board.
     * @param board The board to create the level from
     * @return The level created from the board
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // known bug of pmd with foreach loops.
    public @NotNull Level createLevel(@NotNull Board board) {
        PacMan pacMan = null;
        Set<Ghost> ghosts = new HashSet<>();
        Set<Pellet> pellets = new HashSet<>();
        for (Entity entity : board.getEntities()) {
            if (entity instanceof PacMan) {
                if (pacMan != null) {
                    throw new IllegalArgumentException("There can not be multiple PacMen");
                }
                pacMan = (PacMan) entity;
            } else if (entity instanceof Ghost) {
                ghosts.add((Ghost) entity);
            } else if (entity instanceof Pellet) {
                pellets.add((Pellet) entity);
            }
        }
        if (pacMan == null) {
            throw new IllegalArgumentException("There must be a PacMan");
        }

        return new Level(board, pacMan, ghosts, pellets);
    }

}
