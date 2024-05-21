import javax.swing.*;
import java.util.ArrayList;

public class Window extends JFrame {
    public Window(){
        SimulationEngine engine = new SimulationEngine();
        setTitle("Battle Simulation");
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BattlePanel battlePanel = new BattlePanel(engine);
        add(battlePanel);

        Timer timer = new Timer(10, e -> {
           engine.tick();
           battlePanel.drawSimulation();
            ArrayList<SimulationObject> lista = engine.DEBUG_getObjectsList();
            for (SimulationObject obj: lista){

                System.out.printf("ID: %d Type: %s X: %f, Y: %f, Health: %f \n", ((Unit)obj).ID,obj.getClass().getName(), ((Unit)obj).coordinates.x, ((Unit)obj).coordinates.y, ((Unit)obj).health);
            }
            engine.DEBUG_refreshSimpleList();
            System.out.println(SimulationEngine.simpleSimulationObjectList.size());
        });
        timer.start();


    }
}
