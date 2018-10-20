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
//		double childCuriosityThresh = Math.min(h1.curiosityThresh, h2.curiosityThresh)  rand.nextDouble()*(1 - Math.min(h1.curiosityThresh, h2.curiosityThresh));
		double childCuriosityThresh = rand.nextDouble()*Math.min(h1.curiosityThresh, h2.curiosityThresh) + rand.nextDouble();
		for (int i = 0; i < h1nbrs.length; i++) {
			if (!(h1nbrs[i].humanp)){
/// 			new Human(h1nbrs[i].x, h1nbrs[i].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2));
				new Human(h1nbrs[i].x, h1nbrs[i].y, childCuriosityThresh, (int)(rand.nextGaussian()*50/3 + (h1.lifeSpan + h2.lifeSpan)/2),//, 30);
						(rand.nextGaussian()*20/3 + (h1.hstrength.getCurrStrength() +h2.hstrength.getCurrStrength())/2));
				h1.hstrength.setCurrStrength(h1.hstrength.getCurrStrength() - Constants.mateCost);
				h2.hstrength.setCurrStrength(h2.hstrength.getCurrStrength() - Constants.mateCost);
//				if (h1.interactionList.containsKey(h2) && h2.interactionList.containsKey(h1)) {
//					h1.interactionList.replace(h1, h1.interactionList.get(h2)*2);
//					h2.interactionList.replace(h2, h2.interactionList.get(h1)*2);
//				}
//				else {
//					h1.interactionList.put(h2, Constants.mateInteractFactor);
//					h2.interactionList.put(h1, Constants.mateInteractFactor);
//				}
//				h1.curiosity -= Constants.mateCuriosity;
//				h2.curiosity -= Constants.mateCuriosity;
				h1.mated.add(h2);
				h2.mated.add(h1);
				for (Human h : h2.mated) {
					if (h1 != h) {
						if (h1.enemies.containsKey(h)) {
							h1.enemies.replace(h, (h1.enemies.get(h) + Constants.enemyFactor));
							h.enemies.replace(h1, (h.enemies.get(h1) + Constants.enemyFactor));
//							h1.enemies.replace(h, h1.enemies.get(h)*2);
//							h.enemies.replace(h1, h.enemies.get(h1)*2);
						}
						else {
							h1.enemies.put(h, Constants.initEnemyFactor);
							h.enemies.put(h1, Constants.initEnemyFactor);
						}
					}
					h.interactionList.remove(h1);
					h1.interactionList.remove(h);
				}
				return;
			}
		}
		position[] h2nbrs = h2.neighbors();
		for (int j = 0; j < h2nbrs.length; j++) {
			if (!(h1nbrs[j].humanp)){
//				new Human(h2nbrs[j].x, h2nbrs[j].y, (rand.nextGaussian()*((1 - Math.max(h1.curiosityThresh, h2.curiosityThresh))/3) + Math.min(h1.curiosityThresh, h2.curiosityThresh)), (int)(rand.nextGaussian()*10/3 + (h1.lifeSpan + h2.lifeSpan)/2));
				new Human(h2nbrs[j].x, h2nbrs[j].y, childCuriosityThresh, (int)(rand.nextGaussian()*50/3 + (h1.lifeSpan + h2.lifeSpan)/2),//, 30);
						(rand.nextGaussian()*20/3 + (h1.hstrength.getCurrStrength() +h2.hstrength.getCurrStrength())/2));
				h1.hstrength.setCurrStrength(h1.hstrength.getCurrStrength() - Constants.mateCost);
				h2.hstrength.setCurrStrength(h2.hstrength.getCurrStrength() - Constants.mateCost);
//				if (h1.interactionList.containsKey(h2) && h2.interactionList.containsKey(h1)) {
//					h1.interactionList.replace(h1, h1.interactionList.get(h2)*2);
//					h2.interactionList.replace(h2, h2.interactionList.get(h1)*2);
//				}
//				else {
//					h1.interactionList.put(h2, Constants.mateInteractFactor);
//					h2.interactionList.put(h1, Constants.mateInteractFactor);
//				}
//				h1.curiosity -= Constants.mateCuriosity;
//				h2.curiosity -= Constants.mateCuriosity;
				h1.mated.add(h2);
				h2.mated.add(h1);
				for (Human h : h2.mated) {
					if (h1 != h) {
						if (h1.enemies.containsKey(h)) {
							h1.enemies.replace(h, (h1.enemies.get(h) + Constants.enemyFactor));
							h.enemies.replace(h1, (h.enemies.get(h1) + Constants.enemyFactor));
//							h1.enemies.replace(h, h1.enemies.get(h)*2);
//							h.enemies.replace(h1, h.enemies.get(h1)*2);
						}
						else {
							h1.enemies.put(h, Constants.initEnemyFactor);
							h.enemies.put(h1, Constants.initEnemyFactor);
						}
					}
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
	
	public static Vector<Resource> currResources () {
		Vector<Resource> resources = new Vector<Resource>();
		for (int i = 0; i < Constants.environment.length; i++) {
			for (int j = 0; j < Constants.environment[0].length; j++) {
				if (Constants.environment[i][j].resourcep) {
					resources.add(Constants.environment[i][j].resourceHere);
				}
			}
		}
		return resources;
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
		    			  if (humans.get(i).isNeighbor(humans.get(j)) && humans.get(i).gender != humans.get(j).gender && humans.get(i).hstrength.getCurrStrength() > Constants.mateCost && humans.get(j).hstrength.getCurrStrength() > Constants.mateCost && humans.get(i).age > Constants.mateAge) {
		    				  mate(humans.get(i), humans.get(j));
		    			  }		
		    		  }
		    	  }
		    	
			      
			      		// New Mating algorithm -- Based on the interaction list
//		    	  for (int i = 0; i < humans.size(); i++) {
//		    		  position[] nbrs = humans.get(i).neighbors();
//		    		  double mit = 0;
//		    		  Human h = null;
//		    		  for (int j = 0; j < 8; j++) {
//		    			  if ((nbrs[j].humanp) && (humans.get(i).gender) && (!(nbrs[j].humanHere.gender))) {
//		    				  if (humans.get(i).interactionList.containsKey(nbrs[j].humanHere)) {
//		    					  double it = humans.get(i).interactionList.get(nbrs[j].humanHere);
//		    					  if (it > mit) {
//		    						  mit = it;
//		    						  h = nbrs[j].humanHere;
//		    					  }
//		    				  }
//		    			  }
//		    		  }
//		    		  if (h != null) {
//		    			  mate(humans.get(i), h);
//		    		  }
//		    	  }
			      
					
		    	  for (Human h : humans) {
		    		  h.lifeSpan--;
		    	  }
		      }
		  };
		  new Timer(delay, taskPerformer).start();
		  
		
	}
}
