import java.util.*;
class InvalidStringTagException extends Exception{
	public String toString() {
		return "Invalid string tag to position!";
	}
}
class AlreadyOccupiedException extends Exception {
	public String toString() {
		return "Position already occupied";
	}
}
public class position {
	private Random rand = new Random();
	public int x,y;
	public Resource resourceHere = null;
	public Human humanHere = null;
	public boolean resourcep = false;
	public boolean humanp = false;
	public position (Object obj) throws InvalidStringTagException {
		if (obj instanceof Human) {
			initHuman();
			Constants.environment[this.x][this.y].humanp = true;
			humanHere = (Human) obj;
		}
		else if (obj instanceof Resource) {
			initResource();
			Constants.environment[this.x][this.y].resourcep = true;
			resourceHere = (Resource) obj;
		}
		else {
			throw new InvalidStringTagException();
		}
	}
	
	public position (Object obj, int x, int y) throws Exception{
		if (obj instanceof Human) {
			this.x = x;
			this.y = y;
			if (Constants.environment[this.x][this.y].humanp) {
				throw new AlreadyOccupiedException();
			}
			Constants.environment[this.x][this.y].humanp = true;
			humanHere = (Human) obj;
		}
		else if (obj instanceof Resource) {
			this.x = x;
			this.y = y;
			Constants.environment[this.x][this.y].resourcep = true;
			resourceHere = (Resource) obj;
		}
		else {
			throw new InvalidStringTagException();
		}
	}
	
	public position (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void initHuman() {
		/*
		this.x = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridCol/2;
		this.y = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridRow/2;
		while (Constants.occupied.contain(this)) {
			this.x = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridCol/2;
			this.y = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridRow/2;
		}
		*/
		
		this.x = (int) (rand.nextGaussian()*Constants.gridCol/2 + Constants.gridCol/2);
		this.y = (int) (rand.nextGaussian()*Constants.gridRow/2 + Constants.gridRow/2);
		while (Constants.occupied.contains(this)) {
			this.x = (int) (rand.nextGaussian()*Constants.gridCol/2 + Constants.gridCol/2);
			this.y = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridRow/2;
		}
		
//		int i = (int) (rand.nextGaussian()*Constants.gridCol/2 + Constants.gridCol/2);
//		int j = (int) (rand.nextGaussian()*Constants.gridRow/2 + Constants.gridRow/2);
//		while (Constants.environment[i][j].humanp) {
//			i = (int) (rand.nextGaussian()*Constants.gridCol/2 + Constants.gridCol/2);
//			j = (int) (rand.nextGaussian()*Constants.gridRow/2 + Constants.gridRow/2);
//		}
	}
	
	public void initResource() {
		/*
		 * Geographical Environment ---->>>>>
		 * 
		 */
		
		// Random Environment //
		this.x = rand.nextInt(Constants.gridCol);
		this.y = rand.nextInt(Constants.gridRow);
	}
}
