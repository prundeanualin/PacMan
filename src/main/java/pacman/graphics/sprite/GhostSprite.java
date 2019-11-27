package pacman.graphics.sprite;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.Ghost;

public class GhostSprite extends Sprite<Ghost>{

    @Override
    public void draw(@NotNull Ghost entity, @NotNull GraphicsContext g, @NotNull Style style,
                     double t) {

        Group root = new Group();
        Arc arc= createArc(ArcType.CHORD, 80, 100, style.getBackgroundColor());
        root.getChildren().addAll(arc);
    }

    public static Arc createArc(ArcType arcType, double x, double y, Color color){
        Arc arc= new Arc();
        arc.setCenterX(x);
        arc.setCenterY(y);
        arc.setRadiusX(30);
        arc.setRadiusY(40);
        arc.setStartAngle(0);
        arc.setLength(180);
        arc.setFill(color);
        arc.setStroke(color);
        arc.setType(arcType);
        return arc;
    }
}
