import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window extends JFrame {
    public Window(){
        Boolean startSimulation = false;

        SimulationEngine engine = new SimulationEngine();
        setTitle("Battle Simulation");
        setSize(1000,735);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        BattlePanel battlePanel = new BattlePanel(engine);
        add(battlePanel);

        ButtonPanel buttonPanel = new ButtonPanel(engine, battlePanel);
        add(buttonPanel);

        StatsPanel statsPanel = new StatsPanel(engine);
        add(statsPanel);

        SimulationTime simulationTime = new SimulationTime();
        add(simulationTime);


        engine.tick();


        battlePanel.drawSimulation();

        //Creating array lists to store data about team, summary team health and number of units in each team
        ArrayList<Color> teamTable = new ArrayList<>();
        ArrayList<Double> healthTable = new ArrayList<>();
        ArrayList<Integer> unitCountTable = new ArrayList<>();

        ArrayList<SimulationObject> objects = engine.DEBUG_getObjectsList();

        teamTable.add(0,((Unit)objects.get(0)).team);

        //Creating a table of teams
        for(SimulationObject object : objects){
            boolean isColorAlreadyInTable = false;
            for(int i = 0; i < teamTable.size(); i++){
                if(((Unit)object).team.equals(teamTable.get(i))) {
                    isColorAlreadyInTable = true;
                    break;
                }
            }
            if (isColorAlreadyInTable == false) teamTable.add(((Unit) object).team);
        }
        int labelBoundY = 0;

        for (int i = 0; i < teamTable.size(); i++){
            healthTable.add(i,0.0);
            unitCountTable.add(i,0);

            statsPanel.statistics.add(i,new JLabel());
            statsPanel.statistics.get(i).setForeground(teamTable.get(i));
            statsPanel.statistics.get(i).setBounds(10,labelBoundY,200,40);
            statsPanel.add(statsPanel.statistics.get(i));
            labelBoundY += 20;
        }


        System.out.println(teamTable);
        System.out.println(healthTable);
        System.out.println(unitCountTable);


        Timer timer = new Timer(10, e -> {
            if(buttonPanel.startSimulation){
                engine.tick();
                battlePanel.drawSimulation();
                ArrayList<SimulationObject> lista = engine.DEBUG_getObjectsList();

                for(int i = 0; i < teamTable.size(); i++){
                    healthTable.set(i,0.0);
                    unitCountTable.set(i,0);
                }

                for(SimulationObject object : objects){
                    for(int i = 0; i < teamTable.size(); i++){
                        if(((Unit)object).team.equals(teamTable.get(i)) && ((Unit)object).getClass() != Arrow.class){
                            healthTable.set(i, healthTable.get(i) + ((Unit)object).health);
                            unitCountTable.set(i, unitCountTable.get(i) + 1);
                            break;
                        }

                    }
                }
                System.out.println(healthTable);
                System.out.println(unitCountTable);

                for(int i = 0; i < statsPanel.statistics.size(); i++){
                    statsPanel.statistics.get(i).setText("Team health: " + healthTable.get(i) + "Units: " + unitCountTable.get(i));
                }



                System.out.printf("TickNow: %d \n",SimulationEngine.getTickCount());
                for (SimulationObject obj: lista){
                    System.out.printf("ID: %d Type: %s X: %f, Y: %f, Health: %f LastAtack: %d LastDmg: %d\n", ((Unit)obj).ID, obj.getClass().getName(), ((Unit)obj).coordinates.x, ((Unit)obj).coordinates.y, ((Unit)obj).health, ((Unit)obj).lastAttack, ((Unit)obj).lastDamageTaken);
                }


                engine.DEBUG_refreshSimpleList();
                System.out.println(SimulationEngine.simpleSimulationObjectList.size());
            }
        });
        timer.start();


    }

}
