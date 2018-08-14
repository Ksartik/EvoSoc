class Constants {
	public static final double strengthThreshold = 100;
	public static final int lifeSpan = 100; // generations or iterations 
	public static final int stdDevLife = 50; // generations or iterations
	public static final int gridRow = 144;
	public static final int gridCol = 260;
//	public static Vector<position> occupied = new Vector<position>();
	public static final int nHumans = 30;
	public static position[][] environment = new position[gridRow][gridCol]; 
	public static final double trackedFactor = 0.01;
	public static final double untrackedFactor = 0.1;
	public static final double minStrength = 10;
	public static final double bored = 0.05;
	public static final double interactFactor = 0.05;
	public static final double initInteract = 0.25;
	public static final int nGens = 1;
	public static final int nResources = 100;
	public static final double moveCost = 5;
	public static final double mateCost = 10;
	public static final double boredLost = 30;
}