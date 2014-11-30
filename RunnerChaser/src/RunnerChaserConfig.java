/*
 * Configuration class contains the constant
 * values used by the simulation that
 * can be modified to change the balance
 * outcomes of the games.
 */
public class RunnerChaserConfig {
	public static final int RUNNERCLOCK = 3000;
	public static final int CHASERCLOCK = 6000;
	public static final int HEADSTART = 6000;
	/* The following is the mean and std deviation for
	 * the Gaussian distribution of npc allegiences.
	 * Allegiance is 0-100 going from chaser to runner,
	 * respectively.
	 */
	public static final double NPC_ALLEGIANCE_MEAN = 15;
	public static final double NPC_ALLEGIANCE_STD_DEVIATION = 20;
}
