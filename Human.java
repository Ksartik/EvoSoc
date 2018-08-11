import java.util.*;
public class Human {
	private class strength {
		double currStrength;
		Random rand = new Random();
		public strength () {
			this.currStrength = rand.nextDouble()*Constants.strengthThreshold;
		}
		public double getCurrStrength() {
			return this.currStrength;
		}

		public Boolean setCurrStrength(double currStrength) {
			if (currStrength > Constants.strengthThreshold) {
				return false;
			}
			else {
				this.currStrength = currStrength;
				return true;
			}
		}
		
	}
	Random rand = new Random();
	Boolean gender; // false -> female, true -> male
	strength hstrength; 
	Vector<position> expTrack = new Vector<position>(); 
	int lifeSpan; 
	double curiosity;
	double curiosityThresh;
	position pos;
	public Human() {
		// TODO Auto-generated constructor stub
		this.gender = rand.nextInt(1) == 0 ? false : true; 
		this.hstrength = new strength();
		this.lifeSpan = (int) (rand.nextGaussian()*Constants.stdDevLife + Constants.lifeSpan);
		this.curiosity = rand.nextDouble();
		this.curiosityThresh = rand.nextDouble();
		try {
			this.pos = new position("Human");
		} catch(InvalidStringTagException e) {
			System.out.println(e);
		}
	}
	public Human(position pos, double curThresh, int lifeSpan) {
		this.gender = rand.nextInt(1) == 0 ? false : true; 
		this.hstrength = new strength();
		this.lifeSpan = lifeSpan; // mated + mutated
		this.curiosity = rand.nextDouble();
		this.curiosityThresh = curThresh; // mated + mutated
		this.pos = pos;
	}
	
	public position[] neighbors(){
		position[] nbrs = new position[8];
		int k = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				nbrs[k] = Constants.environment[this.pos.x + i][this.pos.y + i];
				k++;
			}
		}
		return nbrs;
	}
	
	public void move() {
		position[] nbrs = this.neighbors();
		for (int i = 0 ; i < nbrs.length; i++) {
			if (nbrs[i].resourcep && this.hstrength.getCurrStrength() < Constants.strengthThreshold) {
				nbrs[i].resourceHere.used(this);
				
			}
		}
	}
//	public Human(double curThresh, Vector<position> expTrack, int lifeSpan) {
//		
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
