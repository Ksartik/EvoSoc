//class InvalidStringTagException extends Exception{
//	public String toString() {
//		return "Invalid string tag to position!";
//	}
//}
//class AlreadyOccupiedException extends Exception {
//	public String toString() {
//		return "Position already occupied";
//	}
//}
public class position {
	public int x,y;
	public Resource resourceHere;
	public Human humanHere;
	public boolean resourcep;
	public boolean humanp;
	
	public position (int x, int y) {
		this.x = x;
		this.y = y;
		this.resourceHere = null;
		this.resourcep = false;
		this.humanHere = null;
		this.humanp = false;
	}
}
	
//	public position (Object obj) throws InvalidStringTagException {
//		if (obj instanceof Human) {
//			initHuman();
//			Constants.environment[this.x][this.y].humanp = true;
//			Constants.environment[this.x][this.y].humanHere = (Human) obj;
//			humanHere = (Human) obj;
//		}
//		else if (obj instanceof Resource) {
//			initResource();
//			Constants.environment[this.x][this.y].resourcep = true;
//			Constants.environment[this.x][this.y].resourceHere = (Resource) obj;
//			resourceHere = (Resource) obj;
//		}
//		else {
//			throw new InvalidStringTagException();
//		}
//	}
//	
//	public position (Object obj, int x, int y) throws Exception{
//		if (obj instanceof Human) {
//			this.x = x;
//			this.y = y;
//			if (Constants.environment[this.x][this.y].humanp) {
//				throw new AlreadyOccupiedException();
//			}
//			Constants.environment[this.x][this.y].humanp = true;
//			Constants.environment[this.x][this.y].humanHere = (Human) obj;
//			humanHere = (Human) obj;
//		}
//		else if (obj instanceof Resource) {
//			this.x = x;
//			this.y = y;
//			Constants.environment[this.x][this.y].resourcep = true;
//			Constants.environment[this.x][this.y].resourceHere = (Resource) obj;
//			resourceHere = (Resource) obj;
//		}
//		else {
//			throw new InvalidStringTagException();
//		}
//	}

//	public void initHuman() {
//		/*
//		this.x = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridCol/2;
//		this.y = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridRow/2;
//		while (Constants.occupied.contain(this)) {
//			this.x = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridCol/2;
//			this.y = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridRow/2;
//		}
//		*/
//		
//		this.x = (int) (rand.nextGaussian()*Constants.gridCol/2 + Constants.gridCol/2);
//		this.y = (int) (rand.nextGaussian()*Constants.gridRow/2 + Constants.gridRow/2);
//		while (Constants.environment[this.x][this.y].humanp) {
//			this.x = (int) (rand.nextGaussian()*Constants.gridCol/2 + Constants.gridCol/2);
//			this.y = (int) (rand.nextGaussian()*(int)(Math.floor(Math.sqrt(Constants.nHumans)))) + Constants.gridRow/2;
//		}
//		
////		int i = (int) (rand.nextGaussian()*Constants.gridCol/2 + Constants.gridCol/2);
////		int j = (int) (rand.nextGaussian()*Constants.gridRow/2 + Constants.gridRow/2);
////		while (Constants.environment[i][j].humanp) {
////			i = (int) (rand.nextGaussian()*Constants.gridCol/2 + Constants.gridCol/2);
////			j = (int) (rand.nextGaussian()*Constants.gridRow/2 + Constants.gridRow/2);
////		}
//	}
//	
//	public void initResource() {
//		/*
//		 * Geographical Environment ---->>>>>
//		 * 
//		 */
//		
//		// Random Environment //
//		this.x = rand.nextInt(Constants.gridCol);
//		this.y = rand.nextInt(Constants.gridRow);
//	}
//}
