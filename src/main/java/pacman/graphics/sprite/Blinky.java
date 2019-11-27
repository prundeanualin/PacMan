package pacman.graphics.sprite;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import org.jetbrains.annotations.NotNull;
import pacman.graphics.Style;
import pacman.logic.entity.Ghost;

public class Blinky {

    public void draw(@NotNull Ghost entity, @NotNull Style style) {

        Group root = new Group();
        Arc arc= createArc(ArcType.CHORD, 80, 100, style.getBlinkyColour());

        QuadCurve quadCurve1= createQuadCurve(50, 101, 65, 101, style.getBackgroundColor());
        QuadCurve quadCurve2= createQuadCurve(65, 101, 80, 101, style.getBackgroundColor());
        QuadCurve quadCurve3= createQuadCurve(80, 101, 95, 101, style.getBackgroundColor());
        QuadCurve quadCurve4= createQuadCurve(95, 101, 110, 101, style.getBackgroundColor());

        Circle circle1= createCircle(70, 60);
        Circle circle2= createCircle(90, 60);
        root.getChildren().addAll(arc, quadCurve1, quadCurve2, quadCurve3, quadCurve4, circle1, circle2);
    }

    public  static  Circle createCircle(double x, double y){
        Circle circle= new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(3);
        circle.setStroke(Color.WHITE);
        circle.setFill(Color.WHITE);
        return circle;
    }

    public static Arc createArc(ArcType arcType, double x, double y, Color color){
        Arc arc= new Arc();
        arc.setCenterX(x);
        arc.setCenterY(y);
        arc.setRadiusX(30);
        arc.setRadiusY(60);
        arc.setStartAngle(0);
        arc.setLength(180);
        arc.setFill(color);
        arc.setStroke(color);
        arc.setType(arcType);
        return arc;
    }

    public static QuadCurve createQuadCurve(double x1, double y1, double x2, double y2, Color color){
        QuadCurve quadCurve= new QuadCurve();
        quadCurve.setStartX(x1);
        quadCurve.setStartY(y1);
        quadCurve.setEndX(x2);
        quadCurve.setEndY(y2);
        quadCurve.setControlX((x1+x2)/2);
        quadCurve.setControlY(63);
        quadCurve.setFill(color);



        return quadCurve;
    }

}
