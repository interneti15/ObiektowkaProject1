import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel{
    SimulationEngine engine;
    Boolean startSimulation;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    public ButtonPanel(SimulationEngine engine, BattlePanel battlePanel){
        this.engine = engine;
        setStartSimulation(false);
        setBackground(Color.LIGHT_GRAY);
        setBounds(0,550,775,150);
        setLayout(null);


        button1 = new JButton();
        button1.setText("<-");
        button1.addActionListener(e -> {
            setStartSimulation(false);
            if (SimulationEngine.getTickCount() > 1){
                SouvenirHandler.loadDataFromMemory(this.engine,1);
                battlePanel.drawSimulation();
            }
        });
        button1.setBounds(25,25, 100,100);
        add(button1);

        button2 = new JButton();
        button2.setText("Stop");
        button2.addActionListener(e -> {
            setStartSimulation(false);
        });
        button2.setBounds(150,25, 100,100);
        add(button2);

        button3 = new JButton();
        button3.setText("Start");
        button3.addActionListener(e -> {
            setStartSimulation(true);
        });
        button3.setBounds(275,25, 100,100);
        add(button3);

        button4 = new JButton();
        button4.setText("Random");
        button1.addActionListener(e -> {

        });
        button4.setBounds(400,25, 100,100);
        add(button4);

        button5 = new JButton();
        button5.setText("Load-file");
        button5.addActionListener(e -> {
            this.setStartSimulation(false);
            SouvenirHandler.loadDataFromFile(this.engine);
            battlePanel.drawSimulation();

        });
        button5.setBounds(525,25, 100,100);
        add(button5);

        button6 = new JButton();
        button6.setText("Save-file");
        button6.addActionListener(e -> {
            SouvenirHandler.saveToFile();
        });
        button6.setBounds(650,25, 100,100);
        add(button6);

    }
    public void setStartSimulation(Boolean x){
        this.startSimulation = x;
    }
//    public Boolean showStartSimulation(){
//        return startSimulation;
//    }
}
