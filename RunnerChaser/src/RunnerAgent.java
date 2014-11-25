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
    	System.out.println("Activating RunnerAgent");
        City a = new City(0);
        a.setLocation(100, 100);
        a.setName("Halifax");
        City b = new City(1);
        b.setLocation(200, 500);
        b.setName("San Francisco");
        City c = new City(2);
        c.setLocation(800, 100);
        c.setName("Wolfville");
        City d = new City(3);
        d.setLocation(500, 100);
        d.setName("Toronto");
        City e = new City(4);
        e.setLocation(700, 500);
        e.setName("Los Angeles");
        City f = new City(5);
        f.setName("New York");
        f.setLocation(750, 350);
        d.addAdjCity(a);
        d.addAdjCity(b);
        a.addAdjCity(b);
        c.addAdjCity(d);
        f.addAdjCity(a);
        f.addAdjCity(b);
        f.addAdjCity(c);
        e.addAdjCity(f);
        e.addAdjCity(b);
        size = 6;
        m = new Map(6);
        m.addCity(a);
        m.addCity(b);
        m.addCity(c);
        m.addCity(d);
        m.addCity(e);
        m.addCity(f);
        frame = m.drawMap();
        currentCity = 0;
    	
        /* Create all the other agents */
        NPCAgent npc[] = new NPCAgent[5];
        npc[0] = new NPCAgent(a, m);
        npc[1] = new NPCAgent(b, m);
        npc[2] = new NPCAgent(c, m);
        npc[3] = new NPCAgent(d, m);
        npc[4] = new NPCAgent(a, m);

        for (int i = 0; i < 5; i++) {
        	launchAgent(npc[i], true);
        }

    }
    protected void live() {
    	java.util.Random r = new java.util.Random();
        Message ans;
    	while (true) {
            pause(RUNNERCLOCK);
            logger.info("Runner: Im currently in :" + m.getCity(currentCity).getName());           
            int numberOfPaths = m.getCity(currentCity).getAdjNum();
            currentCity = m.getCity(currentCity).getAdjCity(r.nextInt(numberOfPaths)).getNum();
            m.setRunnerLocation(currentCity);
            m.refreshMap();
            /*ans = sendMessageWithRoleAndWaitForReply(
					RunnerChaserCommunication.COMMUNITY, 
					RunnerChaserCommunication.RUNNER_NPC_GROUP, 
					RunnerChaserCommunication.NPC_ROLE, 
					new StringMessage(m.getCity(currentCity).getName()), 
					RunnerChaserCommunication.RUNNER_ROLE,
					1000);
			*/
    		//broadcastMessage(COMMUNITY,SIMU_GROUP,FOLLOWER_ROLE, new ObjectMessage<>(myInformation));
            broadcastMessage(comm.COMMUNITY, comm.RUNNER_NPC_GROUP, comm.NPC_ROLE, 
            		new StringMessage(m.getCity(currentCity).getName()));
    	}
    }
    /*
     * This is where all the agents are launched from!
     */
    public static void main(String[] args) {
        //executeThisAgent();
    	//the number at the end of the last arguments string is how many 
    	//instances of the agent to launch.
		//god knows what the true does...
    	/*new Madkit(Option.launchAgents.toString(), RunnerAgent.class.getName()+",true,1;",
                   	NPCAgent.class.getName()+",true,1"
                );*/
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
