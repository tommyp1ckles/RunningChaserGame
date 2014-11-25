/**
 * @author tom
 *
 */
import java.util.Random;
import java.util.logging.Level;

import madkit.kernel.AgentAddress;
import madkit.kernel.Madkit;
import madkit.kernel.Agent;
import madkit.kernel.Message;
import madkit.message.StringMessage;

public class NPCAgent extends Agent {
    //allegience is chances of npc lying to chaser
	//so 0 -> (fully) loyal to chaser and 1 -> loyal to Runner.
	//We may have to tweak to balance the "game".
	private int allegiance = 0;
	private City residence;
	public City runnrgoing;
	public Map worldMap;
	public NPCAgent(City _residence, Map worldMap) {
			residence = _residence;
			Random r = new Random();
			allegiance = r.nextInt(100);
	}
	protected void activate() {
	        logger.info("NPC: Hello I am a npc agent and my allegience is :" + allegiance);
	        createGroupIfAbsent(RunnerChaserCommunication.COMMUNITY, RunnerChaserCommunication.RUNNER_NPC_GROUP, true, null);
	    	requestRole(RunnerChaserCommunication.COMMUNITY, RunnerChaserCommunication.RUNNER_NPC_GROUP, 
	    			RunnerChaserCommunication.NPC_ROLE);
			
	}
	@Override
	protected void live() {
		Message msg;
		while (true) {
			msg = waitNextMessage();
			logger.info("NPC: Runner agent is currently in: " + ((StringMessage) msg).getContent());
			//((StringMessage) msg).getContent()
		}
	}
	
	@Override
	protected void end() {
	}
	
	public void met(){
	}
}
