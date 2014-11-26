/**
 * @author tom
 *
 */
import java.util.Date;
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
	private City runnrgoing;
	private Map worldMap;
	private String lastSeenHeaded;
	private RunnerChaserCommunication comm;
	public NPCAgent(City _residence, Map worldMap) {
			residence = _residence;
			_residence.incPop();
			Random r = new Random();
			allegiance = r.nextInt(100);
	}
	protected void activate() {
	        logger.info("NPC: Hello I am a npc agent, I live in " +
	        		residence.getName() + " and my allegience is :" + allegiance);
	        /* Create NPC/RUNNER comm group */ 
	        createGroupIfAbsent(
	        		comm.COMMUNITY,
	        		comm.RUNNER_NPC_GROUP,
	        		true, null);
	    	requestRole(
	    			comm.COMMUNITY, 
	    			comm.RUNNER_NPC_GROUP, 
	    			comm.NPC_ROLE);
			createGroupIfAbsent(
					comm.COMMUNITY,
					residence.getName(),
					true, null);
			requestRole(
					comm.COMMUNITY,
					residence.getName(),
					comm.NPC_ROLE);
			/* Create NPC/CHASER comm group */
			createGroupIfAbsent(
					comm.COMMUNITY,
					comm.CHASER_NPC_GROUP,
					true, null);
			requestRole(
					comm.COMMUNITY,
					comm.CHASER_NPC_GROUP,
					comm.NPC_ROLE
					);
	}
	@Override
	protected void live() {
		Message msg;
		while (true) {
			msg = waitNextMessage();
			String msgStr = ((StringMessage) msg).getContent();
			//logger.info("THE RUNNER IS IN MY CITY OMG OMG OMG OMG!");
			if (msg.getSender().getRole() == comm.RUNNER_ROLE) {
				logger.info("NPC: Runner agent is currently here\n "
						+ "and is seen headed to: " + msgStr);
				lastSeenHeaded = msgStr;
			}
			if (msg.getSender().getRole() == comm.CHASER_ROLE && 
					((StringMessage) msg).getContent() == comm.POLLMSG) {
				
			}
		}
	}
	
	@Override
	protected void end() {
	}
	
	public void met(){
	}
}
