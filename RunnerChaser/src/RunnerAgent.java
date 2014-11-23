import madkit.kernel.Agent;
import madkit.kernel.Madkit;
public class RunnerAgent extends Agent
{
    private Map m;
    protected void activate() {
        System.out.println("Activating RunnerAgent");
        City a = new City("Wolfville");
        City b = new City("San Francisco");
        City c = new City("Los Angeles");
        City d = new City("Halifax");
        int size = 3;
        int adj[][] = {
          // a  b  c  
            {1, 0, 1, 0}, //a
            {1, 0, 1, 0}, //a
            {1, 1, 0, 0}, //b
            {1, 1, 1, 0}, //c
        };
        m = new Map(4);
        m.addCity(a, adj[0]);
        m.addCity(b, adj[1]);
        m.addCity(c, adj[2]);
        m.addCity(d, adj[3]);
        m.printMatrix();
        m.drawMap();
    }
    
    protected void live() {
        while (true) {
            pause(1000);
            logger.info("im here!");
            System.out.println("tock");
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
