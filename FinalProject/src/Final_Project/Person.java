package Final_Project;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Person extends JPanel implements Serializable{
	
	public static final int SMALL_WIDTH = 300;
	public static final int SMALL_HEIGHT = 200;
	
	private String color;
	private String SmallAvatar;
	private String avatar; //
	private String name;
	private int money;
	private int location; //
	
	private int cooridateX; //
	private int cooridateY; //
	
	private int number; //
	private boolean prison;
	private int prisonCount;
	
	private BufferedImage img;
	
	//private Map map;
	
    JPanel PersonPanelView = new JPanel();
    JLabel Label1 = null;
    JLabel Label2 = null;
	
	public Person(String color, String avatar, String name, int number){
		this.color = color;
		this.avatar = avatar;
		this.name = name;
		this.money = 100000;
		this.location = 0;
		this.number = number;
		this.prison = false;
		this.prisonCount = 0;
		//this.map = map;
		
		this.SmallAvatar = "0"+this.avatar;
		
		try {
			img = ImageIO.read(new File(this.SmallAvatar));	
	    }
		catch (IOException ex) {
			ex.printStackTrace();
	    }
		
		if(this.number == 1){
			this.cooridateX = 30;
			this.cooridateY = 30;
		}else if(this.number == 2){
			this.cooridateX = 60;
			this.cooridateY = 30;
		}else if(this.number == 3){
			this.cooridateX = 30;
			this.cooridateY = 60;
		}else if(this.number == 4){
			this.cooridateX = 60;
			this.cooridateY = 60;
		}else {
			System.out.println("Error");
		}
		
		
		this.setBounds(this.cooridateX, this.cooridateY, 30, 30);
		
	}
	public Person(){
		this.color = "blue";
		this.avatar = "01.jpg";
		this.name = "Little Ti";
		this.money = 100000;
		this.location = 0;
	}
	
	public JPanel getViewListPlanel(){
	    Label1 = new JLabel(this.name);
		ImageIcon Icon1 = new ImageIcon(avatar);
		Label2 = new JLabel(String.valueOf(money));
		ImageIcon Icon2 = new ImageIcon("money.jpg");
		PersonPanelView.setLayout(new GridLayout(2, 1));
		Label1.setIcon(Icon1);
		PersonPanelView.add(Label1);
		Label2.setIcon(Icon2);
		PersonPanelView.add(Label2);
		if (this.color.equals("GREEN")){
			PersonPanelView.setBackground(Color.GREEN);
		}
		else if(this.color.equals("MAGENTA")){
		PersonPanelView.setBackground(Color.MAGENTA);
		}
		else if(this.color.equals("CYAN")){
			PersonPanelView.setBackground(Color.CYAN);
		}
		else PersonPanelView.setBackground(Color.RED);
		return PersonPanelView;
	}
	
	public void Move(int grid, Person person, Map map){		// gird
		for(int i = grid; i > 0;i --){
			if((person.location >= 0 && person.location <= 7) || (person.location == 26)) person.cooridateX += 120;
			else if(person.location >= 8 && person.location <= 12) person.cooridateY += 120;
			else if(person.location >= 13 && person.location <= 20) person.cooridateX -= 120;
			else if(person.location >= 21 && person.location <= 25) person.cooridateY -= 120;
			else {
				System.out.println("Error");
			}
				
			person.setBounds(person.cooridateX, person.cooridateY, 30, 30);	
			person.location ++;
			if(person.location >= 26) person.location -= 26;
		}
		//
		
		person.Visit(map, person);
		
	}

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
    
    public int getMoney(){
    	return money;
    }
    
    public void Build(Grid land){
    	System.out.println("Build");
    	
    	String behavior = "Build";
		(new SignalWindow(land,this,behavior)).setVisible(true);
    	
    	land.BuildHouse(this);
    	this.Label2.setText(String.valueOf(this.getMoney()));
    }
    
    public void BuyLand(Grid land){
    	
    	System.out.println(this.name +" buy " +land.getOwner());
    	
    	String behavior = "Buy";
		(new SignalWindow(land,this,behavior)).setVisible(true);
    	
    	
    	land.changeOwner(this);
    	this.money -= land.getCost();
    	this.Label2.setText(String.valueOf(this.getMoney()));
    }
    
    public void PayTolls(Person person, Grid land){
    	this.money -= land.getTolls();
    	person.setMoney(person.getMoney() + land.getTolls());
    	
    	this.Label2.setText(String.valueOf(this.getMoney()));
    	person.Label2.setText(String.valueOf(person.getMoney()));
    	
    	System.out.println(this.name + " PayTolls to " + person.name);
    	
    	
    }
    
    public void Visit(Map map, Person person){
    	Random rnd = new Random();
    	
    	if((map.grids[person.location].getOwner()).equals("start")){
    		//do nothing
    		Main.Window.rightback1.setIcon(Main.Window.back1);
    	}
    	else if((map.grids[person.location].getOwner()).equals("bank")){
    		Main.Window.rightback1.setIcon(Main.Window.back1);
    	}
    	else if((map.grids[person.location].getOwner()).equals("prison")){
    		Main.Window.rightback1.setIcon(Main.Window.back1);
    	}
    	else if((map.grids[person.location].getOwner()).equals("park")){
    		Main.Window.rightback1.setIcon(Main.Window.back1);
    	}
    	else if((map.grids[person.location].getOwner()).equals("fate")){
    		//fate event
    		int i = rnd.nextInt(6);
    		Fate fateEvent = new Fate(i,person);
    		//person.checkBankrupt();
    	}
    	else if((map.grids[person.location].getOwner()).equals("chance")){
    		//chance event
    		Chance chanceEvent = new Chance(person);
    		//person.checkBankrupt();
    	}
    	else if((map.grids[person.location].getOwner()).equals("space")){
    		//space land
    		String behavior = "BuyOrNot";
    		(new inquiryWindow(map,person,behavior)).setVisible(true);
    	}
    	else if((map.grids[person.location].getOwner()).equals(person.name)){
    		//my land
    		String behavior = "BuildOrNot";
    		(new inquiryWindow(map,person,behavior)).setVisible(true);
    	}
    	else {
    		//„åœ°
    		//System.out.println(person.name + "on "+ map.grids[person.location].getOwner());
    		
    		Person Owner = map.grids[person.location].getOwnerObject();
    		Grid land = map.grids[person.location];
    		person.PayTolls(Owner,land);
    		
    		(new SignalWindow(land,this,Owner)).setVisible(true);
    		
    	}
    	
    }
    
    public void checkBankrupt() {
    	if(this.getMoney() < 0) {
    		Person firstPlace,secondPlace,thirdPlace;
    		Person temp1=null,temp2=null,temp3=null;
    		ImageIcon rupt = new ImageIcon("rupt.jpg");
    		Main.Window.rightback1.setIcon(rupt);
    		Main.Window.rightback1.setBounds(200,150,600,500);
    		switch(this.getNumber()) {
    			case 1: temp1 = Main.Window.person2;
    					temp2 = Main.Window.person3;
    					temp3 = Main.Window.person4;
    					break;
    			case 2: temp1 = Main.Window.person1;
						temp2 = Main.Window.person3;
						temp3 = Main.Window.person4;
						break;
    			case 3: temp1 = Main.Window.person1;
    					temp2 = Main.Window.person2;
						temp3 = Main.Window.person4;
						break;
    			case 4: temp1 = Main.Window.person1;
						temp2 = Main.Window.person2;
						temp3 = Main.Window.person3;
						break;
				default:break;
    		}
    		if(temp1.getMoney() > temp2.getMoney()) {
				if(temp1.getMoney() > temp3.getMoney()) {
					firstPlace = temp1;
					if(temp2.getMoney() > temp3.getMoney()) {
						secondPlace = temp2;
						thirdPlace = temp3;
					}else {
						secondPlace = temp3;
						thirdPlace = temp2;
					}
				}else {
					firstPlace = temp3;
					secondPlace = temp1;
					thirdPlace = temp2;
				}
			}else {
				if(temp2.getMoney() > temp3.getMoney()) {
					firstPlace = temp2;
					if(temp1.getMoney() > temp3.getMoney()) {
						secondPlace = temp1;
						thirdPlace = temp3;
					}else {
						secondPlace = temp3;
						thirdPlace = temp1;
					}
				}else {
					firstPlace = temp3;
					secondPlace = temp2;
					thirdPlace = temp1;
				}
			}
    		(new SignalWindow(this,firstPlace,secondPlace,thirdPlace)).setVisible(true);
    	}
    }
    
    public String getName(){
    	return this.name;
    }
    public String getColor(){
    	return this.color;
    }
    public int getLocate(){
    	return this.location;
    }
    public void setMoney(int money){
    	this.money = money;
    }
    public int getNumber() {
    	return this.number;
    }
    public boolean getPrison(){
    	return this.prison;
    }
    public int getPrisonCount(){
    	return this.prisonCount;
    }
    public void setPrison(){
    	this.prison = true;
    	this.prisonCount++;
    	if(this.prisonCount == 4){
    		this.prison = false;
        	this.prisonCount = 0;
    	}
    }
    public void gotoPrison(){
    	this.cooridateX += 480;
    	this.location = 13;
    	this.setBounds(cooridateX, cooridateY, 30, 30);
    }
    public void gotoPark(){
    	this.cooridateX += 480;
    	this.location = 8;
    	this.setBounds(cooridateX, cooridateY, 30, 30);
    }
    public void gotoBank(){
    	this.cooridateX -= 480;
    	this.location = 21;
    	this.setBounds(cooridateX, cooridateY, 30, 30);
    }
 
    private class inquiryWindow extends JFrame implements ActionListener {
    	private Map map;
    	private Person person;
    	private String behavior = null;
    	
		public inquiryWindow(Map map,Person person,String behavior) {
			this.map = map;
			this.person = person;
			this.behavior = behavior;
			
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setSize(SMALL_WIDTH,SMALL_HEIGHT);
			//getContentPane().setBackground(Color.YELLOW);
			setLayout(new BorderLayout());
			
			JLabel signalLabel = new JLabel();
			if(this.behavior.equals("BuyOrNot")) {
				signalLabel.setText("Do you want to buy this land?");
			}else if(this.behavior.equals("BuildOrNot")) {
				signalLabel.setText("Do you want to build a house?");
			}
			add(signalLabel,BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel();
			//buttonPanel.setBackground(Color.ORANGE);
			buttonPanel.setLayout(new FlowLayout());
			
			JButton yesButton = new JButton("Yes");
			yesButton.addActionListener(this);
			buttonPanel.add(yesButton);
			
			JButton noButton = new JButton("No");
			noButton.addActionListener(this);
			buttonPanel.add(noButton);
			
			add(buttonPanel,BorderLayout.SOUTH);
		}
		
		
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("Yes")) {
				if(this.behavior.equals("BuyOrNot")) {
					person.BuyLand(map.grids[person.getLocate()]);
				}else if(this.behavior.equals("BuildOrNot")) {
					person.Build(map.grids[person.getLocate()]);
				}
				dispose();
				//person.checkBankrupt();
			}else if(actionCommand.equals("No")) {
				dispose();
			}else {
				System.out.println("Unexpected Error in ConfirmWindow.");
			}
		}
	}
    
    private class SignalWindow extends JFrame implements ActionListener {
		
    	private Person tmp = null;
    	
		public SignalWindow(Grid land,Person person,String behavior) {
			tmp = person;
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setSize(SMALL_WIDTH,SMALL_HEIGHT);
			//getContentPane().setBackground(Color.YELLOW);
			setLayout(new BorderLayout());
			
			JLabel signalLabel = new JLabel();
			if(behavior.equals("Buy")) {
				signalLabel.setText(person.getName() + " takes $" + land.getCost() + 
									" to buy " + land.getOwner());
			}else if(behavior.equals("Build")) {
				signalLabel.setText(person.getName() + " takes $" + land.houseBuildCost() + 
									" to build a house");
			}
			add(signalLabel,BorderLayout.CENTER);
			
			JButton signal = new JButton("OK");
			signal.addActionListener(this);
			add(signal,BorderLayout.SOUTH);
		}
		
		public SignalWindow(Grid land,Person payer,Person owner) {
			tmp = payer;
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setSize(SMALL_WIDTH,SMALL_HEIGHT);
			//getContentPane().setBackground(Color.YELLOW);
			setLayout(new BorderLayout());
			
			JLabel signalLabel = new JLabel(payer.getName() + " pays $" +  land.getTolls() + 
											" for " + owner.getName());
			add(signalLabel,BorderLayout.CENTER);
			
			JButton signal = new JButton("OK");
			signal.addActionListener(this);
			add(signal,BorderLayout.SOUTH);
		}
		
		public SignalWindow(Person loser,Person firstPlace,Person secondPlace,Person thirdPlace) {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setSize(500,300);
			//getContentPane().setBackground(Color.YELLOW);
			setLayout(new BorderLayout());
			
			JLabel signalLabel = new JLabel(loser.getName() + " went bankrupt!");
			add(signalLabel,BorderLayout.NORTH);
			
			
			JPanel rank = new JPanel(new GridLayout(5,1));
			
			JLabel title = new JLabel("         RANK");
			rank.add(title);
			JLabel first = new JLabel("First : " + firstPlace.getName() + firstPlace.getMoney());
			rank.add(first);
			JLabel second = new JLabel("Second : " + secondPlace.getName() + secondPlace.getMoney());
			rank.add(second);
			JLabel third = new JLabel("Third : " + thirdPlace.getName() + thirdPlace.getMoney());
			rank.add(third);
			JLabel fourth = new JLabel("Fourth : " + loser.getName() + loser.getMoney());
			rank.add(fourth);
			add(rank,BorderLayout.CENTER);
			
			JButton signal = new JButton("Close");
			signal.addActionListener(this);
			add(signal,BorderLayout.SOUTH);
		}
		
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("OK")) {
				dispose();
				Main.Window.rightback1.setIcon(Main.Window.back1);
				tmp.checkBankrupt();
			}else if(actionCommand.equals("Close")){
				System.exit(0);
			}else{
				System.out.println("Unexpected Error in ConfirmWindow.");
			}
		}
	}
}