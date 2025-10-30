import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

public class App {
    protected static void main(String[] args) {

        int resX = 360, resY = 640;

        JFrame frame = new JFrame();
        Logic logic = new Logic();
        MainMenu mainMenu = new MainMenu(logic);
        View display = new View(logic, mainMenu);

        // Set window title
        frame.setTitle("Flappy Bird Remake");

        // Set window size
        frame.setSize(resX, resY);

        // Put the window at the center of the screen
        frame.setLocationRelativeTo(null);

        // Tidak dapat di-resize
        frame.setResizable(false);

        // Ubah default agar program ikut berhenti saat window diclose
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set logic to viewer
        logic.setView(display);

        // Initialize viewer object
        frame.add(display);
        frame.pack();

        // Tambahkan main menu ke frame
        display.add(mainMenu);

        // Tampilkan window
        frame.setVisible(true);

        // Deteksi penutupan window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
    }
}
