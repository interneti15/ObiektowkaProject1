import javax.swing.*;
import java.awt.*;

public class BattlePanel extends JPanel {
    SimulationEngine engine;

    public BattlePanel(SimulationEngine engine){
        this.engine = engine;
        //tutaj dodajemy jednostki na razie

        Knight knight = new Knight(new Coordinates(100,100), Color.green);
        engine.addNewSimulationObject(knight);
        Knight knight2 = new Knight(new Coordinates(100,100), Color.green);
        engine.addNewSimulationObject(knight2);
        Knight knight3 = new Knight(new Coordinates(100,100), Color.green);
        engine.addNewSimulationObject(knight3);

        Archer archer = new Archer(new Coordinates(600,600), Color.cyan);
        //archer.canMove = false;
        archer.range *= 1;
        engine.addNewSimulationObject(archer);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
//        RenderingHints hints = new RenderingHints(
//                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
//        );
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

