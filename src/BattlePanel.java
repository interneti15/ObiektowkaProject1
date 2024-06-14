import javax.swing.*;
import java.awt.*;

public class BattlePanel extends JPanel {
    SimulationEngine engine;


    public BattlePanel(SimulationEngine engine){
        this.engine = engine;


        //tutaj dodajemy jednostki na razie
        setBounds(0,0,1000, 550);


        Knight knight = new Knight(new Coordinates(100,100), Color.cyan);
        engine.addNewSimulationObject(knight);
        Knight knight2 = new Knight(new Coordinates(150,100), Color.black);
        engine.addNewSimulationObject(knight2);
        Knight knight3 = new Knight(new Coordinates(200,500), Color.pink);
        engine.addNewSimulationObject(knight3);
        Knight knight4 = new Knight(new Coordinates(150,150), Color.green);
        engine.addNewSimulationObject(knight4);


        Archer archer = new Archer(new Coordinates(600,600), Color.red);
        archer.range *= 1;
        engine.addNewSimulationObject(archer);
        Archer archer1 = new Archer(new Coordinates(300,200), Color.red);
        engine.addNewSimulationObject(archer1);

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

