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

    /**
     * Checks if the level was won.
     * @return False iff there are pellets left in the level
     */
    public boolean levelWon() {
        return board.pellets.size() == 0 && pacMan.isAlive();
    }

    public Set<Ghost> getGhosts() {
        return ghosts;
    }

    public Set<Pellet> getPellets() {
        return pellets;
    }

    /**
     * Checks if PacMan was hit and therefore should lose a life.
     * @return whether PacMan was hit
     */
    public boolean wasPacManHit() {
        return !pacMan.isAlive();
    }

    /**
     * Revives the player.
     */
    public void revivePlayer() {
        pacMan.setAlive(true);
    }

    public boolean eatBigOne() {
        return pacMan.pumpedWithPower();
    }

}
