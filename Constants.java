import java.util.*;
class Constants {
	public static final double strengthThreshold = 100;
	public static final int lifeSpan = 300; // generations or iterations 
	public static final int stdDevLife = 100; // generations or iterations
	public static final int gridRow = 1000;
	public static final int gridCol = 1000;
	public static Vector<position> occupied = new Vector<position>();
	public static final int nHumans = 10;
	public static position[][] environment = new position[gridRow][gridCol]; 
}