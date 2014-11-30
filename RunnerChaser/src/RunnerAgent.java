	import javax.swing.JFrame;

import madkit.kernel.Agent;
import madkit.kernel.Message;
import madkit.message.StringMessage;

public class RunnerAgent extends Agent
{
	private static final int RUNNERCLOCK = 1000;
	private Map m;
    private int currentCity, lastCity;
    private int size;
    JFrame frame;
    private RunnerChaserCommunication comm;
    protected void activate() {
    	
    	createGroupIfAbsent(RunnerChaserCommunication.COMMUNITY, RunnerChaserCommunication.RUNNER_NPC_GROUP, true, null);
    	requestRole(RunnerChaserCommunication.COMMUNITY, RunnerChaserCommunication.RUNNER_NPC_GROUP, 
    			RunnerChaserCommunication.RUNNER_ROLE);
        size = 8;
        lastCity = -1;
    	City cities[] = new City[size];
        cities[0] = new City(0);
        cities[0].setLocation(50, 50);
        cities[0].setName("Halifax");
        cities[1] = new City(1);
        cities[1].setLocation(150, 450);
        cities[1].setName("San Francisco");
        cities[2] = new City(2);
        cities[2].setLocation(750, 50);
        cities[2].setName("Wolfville");
        cities[3] = new City(3);
        cities[3].setLocation(350, 50);
        cities[3].setName("Toronto");
        cities[4] = new City(4);
        cities[4].setLocation(650, 450);
        cities[4].setName("Los Angeles");
        cities[5] = new City(5);
        cities[5].setName("New York");
        cities[5].setLocation(700, 300);
        cities[6] = new City(6);
        cities[6].setName("Las Vegas");
        cities[6].setLocation(250, 550);
        cities[7] = new City(7);
        cities[7].setName("Miami");
        cities[7].setLocation(900, 250);
        cities[7].addAdjCity(cities[4]);
        cities[7].addAdjCity(cities[2]);
        cities[6].addAdjCity(cities[4]);
        cities[6].addAdjCity(cities[1]);
        cities[3].addAdjCity(cities[0]);
        cities[3].addAdjCity(cities[1]);
        cities[0].addAdjCity(cities[1]);
        cities[2].addAdjCity(cities[3]);
        cities[5].addAdjCity(cities[0]);
        cities[5].addAdjCity(cities[1]);
        cities[5].addAdjCity(cities[2]);
        cities[4].addAdjCity(cities[5]);
        cities[4].addAdjCity(cities[1]);
        m = new Map(size);
        for (int i = 0; i < size; i++) {
        	m.addCity(cities[i]);
        	createGroupIfAbsent(
        			comm.COMMUNITY,
        			cities[i].getName(),
        			true, null);
        	requestRole(
        			comm.COMMUNITY,
        			cities[i].getName(),
        			comm.RUNNER_ROLE
        			);
        }
        frame = m.drawMap();
        currentCity = 0;
        /* Create all the other agents */
        NPCAgent npc[] = new NPCAgent[size];
        for (int i = 0; i < size; i++) {
        	npc[i] = new NPCAgent(cities[i], m);
        }
        /*Launch all other agents here */
        for (int i = 0; i < size; i++) {
        	launchAgent(npc[i], false);
        }
        ChaserAgent chaser = new ChaserAgent(m, 0);
        launchAgent(chaser, true);
    }
    
    protected void live() {
    	java.util.Random r = new java.util.Random();
        Message ans;
    	while (true) {
            pause(RUNNERCLOCK);
            logger.info("Runner: Im currently in :" + m.getCity(currentCity).getName());           
            int numberOfPaths = m.getCity(currentCity).getAdjNum();
            int nextCity = m.getCity(currentCity).getAdjCity(r.nextInt(numberOfPaths)).getNum();
            while (nextCity == lastCity || nextCity == m.chaserLocation) {
            	nextCity = m.getCity(currentCity).getAdjCity(r.nextInt(numberOfPaths)).getNum();
            }
            broadcastMessage(
            		comm.COMMUNITY,
            		m.getCity(currentCity).getName(),
            		comm.NPC_ROLE,
            		new StringMessage(m.getCity(nextCity).getName()));
            lastCity = currentCity;
            currentCity = nextCity;
            m.setRunnerLocation(currentCity);
            m.refreshMap();
    	}
    }
    /*
     * This is where all the agents are launched from.
     */
    public static void main(String[] args) {
        /*The RunnerAgent is responsible for executing all other agents.*/
    	executeThisAgent();
    }
}
