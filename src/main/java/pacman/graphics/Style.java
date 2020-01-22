package pacman.graphics;

import javafx.scene.paint.Color;

/**
 * Represents the style in which the game is rendered.
 */
public enum Style {

    /**
     * Classic style: like the original PacMan.
     */
    CLASSIC("#FFFF00", "#9DDE04", "#1818FF", "#000000",
            "#FF0000", "#FF99CC",
            "#33FFFF", "#FFCC33", "#F8B090",
            "#E08F02", "#DEDEFF");

    private Color pacmanColour;
    private Color pumpedColour;
    private Color wallColour;
    private Color backgroundColor;
    private Color blinkyColour;
    private Color pinkyColour;
    private Color drunkyColor;
    private Color sneakyColor;
    private Color pelletColour;
    private Color powerPelletColor;
    private Color textColour;

    Style(String pacmanColour, String pumpedColour, String wallColour,
          String backgroundColor, String blinkyColour,
          String pinkyColour, String drunkyColor, String sneakyColor, String pelletColour,
          String powerPelletColour, String textColour) {
        this.pacmanColour = Color.web(pacmanColour);
        this.pumpedColour = Color.web(pumpedColour);
        this.wallColour = Color.web(wallColour);
        this.backgroundColor = Color.web(backgroundColor);
        this.blinkyColour = Color.web(blinkyColour);
        this.pinkyColour = Color.web(pinkyColour);
        this.drunkyColor = Color.web(drunkyColor);
        this.sneakyColor = Color.web(sneakyColor);
        this.pelletColour = Color.web(pelletColour);
        this.powerPelletColor = Color.web(powerPelletColour);
        this.textColour = Color.web(textColour);
    }

    /**
     * Gets the colour of PacMan.
     * @return The colour PacMan has
     */
    public Color getPacmanColour() {
        return pacmanColour;
    }

    /**
     * Gets the colour of the walls.
     * @return The colour the walls have
     */
    public Color getWallColour() {
        return wallColour;
    }

    /**
     * Gets the colour of the background.
     * @return The colour the background has
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Gets the colour of Blinky.
     * @return The colour Blinky has
     */
    public Color getBlinkyColour() {
        return blinkyColour;
    }

    /**
     * Gets the colour of Pinky.
     * @return The colour Pinky has
     */
    public Color getPinkyColour() {
        return pinkyColour;
    }

    /**
     * Gets the color of Drunky.
     * @return The color Drunky has
     */
    public Color getDrunkyColor() {
        return drunkyColor;
    }

    /**
     * Gets the color of Sneaky.
     * @return The color Sneaky has
     */
    public Color getSneakyColor() {
        return sneakyColor;
    }

    /**
     * Gets the colour of the pellets.
     * @return The colour the pellets have
     */
    public Color getPelletColour() {
        return pelletColour;
    }

    /**
     * Gets the colour of the text.
     * @return the colour the text has
     */
    public Color getTextColour() {
        return textColour;
    }

    public Color getPowerPelletColor() {
        return powerPelletColor;
    }

    public Color getPumpedColour() {
        return pumpedColour;
    }
}
