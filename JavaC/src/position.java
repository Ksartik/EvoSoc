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
