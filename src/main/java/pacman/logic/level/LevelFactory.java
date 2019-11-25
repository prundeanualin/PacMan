package pacman.logic.level;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;
import pacman.logic.entity.Ghost;
import pacman.logic.entity.PacMan;
import pacman.logic.entity.Pellet;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class LevelFactory {

    private static final String DEFAULT_LEVEL_DIRECTORY = "/resources/levels/";

    private MapParser mapParser;

    public LevelFactory() {
        this(DEFAULT_LEVEL_DIRECTORY);
    }

    public LevelFactory(String levelDirectory) {
        this.mapParser = new MapParser(levelDirectory);
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // known bug of pmd with foreach loops.
    public @NotNull Level createLevel(String levelName) {
        Board board = mapParser.parseMap(levelName);

        // parseMap should ensure that there is a PacMan
        PacMan pacMan = null;
        Set<Ghost> ghosts = new HashSet<>();
        Set<Pellet> pellets = new HashSet<>();
        for (Entity entity : board.getEntities()) {
            if (entity instanceof PacMan) {
                pacMan = (PacMan) entity;
            } else if (entity instanceof Ghost) {
                ghosts.add((Ghost) entity);
            } else if (entity instanceof Pellet) {
                pellets.add((Pellet) entity);
            }
        }

        return new Level(board, pacMan, ghosts, pellets);
    }

}
