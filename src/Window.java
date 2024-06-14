import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Window extends JFrame {

    private JLabel timeLabel;
    public Window(){

        SimulationEngine engine = new SimulationEngine();
        setTitle("Battle Simulation");
        setSize(1014,735);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


        SimulationTime simulationTime = new SimulationTime();
        simulationTime.setLayout(new FlowLayout());
        timeLabel = new JLabel("Time: 0.00");
        timeLabel.setForeground(Color.WHITE);
        simulationTime.add(timeLabel);
        add(simulationTime);
//        simulationTime.repaint(); // repaints the text every tick

        BattlePanel battlePanel = new BattlePanel(engine);
        add(battlePanel);

        ButtonPanel buttonPanel = new ButtonPanel(engine, battlePanel);
        add(buttonPanel);

        StatsPanel statsPanel = new StatsPanel();
        add(statsPanel);

        engine.tick();
        battlePanel.drawSimulation();

        //Creating array lists to store data about team, summary team health and number of units in each team
        ArrayList<Color> teamTable = new ArrayList<>();
        ArrayList<Double> healthTable = new ArrayList<>();
        ArrayList<Integer> unitCountTable = new ArrayList<>();

        AtomicReference<ArrayList<SimplifiedSimulationObject>> objects = new AtomicReference<>(SimulationEngine.simpleSimulationObjectList);

        teamTable.add(0,(objects.get().get(0)).getTeam());

        //Creating a table of teams
        for(SimplifiedSimulationObject object : objects.get()){
            boolean isColorAlreadyInTable = false;
            for(int i = 0; i < teamTable.size(); i++){
                if((object).getTeam().equals(teamTable.get(i))) {
                    isColorAlreadyInTable = true;
                    break;
                }
            }
            if (!isColorAlreadyInTable) teamTable.add((object).getTeam());
        }
        int labelBoundY = 0;

        for (int i = 0; i < teamTable.size(); i++){
            healthTable.add(i,0.0);
            unitCountTable.add(i,0);

            statsPanel.statistics.add(i,new JLabel());
            statsPanel.statistics.get(i).setForeground(teamTable.get(i));
            statsPanel.statistics.get(i).setBounds(10,labelBoundY,200,40);
            statsPanel.add(statsPanel.statistics.get(i));
            labelBoundY += 18;
        }


        System.out.println(teamTable);
        System.out.println(healthTable);
        System.out.println(unitCountTable);


        Timer timer = new Timer(10, e -> {
            objects.set(SimulationEngine.simpleSimulationObjectList);
            if(buttonPanel.startSimulation){
                engine.tick();
                battlePanel.drawSimulation();
                //ArrayList<SimulationObject> lista = engine.DEBUG_getObjectsList();
                ArrayList<SimplifiedSimulationObject> lista = SimulationEngine.simpleSimulationObjectList;

                for(int i = 0; i < teamTable.size(); i++){
                    healthTable.set(i,0.0);
                    unitCountTable.set(i,0);
                }

                for(SimplifiedSimulationObject object : objects.get()){
                    for(int i = 0; i < teamTable.size(); i++){
                        if((object).getTeam().equals(teamTable.get(i)) && !(object.isThisType(SimulationObjectType.ARROW))){
                            healthTable.set(i, healthTable.get(i) + (object).getHealth());
                            unitCountTable.set(i, unitCountTable.get(i) + 1);
                            break;
                        }

                    }
                }
                System.out.println(healthTable);
                System.out.println(unitCountTable);

                for(int i = 0; i < statsPanel.statistics.size(); i++){
                    statsPanel.statistics.get(i).setText("Team health: " + healthTable.get(i) + "   Units: " + unitCountTable.get(i));
                }

                int tickCount = SimulationEngine.getTickCount();
                int seconds = tickCount / 100;
                int milliseconds = tickCount % 100;
                String timeString = String.format("Time: %d.%02d", seconds, milliseconds);
                timeLabel.setText(timeString);
                simulationTime.repaint();

                System.out.printf("TickNow: %d \n",SimulationEngine.getTickCount());
//                for (SimplifiedSimulationObject obj: lista){
//                    break;
//                    System.out.printf("ID: %d Type: %s X: %f, Y: %f, Health: %f LastAtack: %d LastDmg: %d\n", ((Unit)obj).ID, obj.getClass().getName(), ((Unit)obj).coordinates.x, ((Unit)obj).coordinates.y, ((Unit)obj).health, ((Unit)obj).lastAttack, ((Unit)obj).lastDamageTaken);
//                }


                engine.refreshSimpleList();
                System.out.println(SimulationEngine.simpleSimulationObjectList.size());
            }
        });
        timer.start();


    }

}
