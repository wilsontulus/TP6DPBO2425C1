import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;

public class MainMenu extends JPanel {
    private Logic logic;

    private int width = 360;
    private int height = 640;

    private Image backgroundImage;
    private Image bird;

    private Font gameFont;

    private JLabel titleText;
    private JButton startButton, exitButton;

    private Timer menuAnim;
    private View display;

    private int count = 40;
    private int floatSpeed = 1;

    public MainMenu(Logic logic, View display) {
        // Declare logic and font
        this.logic = logic;
        this.display = display;
        this.gameFont = display.getGameFont();

        // Resize and make Transparent main menu
        setLayout(null);
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(0, 0, 0, 0));

        // Declaration of title

        this.titleText = new JLabel("Flappy Bird", SwingConstants.CENTER);
        titleText.setBounds((width - 340) / 2, 210, 340, 48);
        titleText.setFont(this.gameFont);
        titleText.setLayout(null);

        // Declaration of start button
        this.startButton = new JButton("Play");
        startButton.setLayout(null);

        // Remove unnecessary parts
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);

        // Set position
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setBounds((width - 250) / 2, 368, 250, 48);
        startButton.setFont(this.gameFont.deriveFont(18f));

        // Declaration of exit button
        this.exitButton = new JButton("Exit");
        exitButton.setLayout(null);

        // Remove unnecessary parts
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);

        // Set position
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.setBounds((width - 250) / 2, 416, 250, 48);
        exitButton.setFont(this.gameFont.deriveFont(18f));

        // Put the player position at the center of window
        logic.getPlayer().setPosX((width / 2) - (logic.getPlayer().getWidth() / 2));

        // Action listener for button click, which will disable the main menu and start the game
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menuAnim.stop();
                setVisible(false);
                logic.startLogic();
            }
        });

        // Action listener for quit button, which will exit the game
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        // Implement main menu animations
        this.menuAnim = new Timer(1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                count++;
                if (count > 80) {
                    floatSpeed = -floatSpeed;
                    count = 0;
                }

                logic.getPlayer().setPosY(logic.getPlayer().getPosY() + floatSpeed);
                if (display != null) {
                    display.repaint();
                }
            }
        });

        add(titleText);
        add(startButton);
        add(exitButton);
        menuAnim.start();
    }

    public void fadeAnim(boolean isFadeOut) {
        try {
            if (isFadeOut) {
                for (int i = 255; i > 0; i--) {
                    setBackground(new Color(0, 0, 0, i));
                    Thread.sleep(1000 / 255);
                }
            } else {
                for (int i = 0; i < 255; i++) {
                    setBackground(new Color(0, 0, 0, i));
                    Thread.sleep(1000 / 255);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
