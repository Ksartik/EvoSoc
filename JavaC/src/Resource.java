import java.util.*;
public class Resource {
	Random rand = new Random();
	public double value;
	resourcePosition pos;
	
	public class resourcePosition {
		int x, y;
		Resource r;
		public resourcePosition (Resource r) {			
			this.x = rand.nextInt(Constants.gridCol);
			this.y = rand.nextInt(Constants.gridRow);
			this.r = r;
			while (Constants.environment[this.y][this.x].resourcep) {
				this.x = (rand.nextInt(Constants.gridCol));
				this.y = (rand.nextInt(Constants.gridRow));
			}
			// Gaussian distribution about the centre with some std dev
// 			this.x = (int) (rand.nextGaussian()*Constants.resourceXStdDev + Constants.gridCol/2);
// 			this.y = (int) (rand.nextGaussian()*Constants.resourceYStdDev + Constants.gridRow/2);
// 			this.r = r;
// 			while ((this.y < 0) || (this.x < 0) || (this.y >= Constants.gridRow) || (this.x >= Constants.gridCol) || (Constants.environment[this.y][this.x].resourcep)) {
// 				this.x = (int) (rand.nextGaussian()*Constants.resourceXStdDev + Constants.gridCol/2);
// 				this.y = (int) (rand.nextGaussian()*Constants.resourceYStdDev + Constants.gridRow/2);
// 			}
			Constants.environment[this.y][this.x].resourcep = true;
			Constants.environment[this.y][this.x].resourceHere = r;	
		}
		
		public resourcePosition (Resource r, int x, int y) {
			this.x = x;
			this.y = y;
			this.r = r;
			Constants.environment[this.y][this.x].resourcep = true;
			Constants.environment[this.y][this.x].resourceHere = r;
		}
		
		public void setResoucePosition (int x, int y) {
			Constants.environment[this.y][this.x].resourcep = false;
			Constants.environment[this.y][this.x].resourceHere = null;
			this.x = x;
			this.y = y;
			Constants.environment[this.y][this.x].resourcep = true;
			Constants.environment[this.y][this.x].resourceHere = this.r;
		}
	}
	public Resource() {
		// TODO Auto-generated constructor stub
		this.value = rand.nextDouble()*Constants.strengthThreshold/2 + Constants.strengthThreshold/2;
		while (this.value < 0) 
			this.value = rand.nextDouble()*Constants.strengthThreshold/2 + Constants.strengthThreshold/2;
		this.pos = new resourcePosition(this);
	}
	
	// Geographical Environment //
	
	/* This can be further divided into a Tree class (a static resource) and 
	 * an Animal class (moving resource) which inherit this class and then a constructor of Resource
	 * can be either an Animal or a Plant (randomly).
	 */
	public Resource(position pos) {
		this.value = rand.nextDouble()*Constants.strengthThreshold/2 + Constants.strengthThreshold/2;
		while (this.value < 0) 
			this.value = rand.nextDouble()*Constants.strengthThreshold/2 + Constants.strengthThreshold/2;
		this.pos = new resourcePosition(this, pos.x, pos.y);
	}
	public void getUsed(double val) {
		if (val > this.value) {
			Constants.environment[this.pos.y][this.pos.x].resourcep = false;
			Constants.environment[this.pos.y][this.pos.x].resourceHere = null;
		}
		else {
			this.value -= val;
		}
	}
}
