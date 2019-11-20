import javafx.animation.AnimationTimer;
import javafx.scene.layout.VBox;
import pacman.gui.graphics.BoardCanvas;
import pacman.gui.graphics.Entity;
import pacman.gui.graphics.PacmanSprite;
import pacman.gui.graphics.PelletSprite;

public class TestPane extends VBox {

    public TestPane() {
        BoardCanvas canvas = new BoardCanvas(800, 800);
        getChildren().add(canvas);

        Entity pacman = new Entity(400, 400, new PacmanSprite());
        pacman.setDx(1);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                pacman.update();
            }
        }.start();

        canvas.addEntity(new Entity(430, 400, new PelletSprite()));
        canvas.addEntity(new Entity(450, 400, new PelletSprite()));
        canvas.addEntity(new Entity(470, 400, new PelletSprite()));
        canvas.addEntity(new Entity(490, 400, new PelletSprite()));
        canvas.addEntity(new Entity(510, 400, new PelletSprite()));
        canvas.addEntity(new Entity(530, 400, new PelletSprite()));
        canvas.addEntity(pacman);
    }

}
