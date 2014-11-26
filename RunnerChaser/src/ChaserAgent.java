import madkit.kernel.Agent;
import madkit.kernel.Message;
import madkit.message.StringMessage;

import java.util.Date;
import java.util.Timer;
public class ChaserAgent extends Agent {
	private static final int RUNNERCLOCK = RunnerChaserConfig.CHASERCLOCK;
	private RunnerChaserCommunication comm;
	private Map m;
	private int currentCity;
	private Date date;
	public ChaserAgent(Map _m, int initLocation) {
		m = _m;
		currentCity = initLocation;
	}
	protected void activate() {
		/*All the city groups should (hopefully) be created by now */
		createGroupIfAbsent(
				comm.COMMUNITY,
				comm.CHASER_NPC_GROUP,
				true, null);
		requestRole(
				comm.COMMUNITY,
				comm.CHASER_NPC_GROUP,
				comm.CHASER_ROLE
				);
	}
	protected void live() {
		Timer t = new Timer();
		Message msg;
		while (true) {
			broadcastMessage(
					comm.COMMUNITY,
					m.getCity(currentCity).getName(),
					comm.NPC_ROLE,
					new StringMessage(comm.POLLMSG)
					);
			date = new Date();
			long start = date.getTime();
			long timeAlloted = 0;
			while (timeAlloted < RUNNERCLOCK) {
				msg = waitNextMessage(100);
				date = new Date();
				timeAlloted = date.getTime() - start;
			}
			logger.info("Chaser: currently in " + m.getCity(currentCity).getName());
			
		}
	}
}