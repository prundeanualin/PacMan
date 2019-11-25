package pacman.gui.graphics;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for drawing the game board with everything on it.
 *
 * @author Ruben
 */
// entities has access methods (though PMD does not recognize them), additionally class is not a bean.
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class boardCanvas extends Canvas {

    /**
     * The style to draw in.
     */
    private Style drawStyle = Style.CLASSIC;

    /**
     * The entities that should be drawn.
     */
    private List<Entity> entities = new ArrayList<Entity>();

    /**
     * Creates a new board canvas with specified dimensions.
     *
     * @param width The width of the canvas in pixels
     * @param height The height of the canvas in pixels
     */
    public boardCanvas(int width, int height) {
        super(width, height);

        // Starts a render loop
        final long start = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long t = now - start;
                draw(t / 1E9);
            }
        };
        timer.start();
    }

    /**
     * Clears the canvas with the background colour.
     */
    private void clear() {
        Color bg = drawStyle.getBackgroundColor();
        getGraphicsContext2D().setFill(new Color(bg.getRed(), bg.getGreen(), bg.getBlue(), 1.0));
        getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Draws everything on the canvas.
     *
     * @param t The time since started in seconds.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // known bug of pmd with foreach loops.
    public void draw(double t) {
        clear();
        for (Entity e : entities) {
            getGraphicsContext2D().translate(e.getX(), e.getY());
            e.getSprite().draw(e, getGraphicsContext2D(), drawStyle, t);
            getGraphicsContext2D().setTransform(new Affine());
        }
    }

    /**
     * Sets the style that is used to draw with.
     *
     * @param drawStyle The new draw style
     */
    public void setDrawStyle(@NotNull Style drawStyle) {
        this.drawStyle = drawStyle;
    }

    /**
     * Gets the style that is used to draw with.
     * @return The current draw style
     */
    public Style getDrawStyle() {
        return drawStyle;
    }

    /**
     * Adds an entity to the list of entities that will be drawn.
     * @param entity The entity to add
     */
    public void addEntity(@NotNull Entity entity) {
        this.entities.add(entity);
    }

    /**
     * Removes an entity from the list of entities that will be drawn.
     * @param entity The entity to remove
     */
    public void removeEntity(@NotNull Entity entity) {
        this.entities.remove(entity);
    }

}
