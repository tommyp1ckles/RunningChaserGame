	import javax.swing.JFrame;

import madkit.kernel.Agent;
import madkit.kernel.Madkit;
import madkit.kernel.Madkit.Option;
import madkit.kernel.Message;
import madkit.message.StringMessage;

public class RunnerAgent extends Agent
{
	private static final int RUNNERCLOCK = 1000;
	private Map m;
    private int currentCity;
    private int size;
    JFrame frame;
    private RunnerChaserCommunication comm;
    protected void activate() {
        /*
         * createGroupIfAbsent(MarketOrganization.COMMUNITY,MarketOrganization.CLIENT_GROUP,true,null);
		createGroupIfAbsent(MarketOrganization.COMMUNITY,MarketOrganization.PROVIDERS_GROUP,true,null);
		requestRole(MarketOrganization.COMMUNITY,MarketOrganization.CLIENT_GROUP, MarketOrganization.BROKER_ROLE,null);
		requestRole(MarketOrganization.COMMUNITY,MarketOrganization.PROVIDERS_GROUP, MarketOrganization.BROKER_ROLE,null);
         */
    	createGroupIfAbsent(RunnerChaserCommunication.COMMUNITY, RunnerChaserCommunication.RUNNER_NPC_GROUP, true, null);
    	requestRole(RunnerChaserCommunication.COMMUNITY, RunnerChaserCommunication.RUNNER_NPC_GROUP, 
    			RunnerChaserCommunication.RUNNER_ROLE);
        size = 6;
    	City cities[] = new City[size];
        cities[0] = new City(0);
        cities[0].setLocation(100, 100);
        cities[0].setName("Halifax");
        cities[1] = new City(1);
        cities[1].setLocation(200, 500);
        cities[1].setName("San Francisco");
        cities[2] = new City(2);
        cities[2].setLocation(800, 100);
        cities[2].setName("Wolfville");
        cities[3] = new City(3);
        cities[3].setLocation(500, 100);
        cities[3].setName("Toronto");
        cities[4] = new City(4);
        cities[4].setLocation(700, 500);
        cities[4].setName("Los Angeles");
        cities[5] = new City(5);
        cities[5].setName("New York");
        cities[5].setLocation(750, 350);
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
        NPCAgent npc[] = new NPCAgent[5];
        ChaserAgent chaser = new ChaserAgent(m, 5);
        npc[0] = new NPCAgent(m.getCity(0), m);
        npc[1] = new NPCAgent(m.getCity(1), m);
        npc[2] = new NPCAgent(m.getCity(2), m);
        npc[3] = new NPCAgent(m.getCity(3), m);
        npc[4] = new NPCAgent(m.getCity(4), m);
        /*Launch all other agents here */
        for (int i = 0; i < 2; i++) {
        	launchAgent(npc[i], true);
        }
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
            broadcastMessage(
            		comm.COMMUNITY,
            		m.getCity(currentCity).getName(),
            		comm.NPC_ROLE,
            		new StringMessage(m.getCity(nextCity).getName()));
            currentCity = nextCity;
            m.setRunnerLocation(currentCity);
            m.refreshMap();
    	}
    }
    /*
     * This is where all the agents are launched from!
     */
    public static void main(String[] args) {
        //The RunnerAgent is responsible for executing
    	//all other agents.
    	executeThisAgent();
    }
	/*public boolean declareDestination()
	{
		if(actionpoints <= 0)
		{
			return false;
		}
		
		LinkedList<NPCAgents> currentpopulus = new LinkedList();
		currentpopulus = (LinkedList) currentCity.populous;
		nextCity = chooseDestination();
		
		for (NPCAgent a: currentpopulus)
		{
			a.runnerdestination(nextCity);
		}
		
		actionpoints--;
		return true;
	}
	
	public abstract void influencePopulus()
	{
		if(actionpoints <= 0)
		{
			return false;
		}
		
		LinkedList<NPCAgents> currentpopulus = new LinkedList();
		currentpopulus = (LinkedList) currentCity.populous;
		nextCity = chooseDestination();
		
		for (NPCAgent a: currentpopulus)
		{
			a.metrunner();
		}
		
		actionpoints--;
		return true;
	}*/
}
