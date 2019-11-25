package pacman.graphics;

import javafx.scene.paint.Color;

public enum Style {

    CLASSIC("#FFFF00", "#1818FF", "#000000", "#FF0000", "#FF99CC", "#33FFFF", "#FFCC33", "#F8B090",
            "#DEDEFF");

    private Color pacmanColour;
    private Color wallColour;
    private Color backgroundColor;
    private Color blinkyColour;
    private Color pinkyColour;
    private Color inkyColour;
    private Color clydeColour;
    private Color pelletColour;
    private Color textColour;

    Style(String pacmanColour, String wallColour, String backgroundColor, String blinkyColour,
          String pinkyColour, String inkyColour, String clydeColour, String pelletColour,
          String textColour) {
        this.pacmanColour = Color.web(pacmanColour);
        this.wallColour = Color.web(wallColour);
        this.backgroundColor = Color.web(backgroundColor);
        this.blinkyColour = Color.web(backgroundColor);
        this.pinkyColour = Color.web(pinkyColour);
        this.inkyColour = Color.web(inkyColour);
        this.clydeColour = Color.web(clydeColour);
        this.pelletColour = Color.web(pelletColour);
        this.textColour = Color.web(textColour);
    }

    public Color getPacmanColour() {
        return pacmanColour;
    }

    public Color getWallColour() {
        return wallColour;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getBlinkyColour() {
        return blinkyColour;
    }

    public Color getPinkyColour() {
        return pinkyColour;
    }

    public Color getInkyColour() {
        return inkyColour;
    }

    public Color getClydeColour() {
        return clydeColour;
    }

    public Color getPelletColour() {
        return pelletColour;
    }

    public Color getTextColour() {
        return textColour;
    }

}
