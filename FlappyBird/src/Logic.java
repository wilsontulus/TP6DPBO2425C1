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

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    int pipeVelocityX = -2;

    boolean dead = false;

    // Constructor
    public Logic() {
        bird = new ImageIcon(getClass().getResource("assets/flappyremake/textures/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, bird);

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
        dead = false;
        if (!pipesCooldown.isRunning()) {
            pipes.clear();
            pipesCooldown.start();
        }

        if (!gameLoop.isRunning()) {
            player.setVelocityY(0);
            player.setPosY(playerStartPosY);
            gameLoop.start();
        }
    }

    public void stopLogic() {
        pipesCooldown.stop();
        gameLoop.stop();
        dead = true;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - (pipeHeight / 4) - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth,
                                pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth,
                pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean checkCollision() {
        // Cek collision dengan langit dan tanah
        if (player.getPosY() <= 0 || player.getPosY() >= (frameHeight - playerHeight)) {
            return true;
        }

        // Cek collision dengan pipa
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

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));
        player.setPosY(Math.min(player.getPosY(), frameHeight-playerHeight));

        if (checkCollision()) {
            stopLogic();
        } else {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                // Periksa apakah posisi pipa masih terlihat di layar
                if (pipe.getPosX() > -pipe.getWidth()) {
                    pipe.setPosX(pipe.getPosX() + pipeVelocityX);
                } else { // Delete jika posisi pipa sudah offscreen
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

        int currentPosY = player.getPosY();
        //System.out.println("PosY:" + currentPosY);
    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("keyPressed: " + e.getKeyChar());

        if (e.getKeyCode() == KeyEvent.VK_SPACE && !dead) {
            player.setVelocityY(-10);
        } else if (e.getKeyCode() == KeyEvent.VK_R && dead) {
            startLogic();
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
