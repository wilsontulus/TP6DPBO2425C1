import java.awt.*;

public class Pipe {
    private int posX, posY;
    private int width, height;
    private Image image;
    private int direction;
    boolean passed;

    public Pipe(int posX, int posY, int width, int height, Image image, int direction) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.direction = direction;
        this.passed = false;
    }

    // Getter
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

    // Setter

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
