import javax.swing.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
            window.setVisible(true);
        });

    }

}