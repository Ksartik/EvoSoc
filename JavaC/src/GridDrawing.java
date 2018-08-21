//import java.util.*;
import java.awt.*;
//import java.awt.geom.*;
import javax.swing.*;

public class GridDrawing extends JComponent {
	int w, h;	
	
	public Color humanColor (Human h) {
		int green = (int) (h.hstrength.getCurrStrength()/Constants.strengthThreshold * 255);
		int red = (int) (Math.abs((h.curiosity - h.curiosityThresh) * 255));
		return new Color( 0 , green , 0);
	}
	
	public Color resourceColor (Resource r) {
		return new Color (((int) (r.value/Constants.strengthThreshold/2 * 255)), 0, 0);
	}
	
	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int blockWid = this.w / Constants.gridCol;
		int blockHei = this.h / Constants.gridRow;
//		Rectangle2D.Double rect = new Rectangle2D.Double();
		
//		int r = 0;
//		for (int i = 0; i < Constants.gridRow; i++) {
//			for (int j = 0; j < Constants.gridCol; j++) {
//				Constants.environment[i][j] = new position(i,j);
//			}
//		}
		
		int y = 0;

		for (int r = 0; r < Constants.gridRow; r++) {
			int x = 0;
			for (int c = 0; c < Constants.gridCol; c++) {
//				rect.setRect(x, y, 5, 5);
//				g2.draw(rect);

				if (Constants.environment[r][c].humanp) {
					// g2.setColor(humanColor (Constants.environment[r][c].humanHere));
					if(Constants.environment[r][c].humanHere.gender)
					{
						g2.setColor(Color.YELLOW);
					}
					else
					{
						g2.setColor(Color.GREEN);
					}	
//					rect.setRect(x, y, 5, 5);
					g2.fillRect(x, y, blockWid, blockHei);
				}
				else if (Constants.environment[r][c].resourcep) {
					g2.setColor(resourceColor(Constants.environment[r][c].resourceHere));
					g2.setColor(Color.RED);
//					rect.setRect(x, y, 5, 5);
					g2.fillRect(x, y, blockWid, blockHei);
				}
				else {
					
//					g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
					g2.setColor(Color.WHITE);
//					rect.setRect(x, y, 5, 5);
					
//					g2.fill(rect);
					g2.fillRect((int)x,(int) y, blockWid, blockHei);
					
				}
				g2.setColor(Color.BLACK);
				g2.drawRect((int)x, (int) y, blockWid, blockHei);
		
				x += 5;
			}
			y += 5;
			if (Constants.count >= Constants.simulations_genr+1) {
				System.exit(0);
			}


		}
//		for (double y = 0; y < w; y += blockHei) {
//			int c = 0;
//			for (double x = 0; x < h; x += blockWid) {
//				rect.setRect(x, y, blockWid, blockHei);
//				if (Constants.environment[r][c].humanp) {
//					g2.setColor(humanColor (Constants.environment[r][c].humanHere));
//					g2.fill(rect);
//				}
//				else if (Constants.environment[r][c].resourcep) {
//					g2.setColor(resourceColor(Constants.environment[r][c].resourceHere));
//					g2.fill(rect);
//				}
//				else {
//					g2.setColor(Color.WHITE);
//					g2.fill(rect);
//				}
//				c++;
//			}
//			r++;
//		}
	}
	
	public GridDrawing(int width, int height) {
		// TODO Auto-generated constructor stub
		this.w = width;
		this.h = height;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
