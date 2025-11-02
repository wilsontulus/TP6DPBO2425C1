import java.awt.*;

public class Pipe {
    // Positions
    private int posX, posY;

    // Sizes
    private int width, height;

    // Image of itself
    private Image image;

    // Which direction it's facing. In 2D, negative (-1) is upwards, positive (1) is downwards
    private int direction;

    // Is it passed by the player?
    boolean passed;

    public Pipe(int posX, int posY, int width, int height, Image image, int direction) {
        // Apply vars
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.direction = direction;

        // Apply default var, this one is false by default
        this.passed = false;
    }

    // Getters
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isPassed() {
        return passed;
    }

    // Setters

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
