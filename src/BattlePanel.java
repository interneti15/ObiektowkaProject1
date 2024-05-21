import javax.swing.*;
import java.awt.*;

public class BattlePanel extends JPanel {
    SimulationEngine engine;

    public BattlePanel(SimulationEngine engine){
        this.engine = engine;
        //tutaj dodajemy jednostki na razie

        Knight knight = new Knight(new Coordinates(0,0), Color.red);
        knight.canMove = false;
        knight.isImmortal = true;
        engine.addNewSimulationObject(knight);

        Archer archer = new Archer(new Coordinates(500,500),Color.blue);
        archer.range = 1000;
        archer.canMove = false;
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
            g2d.setColor(object.getTeam());
            g2d.fill(object.getShape());
        }
    }
    public void drawSimulation(){
        repaint();
    }
}

