import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

public class App {
    protected static void main(String[] args) {

        // Declare window resolutions
        int resX = 360, resY = 640;

        // Enable OpenGL acceleration for Linux, *BSD, and MacOS, windows has Direct3D acceleration enabled by default
        String osName = System.getProperty("os.name");
        if (osName.contains("Linux") || osName.contains("Mac OS X") || osName.contains("BSD")) {
            try {
                // Env for OpenGL acceleration
                System.setProperty("sun.java2d.opengl", "True");

                // Apply system LookAndFeel if available
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException |
                     ClassNotFoundException e) {
                System.out.println("Your system does not support OpenGL and/or UILookAndFeel. CPU renderer may be used.");
            }
        }

        // Set up constructed var objects
        JFrame frame = new JFrame();
        Logic logic = new Logic();
        View display = new View(logic);
        MainMenu mainMenu = new MainMenu(logic, display);

        // Set window title
        frame.setTitle("Flappy Bird Remake");

        // Set window size
        frame.setSize(resX, resY);

        // Put the window at the center of the screen
        frame.setLocationRelativeTo(null);

        // Currently not resize-able
        frame.setResizable(false);

        // Change default close operation for close button to make it not run as background process
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set logic to viewer
        logic.setView(display);

        // Initialize viewer object
        frame.add(display);
        frame.pack();

        // Add main menu to viewport frame
        display.add(mainMenu);

        // Show the window
        frame.setVisible(true);

        // Detect window closing
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
    }
}
