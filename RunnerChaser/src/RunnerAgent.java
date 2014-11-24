	import madkit.kernel.Agent;
import madkit.kernel.Madkit;
public class RunnerAgent extends Agent
{
    private Map m;
    private City currentCity;
    private int size;
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
        size = 4;
        m = new Map(4);
        m.addCity(a);
        m.addCity(b);
        m.addCity(c);
        m.addCity(d);
        System.out.println("------> " + m.getCity(3).getAdjCity(0).getName());
        m.drawMap();
        	
        currentCity = m.getCity(0);
    }
    
    protected void live() {
        while (true) {
            pause(1000);
            logger.info("Runner: Im currently in :" + currentCity.getName()); 
            //currentCity = m.getCity(nextCity);
        }
    }
    public static void main(String[] args) {
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
