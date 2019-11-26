package pacman.logic;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import pacman.logic.entity.Entity;
import pacman.logic.level.Level;

@SuppressWarnings("PMD.BeanMembersShouldSerialize") // Class is not a bean.
public class Game {

    private Player player;

    private List<Level> levels;
    private int currentLevel;

    private boolean running = false;

    public Game(Player player, List<Level> levels) {
        assert !levels.isEmpty();
        this.player = player;
        this.levels = levels;
        this.currentLevel = 0;
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // known bug of pmd with foreach loops.
    public void update(double dt) {
        if (!running) return;
        for (Entity entity : getLevel().getBoard().getEntities()) {
            entity.update(dt);
        }
        getLevel().getBoard().removeDeadEntities();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public @NotNull Level getLevel() {
        return levels.get(currentLevel);
    }

}
