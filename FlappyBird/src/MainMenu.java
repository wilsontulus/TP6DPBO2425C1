import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel {
    private Logic logic;

    private int width = 360;
    private int height = 640;

    private Image backgroundImage;
    private Image bird;
    public MainMenu(Logic logic) {
        // Tentukan kelas logic
        this.logic = logic;

        // Tentukan ukuran panel main menu
        setPreferredSize(new Dimension(width, height));
        //setBackground(Color.CYAN);

        // main menu is ignored for now
        this.setVisible(false);
        logic.startLogic();
    }


}
