import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
            window.setVisible(true);
        });

    }
}