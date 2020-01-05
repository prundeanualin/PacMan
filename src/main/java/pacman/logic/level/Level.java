package pacman.logic.level;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Ghost;
import pacman.logic.entity.PacMan;
import pacman.logic.entity.Pellet;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Level {

    private Board board;
    private PacMan pacMan;
    private Set<Ghost> ghosts;
    private Set<Pellet> pellets;

    protected Level(@NotNull Board board, @NotNull PacMan pacMan,
                    @NotNull Collection<? extends Ghost> ghosts,
                    @NotNull Collection<? extends Pellet> pellets) {
        this.board = board;
        this.pacMan = pacMan;
        this.ghosts = new HashSet<>(ghosts);
        this.pellets = new HashSet<>(pellets);
    }

    public Board getBoard() {
        return board;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public Set<Ghost> getGhosts() {
        return ghosts;
    }

    public Set<Pellet> getPellets() {
        return pellets;
    }


    // boolean won is a check for having any remaining pellets
    public boolean checkLevelWon() {
        return pellets.size() == 0;
    }

    /**
     * Checks if the game is lost.
     * @return whether pacman isnt alive
     */
    public boolean checkLevelLost(){
        return !pacMan.isAlive();
    }
}
