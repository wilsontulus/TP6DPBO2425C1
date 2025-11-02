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

    Font gameFont;
    JLabel scoreLabel;

    public View(Logic logic) {
        this.logic = logic;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.CYAN);

        setFocusable(true);
        addKeyListener(logic);

        backgroundImage = new ImageIcon(getClass().getResource("assets/flappyremake/textures/background.png")).getImage();
        bird = new ImageIcon(getClass().getResource("assets/flappyremake/textures/bird.png")).getImage();

        // Declare font used for the game
        try {
            this.gameFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("assets/flappyremake/fonts/PressStart2P-Regular.ttf")).deriveFont(Font.PLAIN, 20f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(this.gameFont);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.scoreLabel = new JLabel("0", SwingConstants.CENTER);
        scoreLabel.setBounds((width - 340) / 2, 180, 340, 48);
        scoreLabel.setFont(this.gameFont);
        scoreLabel.setVisible(false);
        this.add(scoreLabel);
    }

    public Font getGameFont() {
        return this.gameFont;
    }

    public JLabel getScoreLabel() {
        return this.scoreLabel;
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
