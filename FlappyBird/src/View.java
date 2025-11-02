import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private MainMenu mainMenu;
    private Logic logic;

    Image backgroundImage;
    Image bird;

    Font gameFont;
    JLabel scoreLabel, gameOverLabel, pressRLabel;

    public String getBackgroundName() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return "background.png";
        } else {
            return "background_dark.png";
        }
    }

    public View(Logic logic) {
        this.logic = logic;
        this.setLayout(null);

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.CYAN);

        setFocusable(true);
        addKeyListener(logic);

        backgroundImage = new ImageIcon(getClass().getResource("assets/flappyremake/textures/" + getBackgroundName())).getImage();
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
        scoreLabel.setLayout(null);
        scoreLabel.setBounds((width - 240) / 2, 64, 240, 48);
        scoreLabel.setFont(this.gameFont);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setVisible(false);

        this.gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverLabel.setLayout(null);
        gameOverLabel.setBounds((width - 320) / 2, 240, 320, 48);
        gameOverLabel.setFont(this.gameFont.deriveFont(24f));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setVisible(false);

        this.pressRLabel = new JLabel("(R) - Restart Game");
        pressRLabel.setLayout(null);
        pressRLabel.setBounds((width - 300) / 2, 380, 300, 40);
        pressRLabel.setFont(this.gameFont.deriveFont(16f));
        pressRLabel.setForeground(Color.WHITE);
        pressRLabel.setVisible(false);

        this.add(scoreLabel);
        this.add(gameOverLabel);
        this.add(pressRLabel);
    }

    public Font getGameFont() {
        return this.gameFont;
    }

    public JLabel getScoreLabel() {
        return this.scoreLabel;
    }

    public void setScore(String score) {
        this.scoreLabel.setText(score);
    }

    public void setGameoverTexts(boolean state) {
        this.gameOverLabel.setVisible(state);
        this.pressRLabel.setVisible(state);
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
