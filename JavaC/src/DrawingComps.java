import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
public class DrawingComps extends JComponent {
	Random rand = new Random();
	double w, h;
	public void paintComponent (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		double blockSize = 5;
//		Line2D.Double l2 = new Line2D.Double();
//		for (double x = 0; x < w; x += blockSize) {
//			l2.setLine(x, 0, x, h);
//			g2d.draw(l2);
//		}
//		for (double y = 0; y < h; y += blockSize) {
//			l2.setLine(0, y, w, y);
//			g2d.draw(l2);
//		}
		Rectangle2D.Double rect = new Rectangle2D.Double();
		for (double y = 0; y < h; y += blockSize) {
			for (double x = 0; x < w; x += blockSize) {
				rect.setRect(x, y, blockSize, blockSize);
				g2d.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
				g2d.fill(rect);
			}
		}
	}
	public DrawingComps(double currWidth, double currHeight) {
		// TODO Auto-generated constructor stub
		this.w = currWidth;
		this.h = currHeight;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
