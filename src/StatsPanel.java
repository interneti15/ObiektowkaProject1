import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatsPanel extends JPanel {
    ArrayList<JLabel> statistics = new ArrayList<>();
    public StatsPanel(SimulationEngine engine){
        setBackground(Color.gray);
        setBounds(775,585,225,115);
        setLayout(null);



    }
}
