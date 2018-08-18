import java.util.*;
public class Human {
	public class strength {
		double currStrength;
		Random rand = new Random();
		public strength () {
			this.currStrength = rand.nextDouble()*Constants.strengthThreshold;
		}
		public double getCurrStrength() {
			return this.currStrength;
		}

		public void setCurrStrength(double currStrength){
			if (currStrength > Constants.strengthThreshold) {
				this.currStrength = Constants.strengthThreshold;
			}
			else {
				this.currStrength = currStrength;
			}
		}
		
	}
	Random rand = new Random();
	Boolean gender; // false -> female, true -> male
	strength hstrength; 
	Vector<Integer[]> expTrack = new Vector<Integer[]>(); 
	int lifeSpan; 
	double curiosity;
	double curiosityThresh;
	humanPosition pos;
	HashMap<Human, Double> interactionList;
	Vector<Human> mated;
	HashMap<Human, Double> enemies;
	public class humanPosition {
		public int x, y;
		Human h;
		public humanPosition(Human h) {
			this.x = (int) ((rand.nextGaussian()*Constants.gridCol/8) + Constants.gridCol/2);
			this.y = (int) ((rand.nextGaussian()*Constants.gridRow/8) + Constants.gridRow/2);
			while (Constants.environment[this.y][this.x].humanp) {
				this.x = (int) ((rand.nextGaussian()*Constants.gridCol/8) + Constants.gridCol/2);
				this.y = (int) ((rand.nextGaussian()*Constants.gridRow/8) + Constants.gridRow/2);
			}
			
			//Uniform initial distribution 
//			this.x = (int) (rand.nextInt(Constants.gridCol));
//			this.y = (int) (rand.nextInt(Constants.gridRow));
//			while (Constants.environment[this.y][this.x].humanp) {
//				this.x = (int) (rand.nextInt(Constants.gridCol));
//				this.y = (int) (rand.nextInt(Constants.gridRow));
//			}
			Constants.environment[this.y][this.x].humanp = true;
			Constants.environment[this.y][this.x].humanHere = h;
			this.h = h;			
		}
		public humanPosition(Human h, int x, int y) {
			this.x = x;
			this.y = y;
			Constants.environment[this.y][this.x].humanp = true;
			Constants.environment[this.y][this.x].humanHere = h;
			this.h = h;
		}
		public void setPosition (int x, int y) {
			Constants.environment[this.y][this.x].humanp = false;
			Constants.environment[this.y][this.x].humanHere = null;
			Constants.environment[y][x].humanp = true;
			Constants.environment[y][x].humanHere = this.h;
			this.x = x;
			this.y = y;
		}
	}
	
	public Human() {
		// TODO Auto-generated constructor stub
		this.gender = rand.nextInt(2) == 0 ? false : true; 
		this.hstrength = new strength();
		this.lifeSpan = (int) (rand.nextGaussian()*Constants.stdDevLife/3 + Constants.lifeSpan);
		this.curiosity = rand.nextDouble();
		this.curiosityThresh = rand.nextDouble();
		this.pos = new humanPosition(this);
		this.interactionList = new HashMap<Human, Double>();
		this.mated = new Vector<Human>();
		this.enemies = new HashMap<Human, Double>();
	}
	public Human(int x, int y, double curThresh, int lifeSpan) {
		this.gender = rand.nextInt(2) == 0 ? false : true; 
		this.hstrength = new strength();
		this.lifeSpan = lifeSpan; // mated + mutated
		this.curiosity = rand.nextDouble();
		this.curiosityThresh = curThresh; // mated + mutated
		this.pos = new humanPosition(this, x, y);
		this.interactionList = new HashMap<Human, Double>();
		this.mated = new Vector<Human>();
		this.enemies = new HashMap<Human, Double>();
	}
	public Human(int x, int y, double curThresh, int lifeSpan, double strength) {
		this.gender = rand.nextInt(2) == 0 ? false : true; 
		this.hstrength = new strength();
		this.hstrength.setCurrStrength(strength);
		this.lifeSpan = lifeSpan; // mated + mutated
		this.curiosity = rand.nextDouble();
		this.curiosityThresh = curThresh; // mated + mutated
		this.pos = new humanPosition(this, x, y);
		this.interactionList = new HashMap<Human, Double>();
		this.mated = new Vector<Human>();
		this.enemies = new HashMap<Human, Double>();
	}
	public Human(int x, int y, double curThresh, int lifeSpan , double currentS) {// this is for child or can be used whenever we want to force some current strength to a human
		this.gender = rand.nextInt(2) == 0 ? false : true; 
		this.hstrength = new strength();
		this.lifeSpan = lifeSpan; // mated + mutated
		this.curiosity = rand.nextDouble();
		this.curiosityThresh = curThresh; // mated + mutated
		this.pos = new humanPosition(this, x, y);
		this.interactionList = new HashMap<Human, Double>();
		// this.currStrength = currentS;
		this.hstrength.setCurrStrength(currentS);

	}
	
	public position[] neighbors(){
		position[] nbrs = new position[8];
		int k = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0) {
					nbrs[k] = Constants.environment[(Constants.gridRow + this.pos.y + i)%(Constants.gridRow)][(Constants.gridCol + this.pos.x + j)%(Constants.gridCol)];
					k++;
				}
			}
		}
		return nbrs;
	}
	
	public void move() {
		position[] nbrs = this.neighbors();
		Vector<position> empty = new Vector<position>();
		Vector<position> resources = new Vector<position>();
		Vector<position> humans = new Vector<position>();
		for (int i = 0 ; i < 8; i++) {
			if (nbrs[i].humanp) 
				humans.add(nbrs[i]);
			else {
				if (nbrs[i].resourcep) {
//					double hst = this.hstrength.getCurrStrength();
//					double rvalue = nbrs[i].resourceHere.value;
//					this.hstrength.setCurrStrength(hst + nbrs[i].resourceHere.value);
//					nbrs[i].resourceHere.value = this.hstrength.getCurrStrength() - hst;
					
					resources.add(nbrs[i]);
				}
				else 
					empty.add(nbrs[i]);
			}
		}
		for (position p : humans) {
			if (this.interactionList.containsKey(p.humanHere)) {
				this.interactionList.put(p.humanHere, Math.max(this.interactionList.get(p.humanHere) + Constants.interactFactor, 1.0));				
			}
			else {
				this.interactionList.put(p.humanHere, Constants.initInteract);
				this.curiosity += Constants.untrackedFactor;
			}
		}
		if (!(resources.isEmpty()) && (this.hstrength.getCurrStrength() < Constants.strengthThreshold)) {
			double rval = 0;
			position pr = null;
			for (position p : resources) {
				double prvalue = p.resourceHere.value;
				if (prvalue >= rval) {
					rval = prvalue;
					pr = p;
				}
			}
			this.pos.setPosition(pr.x, pr.y);
			double hst = this.hstrength.getCurrStrength();
			this.hstrength.setCurrStrength(hst + pr.resourceHere.value);
			pr.resourceHere.getUsed(this.hstrength.getCurrStrength() - hst);
		}
		else if ((!(empty.isEmpty())) && (this.curiosity > this.curiosityThresh)) {
			if (this.interactionList.isEmpty()) {
				position p = empty.get(rand.nextInt(empty.size()));
				this.pos.setPosition(p.x, p.y);
			}
			else {
				double m = Double.MAX_VALUE;
//				position pr = this.pos;
				position pr = null;
				for (position e : empty) {
					double x = 0;
					for (Human h : this.interactionList.keySet()) {
						x += Math.pow(Math.pow(e.x - h.pos.x, 2) + Math.pow(e.y - h.pos.y, 2), 0.5) * this.interactionList.get(h);
					}
					for (Human h : this.enemies.keySet()) {
						x -= Math.pow(Math.pow(e.x - h.pos.x, 2) + Math.pow(e.y - h.pos.y, 2), 0.5) * this.enemies.get(h);
					}
					if (x < m) {
						m = x;
						pr = e;
					}
				}
				int nx = pr.x;
				int ny = pr.y;
				this.pos.setPosition(nx, ny);
			}
		}
		else {
			if (this.curiosity > this.curiosityThresh) {
				this.hstrength.setCurrStrength(this.hstrength.getCurrStrength() - Constants.boredLost);
				this.curiosity = Math.min(Constants.bored + this.curiosity, 1.0);
			}
			else {
				this.curiosity += Constants.bored;
				return;
			}
		}
		Integer[] poss = {this.pos.x, this.pos.y};
		if (this.expTrack.contains(poss)) {
			this.curiosity -= Constants.trackedFactor;
		}
		else {
			this.curiosity -= Constants.untrackedFactor;
			this.expTrack.add(poss);
		}
		this.hstrength.setCurrStrength(this.hstrength.getCurrStrength() - Constants.moveCost);
	}
	
	public Boolean canSurvive () {
		return ((this.hstrength.getCurrStrength() > Constants.minStrength) && (this.lifeSpan >= 0));
	}
	
	public void die () {
		Constants.environment[this.pos.y][this.pos.x].humanp = false;
		Constants.environment[this.pos.y][this.pos.x].humanHere = null;
	}
	
	public Boolean isNeighbor(Human h) {
		position[] nbrs = this.neighbors();
//		Vector<position> nbrs = this.neighbors();
		for (position p : nbrs) {
			if (p.humanp) {
				if (p.humanHere.equals(h)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
