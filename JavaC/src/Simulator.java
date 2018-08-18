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
<<<<<<< HEAD
<<<<<<< HEAD
//				new Human(h1nbrs[i].x, h1nbrs[i].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2));
				new Human(h1nbrs[i].x, h1nbrs[i].y, Math.min(h1.curiosityThresh,  h2.curiosityThresh), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2), 
						(rand.nextGaussian()*5/3 + (h1.hstrength.getCurrStrength() +h2.hstrength.getCurrStrength())/2));
=======
				new Human(h1nbrs[i].x, h1nbrs[i].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2), 30.0);
>>>>>>> f865a2814857b9f9bc59318d54ffa89e7868d29f
=======
				new Human(h1nbrs[i].x, h1nbrs[i].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2), 30.0);
>>>>>>> f865a2814857b9f9bc59318d54ffa89e7868d29f
				h1.hstrength.setCurrStrength(h1.hstrength.getCurrStrength() - Constants.mateCost);
				h2.hstrength.setCurrStrength(h2.hstrength.getCurrStrength() - Constants.mateCost);
				h1.interactionList.replace(h2, ((h1.interactionList.containsKey(h2)) ? (h1.interactionList.get(h2)*2) : (Constants.mateInteractFactor)));
				h2.interactionList.replace(h1, ((h2.interactionList.containsKey(h1)) ? (h2.interactionList.get(h1)*2) : (Constants.mateInteractFactor)));
				h1.mated.add(h2);
				h2.mated.add(h1);
				for (Human h : h2.mated) {
					h.enemies.put(h1, ((h.enemies.containsKey(h1)) ? (h.enemies.get(h1)*2) : (Constants.initEnemyFactor)));
					h.interactionList.remove(h1);
					h1.interactionList.remove(h);
					h1.enemies.put(h, ((h1.enemies.containsKey(h)) ? (h1.enemies.get(h)*2) : (Constants.initEnemyFactor)));
				}
				return;
			}
		}
		position[] h2nbrs = h2.neighbors();
		for (int j = 0; j < h2nbrs.length; j++) {
			if (!(h1nbrs[j].humanp)){
<<<<<<< HEAD
<<<<<<< HEAD
//				new Human(h2nbrs[j].x, h2nbrs[j].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2));
				new Human(h2nbrs[j].x, h2nbrs[j].y, Math.min(h1.curiosityThresh,  h2.curiosityThresh), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2),
						(rand.nextGaussian()*5/3 + (h1.hstrength.getCurrStrength() +h2.hstrength.getCurrStrength())/2));
=======
				new Human(h2nbrs[j].x, h2nbrs[j].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2) , 30.0);
>>>>>>> f865a2814857b9f9bc59318d54ffa89e7868d29f
=======
				new Human(h2nbrs[j].x, h2nbrs[j].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2) , 30.0);
>>>>>>> f865a2814857b9f9bc59318d54ffa89e7868d29f
				h1.hstrength.setCurrStrength(h1.hstrength.getCurrStrength() - Constants.mateCost);
				h2.hstrength.setCurrStrength(h2.hstrength.getCurrStrength() - Constants.mateCost);
				h1.interactionList.replace(h2, ((h1.interactionList.containsKey(h2)) ? (h1.interactionList.get(h2)*2) : (Constants.mateInteractFactor)));
				h2.interactionList.replace(h1, ((h2.interactionList.containsKey(h1)) ? (h2.interactionList.get(h1)*2) : (Constants.mateInteractFactor)));
				h1.mated.add(h2);
				h2.mated.add(h1);
				for (Human h : h2.mated) {
					h.enemies.put(h1, ((h.enemies.containsKey(h1)) ? (h.enemies.get(h1)*2) : (Constants.initEnemyFactor)));
					h1.enemies.put(h, ((h1.enemies.containsKey(h)) ? (h1.enemies.get(h)*2) : (Constants.initEnemyFactor)));
					h.interactionList.remove(h1);
					h1.interactionList.remove(h);
				}
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
		
		for (int i = 0; i < Constants.nHumans; i++) {
			new Human();
		}
		
		
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
						System.out.printf("%d : %d %d %B %d %f %f %f", q, h.pos.x, h.pos.y, h.gender, h.lifeSpan, h.hstrength.getCurrStrength(), h.curiosity, h.curiosityThresh); // Log print
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
			      
			      		// New Mating algorithm -- Based on the interaction list
// 			      		for (int i = 0; i < humans.size(); i++) {
// 						position[] nbrs = humans.get(i).neighbors();
// 						double mit = 0;
// 						Human h = null;
// 						for (int j = 0; j < 8; j++) {
// 							if ((nbrs[j].humanp) && (humans.get(i).gender != humans.get(j).gender)) {
// 								if (humans.get(i).interactionList.containsKey(nbrs[i].humanHere)) {
// 									double it = humans.get(i).interactionList.get(nbrs[j].humanHere);
// 									if (it > mit) {
// 										mit = it;
// 										h = humans.get(j);
// 									}
// 								}
// 							}
// 						}
// 						if (h != null) {
// 							mate(humans.get(i), h);
// 						}
// 					}
			      
					
					for (Human h : humans) {
						h.lifeSpan--;
					}
		      }
		  };
		  new Timer(delay, taskPerformer).start();
		  
		  
		// Initial implementation 
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
