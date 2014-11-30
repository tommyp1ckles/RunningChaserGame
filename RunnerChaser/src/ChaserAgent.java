import madkit.kernel.Agent;
import madkit.kernel.AgentAddress;
import madkit.kernel.Message;
import madkit.message.ObjectMessage;
import madkit.message.StringMessage;

import java.util.Date;
import java.util.List;
import java.util.Timer;
public class ChaserAgent extends Agent {
	private static final int RUNNERCLOCK = RunnerChaserConfig.CHASERCLOCK;
	private RunnerChaserCommunication comm;
	private RunnerChaserConfig config;
	private Map m;
	private int currentCity;
	private int moves = 0, pollRequests = 0, pollResponse = 0;
	private Agent runner;
	public ChaserAgent(Map _m, int initialCity, Agent _runner) {
		m = _m;
		currentCity = initialCity;
		runner = _runner;
		_m.setChaserLocation(currentCity);
	}
	protected void activate() {
		/*All the city groups should (hopefully) be created by now */
		createGroupIfAbsent(
				comm.COMMUNITY,
				m.getCity(currentCity).getName(),
				true, null);
	}
	protected void live() {
		pause(config.HEADSTART);
		while (true) {
			requestRole(
					comm.COMMUNITY,
					m.getCity(currentCity).getName(),
					comm.CHASER_ROLE
					);
			pause(2000);
			List<Message> messages;
			messages = broadcastMessageWithRoleAndWaitForReplies(
					comm.COMMUNITY,
					m.getCity(currentCity).getName(),
					comm.NPC_ROLE,
					new StringMessage("CHASERPOLL"),
					comm.CHASER_ROLE,
					config.CHASERCLOCK
					);
			if (messages != null) {
				for (int i = 0; i < messages.size(); i++) {
					StringMessage sm = (StringMessage) messages.get(i);
					pollRequests++;
					if (sm.getContent() != comm.DECLINE) {
						pollResponse++;
						currentCity = m.searchCities(sm.getContent()).getNum();
						m.setChaserLocation(currentCity);
						moves++;
					}
				}
			}
			if (currentCity == m.runnerLocation) {
				System.out.println("Chaser took " + moves + " moves to catch runner!");
				System.out.println("Chaser polled npc's a total of " + pollRequests + " times.");
				System.out.println("Chaser recieved :" + pollResponse  + " responses.");
				((RunnerAgent) runner).printStats();
				System.exit(0);
			}
			logger.info("Chaser: currently in " + m.getCity(currentCity).getName());
		}
	}
}