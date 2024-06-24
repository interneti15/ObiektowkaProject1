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
        engine.addNewSimulationObject(knight);

        Knight knight2 = new Knight(new Coordinates(70,10), Color.orange);
        //knight.canMove = false;
        engine.addNewSimulationObject(knight2);
        Knight knight3 = new Knight(new Coordinates(10,70), Color.orange);
        //knight.canMove = false;
        engine.addNewSimulationObject(knight3);

        Archer archer = new Archer(new Coordinates(100,700), Color.cyan);
        engine.addNewSimulationObject(archer);
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

