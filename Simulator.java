import java.util.*;
//import java.util.Timer;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.event.*;
import javax.swing.*;
//import java.awt.geom.*;

public class Simulator {
	private static Random rand = new Random();
	public static void mate (Human h1, Human h2) {
		position[] h1nbrs = h1.neighbors();
		for (int i = 0; i < h1nbrs.length; i++) {
			if (!(h1nbrs[i].humanp)){
				new Human(h1nbrs[i].x, h1nbrs[i].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2));
				h1.hstrength.setCurrStrength(h1.hstrength.getCurrStrength() - Constants.mateCost);
				h2.hstrength.setCurrStrength(h2.hstrength.getCurrStrength() - Constants.mateCost);
				return;
			}
		}
		position[] h2nbrs = h2.neighbors();
		for (int j = 0; j < h2nbrs.length; j++) {
			if (!(h1nbrs[j].humanp)){
				new Human(h2nbrs[j].x, h2nbrs[j].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2));
				h1.hstrength.setCurrStrength(h1.hstrength.getCurrStrength() - Constants.mateCost);
				h2.hstrength.setCurrStrength(h2.hstrength.getCurrStrength() - Constants.mateCost);
				return;
			}
		}
	}
	public Simulator() {
		// TODO Auto-generated constructor stub
	}

	public static Vector<Human> currHumans () {
		Vector<Human> humans = new Vector<Human>();
		for (int i = 0; i < Constants.environment.length; i++) {
			for (int j = 0; j < Constants.environment[0].length; j++) {
				if (Constants.environment[i][j].humanp) {
					humans.add(Constants.environment[i][j].humanHere);
				}
			}
		}
		return humans;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int countGen = 0;
		JFrame window = new JFrame();
		window.setSize(1300,720);
		window.setTitle("SIMULATOR");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = window.getSize();
		window.setVisible(true);
		for (int i = 0; i < Constants.gridRow; i++) {
			for (int j = 0; j < Constants.gridCol; j++) {
				Constants.environment[i][j] = new position(j,i);
			}
		}
		
		for (int i = 0; i < Constants.nResources; i++) {
			new Resource();
		}
		
//		for (int i = 0; i < Constants.gridCol; i++) {
//			for (int j = 0; j < Constants.gridRow; j++) {
//				if (Constants.environment[i][j].resourcep) {
//					System.out.print(Constants.environment[i][j].resourceHere.pos.x);
//					System.out.print(" ");
//					System.out.print(Constants.environment[i][j].resourceHere.pos.y);
//					System.out.print(" ");
//					System.out.print(Constants.environment[i][j].resourceHere.value);
//					System.out.println();
//				}
//			}
//		}
		
		for (int i = 0; i < Constants.nHumans; i++) {
			new Human();
		}
		

//		for (int i = 0; i < Constants.gridRow; i++) {
//			for (int j = 0; j < Constants.gridCol; j++) {
//				if (Constants.environment[i][j].humanp) {
//					for (position p : Constants.environment[i][j].humanHere.neighbors()) {
//						System.out.printf("%d %d\n", p.x, p.y);
//						System.out.printf("%B %B\n", p.humanp, p.resourcep);
//					}
//					System.out.println();
////					System.out.print(Constants.environment[i][j].humanHere.pos.x);
////					System.out.print(" ");
////					System.out.print(Constants.environment[i][j].humanHere.pos.y);
////					System.out.print(" ");
////					System.out.print(Constants.environment[i][j].humanHere.hstrength.getCurrStrength());
////					System.out.println();
//				}
//			}
//		}
		
//		window.add(new GridDrawing((int)d.getWidth(), (int)d.getHeight()));
		
		
		
		
		
		JComponent jc = new GridDrawing((int)d.getWidth(), (int)d.getHeight());
		
		window.add(jc);
		  int delay = 100; //milliseconds
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          //...Perform a task...
		    	  Vector<Human> humans = currHumans();
					int q = 0;
					for (Human h : humans) {
						System.out.printf("%d : %d %d %B %d %f %f %f", q, h.pos.x, h.pos.y, h.gender, h.lifeSpan, h.hstrength.getCurrStrength(), h.curiosity, h.curiosityThresh);
						System.out.println();
						q++;
					}
					for (Human h : humans) {
						if (!(h.canSurvive())) {
							h.die();
						}
						else {
							h.move();
						}
					}
					
					jc.repaint();
					
					//Mating **
					for(int i = 0; i < humans.size(); i++) {
						for(int j = i+1; j < humans.size(); j++) {
							if (humans.get(i).isNeighbor(humans.get(j)) && humans.get(i).gender != humans.get(j).gender) {
								mate(humans.get(i), humans.get(j));
							}	
						}
					}
//					
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						Thread.currentThread().interrupt();
//					}
					
					for (Human h : humans) {
						h.lifeSpan--;
					}
					
//					countGen++;
					
//					window.remove(jc);
		      }
		  };
		  new Timer(delay, taskPerformer).start();
		  
		  
		
//		while (countGen < Constants.nGens) {
//			Vector<Human> humans = currHumans();
//			int q = 0;
//			for (Human h : humans) {
//				System.out.printf("%d : %d %d %B %d %f %f %f", q, h.pos.x, h.pos.y, h.gender, h.lifeSpan, h.hstrength.getCurrStrength(), h.curiosity, h.curiosityThresh);
//				System.out.println();
//				q++;
//			}
//			for (Human h : humans) {
//				if (!(h.canSurvive())) {
//					h.die();
//				}
//				else {
//					h.move();
//				}
//			}
//			
//			
//			JComponent jc = new GridDrawing((int)d.getWidth(), (int)d.getHeight());
//			
//			window.add(jc);
//			
//			//Mating **
//			for(int i = 0; i < humans.size(); i++) {
//				for(int j = i+1; j < humans.size(); j++) {
//					if (humans.get(i).isNeighbor(humans.get(j)) && humans.get(i).gender != humans.get(j).gender) {
//						mate(humans.get(i), humans.get(j));
//					}	
//				}
//			}
////			
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				Thread.currentThread().interrupt();
//			}
//			
//			for (Human h : humans) {
//				h.lifeSpan--;
//			}
//			
//			countGen++;
//			
//			window.remove(jc);
//		}
		
	}
}
