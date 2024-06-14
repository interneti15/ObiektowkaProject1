import javax.swing.*;
import java.awt.*;


public class ButtonPanel extends JPanel {
    SimulationEngine engine;

    Boolean startSimulation;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;

    public ButtonPanel(SimulationEngine engine, BattlePanel battlePanel) {
        this.engine = engine;
        setStartSimulation(false);
        setBackground(Color.LIGHT_GRAY);
        setBounds(0, 550, 775, 150);
        setLayout(null);


        button1 = new JButton();
        button1.setText("⬅");
        button1.addActionListener(e -> {
            if (SimulationEngine.getTickCount() > 1) {
                SouvenirHandler.loadDataFromMemory(this.engine, 1);
                battlePanel.drawSimulation();
            }
        });
        button1.setBounds(25, 25, 100, 100);
        add(button1);

        button2 = new JButton();
        button2.setText("Stop");
        button2.addActionListener(e -> {
            setStartSimulation(false);
            button2.setBackground(Color.getHSBColor(0.2972f, 0.52f, 1.0f));
            button3.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));
            button1.setEnabled(true);
            button4.setEnabled(true);
            button5.setEnabled(true);
//            button1.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));
//            button4.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));

        });
        button2.setBounds(150, 25, 100, 100);
        add(button2);

        button3 = new JButton();
        button3.setText("Start");
        button3.addActionListener(e -> {
            setStartSimulation(true);
            button2.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));
            button3.setBackground(Color.getHSBColor(0.2972f, 0.52f, 1.0f));
            button1.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
//            button1.setBackground(Color.GRAY);
//            button4.setBackground(Color.GRAY);
        });
        button3.setBounds(275, 25, 100, 100);
        add(button3);

        button4 = new JButton();
        button4.setText("Random");
        button4.addActionListener(e -> {
            engine.randomPlacement();
            battlePanel.drawSimulation();
        });
        button4.setBounds(400, 25, 100, 100);
        add(button4);

        button5 = new JButton();
        button5.setText("Load-file");
        button5.addActionListener(e -> {
            this.setStartSimulation(false);
            SouvenirHandler.loadDataFromFile(this.engine);
            battlePanel.drawSimulation();

        });
        button5.setBounds(525, 25, 100, 100);
        add(button5);

        button6 = new JButton();
        button6.setText("Save-file");
        button6.addActionListener(e -> {
            SouvenirHandler.saveToFile();
        });
        button6.setBounds(650, 25, 100, 100);
        add(button6);


        button1.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));
        button2.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));
        button3.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));
        button4.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));
        button5.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));
        button6.setBackground(Color.getHSBColor(0.6028f, 0.07f, 0.90f));

        button1.setFont(new Font("Comic Sans", Font.BOLD, 16));
        button2.setFont(new Font("Comic Sans", Font.BOLD, 16));
        button3.setFont(new Font("Comic Sans", Font.BOLD, 16));
        button4.setFont(new Font("Comic Sans", Font.BOLD, 16));
        button5.setFont(new Font("Comic Sans", Font.BOLD, 16));
        button6.setFont(new Font("Comic Sans", Font.BOLD, 16));

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);
        button6.setFocusable(false);

    }

    public void setStartSimulation(Boolean x) {
        this.startSimulation = x;
    }
}