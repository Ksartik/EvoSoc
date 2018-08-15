import java.util.*;
import java.awt.*;

import javax.swing.*;
public class TryGraph extends JFrame{
	public TryGraph() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setSize(1300,720);
		window.setTitle("Yo bitvches");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		Dimension d = window.getSize();

//		Rectangle rect = window.getBounds();
//		DrawingComps dc = new DrawingComps(rect.getWidth(), rect.getHeight());
		DrawingComps dc = new DrawingComps(d.getWidth(), d.getHeight());
		window.add(dc);
//		Dimension d = window.getSize();
//		System.out.println(d.getWidth());
//		System.out.println(d.getHeight());
	
	}

}
