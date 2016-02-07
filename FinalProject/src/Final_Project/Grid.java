package Final_Project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grid extends JPanel implements Serializable{
	private int cost;
	private String owner;
	private Person ownerObject;
	
	private int houseNumber;
	
	private int houseBuildCost;
	
	private BufferedImage img;
	
	private JPanel architecture = new JPanel();
	
	public Grid(int i, int cost){
		/******************************************************************
		 * i = 0 top                                                      *
		 * i = 1 right                                                    *
		 * i = 2 bottom                                                   *
		 * i = 3 lift                                                     *
		 * cost                                                           *
		 * this constructor is normal land                                *
		 ******************************************************************/
		
		
		
		if(i == 0){
			this.setLayout(null);
			architecture.setSize(120, 30);
			architecture.setLocation(0, 90);
			architecture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			architecture.setLayout(new GridLayout(1, 4));
			this.add(architecture);
		}
		else if(i == 1){
			this.setLayout(null);
			architecture.setSize(30, 120);
			architecture.setLocation(0, 0);
			architecture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			architecture.setLayout(new GridLayout(4, 1));
			this.add(architecture);
		}
		else if(i == 2){
			this.setLayout(null);
			architecture.setSize(120, 30);
			architecture.setLocation(0, 0);
			architecture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			architecture.setLayout(new GridLayout(1, 4));
			this.add(architecture);
		}
		else if(i == 3){
			this.setLayout(null);
			architecture.setSize(30, 120);
			architecture.setLocation(90, 0);
			architecture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			architecture.setLayout(new GridLayout(4, 1));
			this.add(architecture);
		}
		else System.out.println("Error");
		
		this.cost = cost;
		
		this.houseNumber = 0;
		this.owner = "space";
		this.houseBuildCost = 10000;
		
	}
	
	public Grid(String filename, String owner){
		/* no matter?? */
		try {
			img = ImageIO.read(new File(filename));	
	    }
		catch (IOException ex) {
			ex.printStackTrace();
	    }
		
		this.owner = owner;
		
		/* no matter */
		this.cost = 0;
		this.houseNumber = 0;
		this.houseBuildCost = 0;
	}
	
	public void changeOwner(Person person){
		this.owner = person.getName();
		this.ownerObject = person;
		if(person.getColor().equals("RED")) this.setBackground(Color.RED);
		else if(person.getColor().equals("GREEN")) this.setBackground(Color.GREEN);
		else if(person.getColor().equals("CYAN")) this.setBackground(Color.CYAN);
		else if(person.getColor().equals("MAGENTA")) this.setBackground(Color.MAGENTA);
	}
	
	public void BuildHouse(Person person){
		if(this.houseNumber <= 3){
			
			System.out.println("0.0");
			
			JLabel Label1 = new JLabel();
			ImageIcon Icon1 = new ImageIcon("house.jpg");
			Label1.setIcon(Icon1);
			this.architecture.add(Label1);
			
			this.houseNumber ++ ;
			person.setMoney(person.getMoney() - this.houseBuildCost);
		}
		else System.out.println("House layer is over");
	}
	
	public int getTolls(){
		//cost*(house+1)^2
		return this.cost*(this.houseNumber + 1)*(this.houseNumber + 1);
	}
	
	public int getCost(){
		return this.cost;
	}
	
	public String getOwner(){
		return this.owner;
	}
	
	public Person getOwnerObject(){
		return this.ownerObject;
	}
	
	public int houseBuildCost() {
		return this.houseBuildCost;
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
}