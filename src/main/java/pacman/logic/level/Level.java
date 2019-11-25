package pacman.logic.level;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pacman.logic.entity.Ghost;
import pacman.logic.entity.PacMan;
import pacman.logic.entity.Pellet;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Level {

    private Board board;
    private PacMan pacMan;
    private Set<Ghost> ghosts;
    private Set<Pellet> pellets;

    protected Level(Board board, PacMan pacMan, Collection<? extends Ghost> ghosts,
                 Collection<? extends Pellet> pellets) {
        this.board = board;
        this.pacMan = pacMan;
        this.ghosts = new HashSet<>(ghosts);
        this.pellets = new HashSet<>(pellets);
    }

}
