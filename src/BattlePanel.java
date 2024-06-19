import javax.swing.*;
import java.awt.*;

public class BattlePanel extends JPanel {
    SimulationEngine engine;


    public BattlePanel(SimulationEngine engine){
        this.engine = engine;


        //tutaj dodajemy jednostki na razie
        setBounds(0,0,1000, 550);


        Knight knight = new Knight(new Coordinates(10,10), Color.cyan);
        //knight.canMove = false;
        knight.isImmortal = true;
        engine.addNewSimulationObject(knight);

        Witch witch = new Witch(new Coordinates(300,200), Color.orange);
        //witch.canMove = false;
        engine.addNewSimulationObject(witch);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(SimplifiedSimulationObject object : SimulationEngine.simpleSimulationObjectList){
            g2d.setColor(object.getColor());
            g2d.fill(object.getShape());
        }
    }
    public void drawSimulation(){
        repaint();
    }
}

