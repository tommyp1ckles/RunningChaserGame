	import javax.swing.JFrame;

import madkit.kernel.Agent;
import madkit.kernel.Madkit;
import madkit.kernel.Madkit.Option;

public class RunnerAgent extends Agent
{
	private static final int RUNNERCLOCK = 1000;
	private Map m;
    private int currentCity;
    private int size;
    JFrame frame;
    protected void activate() {
        System.out.println("Activating RunnerAgent");
        City a = new City(0);
        a.setName("Halifax");
        City b = new City(1);
        b.setName("San Francisco");
        City c = new City(2);
        c.setName("Wolfville");
        City d = new City(3);
        d.setName("Toronto");
        d.addAdjCity(a);
        a.addAdjCity(b);
        c.addAdjCity(d);
        size = 4;
        m = new Map(4);
        m.addCity(a);
        m.addCity(b);
        m.addCity(c);
        m.addCity(d);
        frame = m.drawMap();
        currentCity = 0;
    	NPCAgent npc1 = new NPCAgent(a, m);
        launchAgent(npc1, true);
    }
    protected void live() {
    	java.util.Random r = new java.util.Random();
        while (true) {
            pause(RUNNERCLOCK);
            logger.info("Runner: Im currently in :" + m.getCity(currentCity).getName());           
            int numberOfPaths = m.getCity(currentCity).getAdjNum();
            currentCity = m.getCity(currentCity).getAdjCity(r.nextInt(numberOfPaths)).getNum();
            m.setRunnerLocation(currentCity);
            m.refreshMap();
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
