import madkit.kernel.Agent;

public class GameAgent extends Agent {
	private RunnerChaserCommunication comm = new RunnerChaserCommunication();
	private Map m;
	protected void activate() {
		createGroupIfAbsent(RunnerChaserCommunication.COMMUNITY, RunnerChaserCommunication.GAME_GROUP, true, null);
    	requestRole(RunnerChaserCommunication.COMMUNITY, RunnerChaserCommunication.GAME_GROUP, 
    			RunnerChaserCommunication.GAME_ROLE);
	}
	public GameAgent(Map _m) {
		m = _m;
	}
	protected void live() {
		while (true) {
			if (m.chaserLocation == m.runnerLocation) System.exit(0);
			logger.info("chaser= " + m.chaserLocation + ", runner = " + m.runnerLocation);
		}
	}
}
