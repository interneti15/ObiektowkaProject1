import javax.swing.*;
import java.awt.*;

public class BattlePanel extends JPanel {
    SimulationEngine engine;

    public BattlePanel(SimulationEngine engine){
        this.engine = engine;
        //tutaj dodajemy jednostki na razie
        Knight temp = new Knight(new Coordinates(100, 400), Color.blue);
        temp.health = 2000;
        engine.addNewSimulationObject(temp);

        temp = new Knight(new Coordinates(600, 100), Color.pink  );
        engine.addNewSimulationObject(temp);
        temp = new Knight(new Coordinates(100, 600), Color.red);
        engine.addNewSimulationObject(temp);
        temp = new Knight(new Coordinates(300, 800), Color.red);
        engine.addNewSimulationObject(temp);
//        Archer archer = new Archer(new Coordinates(0,0), Color.yellow);
//        engine.addNewSimulationObject(archer);
//        Archer archer1 = new Archer(new Coordinates(500,500), Color.pink);
//        engine.addNewSimulationObject(archer1);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
//        RenderingHints hints = new RenderingHints(
//                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
//        );
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(SimulationObject object : engine.objectsToTick){
            g2d.setColor(object.getTeam());
            g2d.fill(object.getShape());
        }
    }
    public void drawSimulation(){
        repaint();
    }
}

