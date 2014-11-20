/**
 * @author tom
 *
 */
import java.util.logging.Level;

import madkit.kernel.AgentAddress;
import madkit.kernel.Madkit;
import madkit.kernel.Agent;

public class NPCAgent extends Agent {
	private long runnerAllegiance = 0;
	private long chaserAllegiance = 0;
	private int cityNumber;
	public City runnrgoing;

	protected void activate() {
		pause(500);
	}
	@Override
	protected void live() {
		//AgentAddress other = null;
		logger.info("LOLOLOLOL");
	}
	
	@Override
	protected void end() {
	}
	
	public void met(){
	}
	
	public static void main(String[] args) {
	}
}
