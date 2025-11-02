import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360, frameHeight = 640;

    int playerWidth = 34, playerHeight = 24;

    int playerStartPosX = (frameWidth / 4) - (playerWidth / 2);
    int playerStartPosY = (frameHeight / 2) - (playerHeight / 2);

    // Atribut posisi dan ukuran pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image bird;
    Player player;

    // list pipa dan gambarnya
    Image lowerPipeImage, upperPipeImage;
    ArrayList<Pipe> pipes;

    // Sound Effects
    Audio scoreEarnedSound, flapSound, deathSound;

    Timer gameLoop;
    Timer pipesCooldown;

    int gravity = 1;

    int pipeVelocityX = -2;

    boolean isPlaying = false;

    // Constructor
    public Logic() {
        bird = new ImageIcon(getClass().getResource("assets/flappyremake/textures/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, bird);

        this.scoreEarnedSound = new Audio("assets/flappyremake/sounds/point.wav");
        this.flapSound = new Audio("assets/flappyremake/sounds/wing.wav");
        this.deathSound = new Audio("assets/flappyremake/sounds/die.wav");

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/flappyremake/textures/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/flappyremake/textures/upperPipe.png")).getImage();

        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                placePipes();
            }
        });
        gameLoop = new Timer(1000 / 60, this);
    }

    public void startLogic() {
        isPlaying = true;
        if (!pipesCooldown.isRunning()) {
            // Clear existing pipes
            pipes.clear();

            // Start the pipe spawner
            pipesCooldown.start();
        }

        if (!gameLoop.isRunning()) {
            // Reset player's position
            player.setScore(0);
            player.setVelocityY(0);
            player.setPosX(playerStartPosX);
            player.setPosY(playerStartPosY);

            // Enable score text
            view.getScoreLabel().setVisible(true);
            view.setScore("0");

            // Disable game over test
            view.setGameoverTexts(false);

            // Start the game
            gameLoop.start();
        }
    }

    public void stopLogic() {
        pipesCooldown.stop();
        gameLoop.stop();
        isPlaying = false;
        this.deathSound.play();
        view.setGameoverTexts(true);
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public Player getPlayer() {
        return player;
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - (pipeHeight / 4) - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth,
                                pipeHeight, upperPipeImage, -1); // For 2D, Y direction up is negative
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth,
                pipeHeight, lowerPipeImage, 1); // For 2D, Y direction down is positive
        pipes.add(lowerPipe);
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean checkCollision() {
        // Check player collision with upper and lower JFrame border
        if (player.getPosY() <= 0 || player.getPosY() >= (frameHeight - playerHeight)) {
            return true;
        }

        // Check player collision with pipes
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

            if (playerRect.intersects(pipeRect)) {
                return true;
            }

        }
        return false;
    }

    public void checkPassedPipe() {
        // First pipe (most-left)
        if (!pipes.isEmpty()) {
            Pipe firstPipe = pipes.getFirst();

            if (!firstPipe.isPassed() && firstPipe.getDirection() == -1 && player.getPosX() > firstPipe.getPosX()*2) {
                firstPipe.setPassed(true);
                player.setScore(player.getScore() + 1);
                scoreEarnedSound.play();
                view.setScore(Integer.toString(player.getScore()));
                System.out.println("Player earned score! Score is now " + player.getScore());
            }
        }
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));
        player.setPosY(Math.min(player.getPosY(), frameHeight-playerHeight));

        checkPassedPipe();

        if (checkCollision()) {
            stopLogic();
        } else {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                // Periksa apakah posisi pipa masih terlihat di layar
                if (pipe.getPosX() > -pipe.getWidth()) {
                    pipe.setPosX(pipe.getPosX() + pipeVelocityX);
                } else { // Delete jika posisi pipa sudah offscreen
                    System.out.println("Pipe #" + i + " deletion");
                    pipes.remove(pipe);
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) {
            view.repaint();
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("keyPressed: " + e.getKeyChar());

        if (e.getKeyCode() == KeyEvent.VK_SPACE && isPlaying) {
            player.setVelocityY(-10);
            flapSound.play();
        } else if (e.getKeyCode() == KeyEvent.VK_R && !isPlaying) {
            startLogic();
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
