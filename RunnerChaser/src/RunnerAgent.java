//import madkit.runnerchaser;

public class RunnerAgent extends AbstractMovingAgent
{ 
	public boolean declareDestination()
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
	}
}
