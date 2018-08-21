
// This File maintains all the public Constants which one can change for performing simulations under different conditions.
// If in future any more attribure or feature is added. Define its public Constant here


class Constants {
	public static int count = 0;
	public static int nP = 0; // number of people to return;
	public static final double strengthThreshold = 500;
	public static final int simulations_genr = 30;  // Max number of simulations we will iterate to analyze results.
	public static final int lifeSpan = 300; // generations or iterations 
	public static final int stdDevLife = 50; // generations or iterations
	public static final int gridRow = 144;
	public static final int gridCol = 260;
	public static final int nHumans = 1000;
	public static position[][] environment = new position[gridRow][gridCol]; 
	public static final double trackedFactor = 0.01;
	public static final double untrackedFactor = 0.1;
	public static final double minStrength = 10;
	public static final double bored = 0.05;
	public static final double interactFactor = 0.05;
	public static final double initInteract = 0.25;
	public static final int nResources = 1000;
	public static final double moveCost = 5;
	public static final double mateCost = 10;
	public static final double boredLost = 30;
	public static final double initEnemyFactor = 0.25;
	public static final double enemyFactor = 0.2;
	public static final double mateInteractFactor = 0.5;
	public static final double resourceXStdDev = Constants.gridCol/12;
	public static final double resourceYStdDev = Constants.gridRow/12;
	public static final double humanXStdDev = Constants.gridCol/12;
	public static final double humanYStdDev = Constants.gridRow/12;
}
