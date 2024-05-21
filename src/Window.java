import javax.swing.*;
import java.io.IOException;
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
            System.out.printf("TickNow: %d \n",SimulationEngine.getTickCount());
            for (SimulationObject obj: lista){
                System.out.printf("ID: %d Type: %s X: %f, Y: %f, Health: %f LastAtack: %d LastDmg: %d\n", ((Unit)obj).ID,obj.getClass().getName(), ((Unit)obj).coordinates.x, ((Unit)obj).coordinates.y, ((Unit)obj).health, ((Unit)obj).lastAttack, ((Unit)obj).lastDamageTaken);
            }
            engine.DEBUG_refreshSimpleList();
            System.out.println(SimulationEngine.simpleSimulationObjectList.size());
//            try {
//                // This line waits for the user to press any key
//                System.in.read();
//            } catch (IOException f) {
//                f.printStackTrace();
//            }

        });
        timer.start();


    }
}
