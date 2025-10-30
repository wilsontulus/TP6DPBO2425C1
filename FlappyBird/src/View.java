import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private MainMenu mainMenu;
    private Logic logic;

    Image backgroundImage;
    Image bird;

    public View(Logic logic, MainMenu mainMenu) {
        this.logic = logic;
        this.mainMenu = mainMenu;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.CYAN);

        setFocusable(true);
        addKeyListener(logic);

        backgroundImage = new ImageIcon(getClass().getResource("assets/flappyremake/textures/background.png")).getImage();
        bird = new ImageIcon(getClass().getResource("assets/flappyremake/textures/bird.png")).getImage();
    }

    public MainMenu getMainMenu() {
        return this.mainMenu;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        draw(g);
    }

    public void draw(Graphics g) {
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                        player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                            pipe.getWidth(), pipe.getHeight(), null);
            }
        }
    }
}
