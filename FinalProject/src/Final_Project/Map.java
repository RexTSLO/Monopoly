package Final_Project;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.net.URL;

import javax.swing.*;

public class Map extends JPanel implements Serializable {
	
	protected Grid[] grids = new Grid[26];
	
	protected JButton[] buyButton = new JButton[2];
	
	protected JButton[] buildButton = new JButton[2];
	
	public Map() {
		this.setLayout(null);
		this.setSize(1080,720);
		
		grids[0] = new Grid("start.jpg", "start");
		grids[0].setSize(120, 120);
		grids[0].setLocation(0,0);
		grids[0].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(grids[0]);
		
		for(int i=1;i<=7;i++) {
			
			if(i == 4) grids[i] = new Grid("chance.jpg", "chance");
			else grids[i] = new Grid(0, 1000);
			grids[i].setSize(120, 120);
			grids[i].setLocation(120+((i-1)*120),0);
			grids[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.add(grids[i]);
		}
		
		
		grids[8] = new Grid("park.jpg", "park");
		grids[8].setSize(120, 120);
		grids[8].setLocation(960,0);
		grids[8].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(grids[8]);
		
		
		for(int i=1;i<=4;i++) {
			grids[i+8] = new Grid(1, 1000);
			grids[i+8].setSize(120,120);
			grids[i+8].setLocation(960,120+((i-1)*120));
			grids[i+8].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.add(grids[i+8]);
		}
		
		grids[13] = new Grid("prison.jpg", "prison");
		grids[13].setSize(120,120);
		grids[13].setLocation(960,600);
		grids[13].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(grids[13]);
		
		for(int i=1;i<=7;i++) {
			if(i == 4) grids[i+13] = new Grid("fate.jpg", "fate");
			else grids[i+13] = new Grid(2, 1000);
			grids[i+13].setSize(120,120);
			grids[i+13].setLocation(840-((i-1)*120),600);
			grids[i+13].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.add(grids[i+13]);
		}
		
		grids[21] = new Grid("bank.jpg", "bank");
		grids[21].setSize(120,120);
		grids[21].setLocation(0,600);
		grids[21].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(grids[21]);
		
		for(int i=1;i<=4;i++) {
			grids[i+21] = new Grid(3, 1000);
			grids[i+21].setSize(120,120);
			grids[i+21].setLocation(0,480-((i-1)*120));
			grids[i+21].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.add(grids[i+21]);
		}
		
	}
}