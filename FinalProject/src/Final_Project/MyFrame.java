package Final_Project;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.Random;

public class MyFrame extends JFrame implements Serializable{

	/******************************************************************
	 *  static variable                                               *
	 *  frame, lift panel, right panel size                           * 
	 ******************************************************************/
	public static final int WIDTH = 1250;
	public static final int HEIGHT = 720;
	public static final int leftWIDTH = 170;
	public static final int leftHEIGHT = 720;
	public static final int rightWIDTH = 1080;
	public static final int rightHEIGHT = 720;
	
	/******************************************************************
	 *  create a game map                                             * 
	 ******************************************************************/
	public static Map map = new Map();
	
	
	
	/******************************************************************
	 *  create lift right panel                                       *
	 *  create four person                                            * 
	 ******************************************************************/
	public JPanel leftPanel;
	public JLayeredPane rightPanel;
	
	public Person person1 = new Person("GREEN", "01.jpg", "台台兒OuO", 1);
	public Person person2 = new Person("MAGENTA", "02.jpg", "白白兒OuO", 2);
	public Person person3 = new Person("CYAN", "03.jpg", "翔翔兒OuO", 3);
	public Person person4 = new Person("RED", "04.jpg", "勝勝兒OuO", 4);
	
	
	
	
	/******************************************************************
	 *  a button to toss the dice and get number to walk              * 
	 ******************************************************************/
	public JButton diceButton;
	
	/******************************************************************
	 *  now object present who can toss dice                          *
	 *  next is the next one to toss dice                             * 
	 ******************************************************************/
	public Person Now = person1;
	public static Person Next;
	
	/*********************************
	 * Fate Event 4, move twice
	 *********************************/
	public static boolean TwiceMoving = false;
	
	public ImageIcon dice1 = new ImageIcon("dice1.png");
	public ImageIcon dice2 = new ImageIcon("dice2.png");
	public ImageIcon dice3 = new ImageIcon("dice3.png");
	public ImageIcon dice4 = new ImageIcon("dice4.png");
	public ImageIcon dice5 = new ImageIcon("dice5.png");
	public ImageIcon dice6 = new ImageIcon("dice6.png");
	
	public JLabel rightback1;
	ImageIcon back1 = new ImageIcon("movingdice.gif");
	
	
	
	
	
	/**/
	//protected JButton[] buyButton = new JButton[2];
	
	
	 //JLayeredPane pane = new JLayeredPane();
	
	
	
	/******************************************************************
	 *  MyFrame constructor                                           * 
	 ******************************************************************/
	public MyFrame()
	{
		/******************************************************************
		 *  frame initial                                                 *
		 *  setting frame size                                            *
		 *  setting program title                                         *
		 *  setting lowest level layout using border layout               *
		 *  create biggerPanel on border layout                           *
		 *  setting border layout on biggerLayout                         *
		 ******************************************************************/
		super();
		setSize(WIDTH, HEIGHT);
		setTitle("Millionare");
		setLayout(new BorderLayout());
		JPanel biggerPanel = new JPanel();
		biggerPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		biggerPanel.setLayout(new BorderLayout());
		
		/******************************************************************
		 *  create liftPanel                                              *
		 *  setting size and layout                                       *
		 *  add person1~4                                                 *
		 *  finally, add inside biggerPanel on west                       *
		 ******************************************************************/
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(leftWIDTH, leftHEIGHT));
		leftPanel.setLayout(new GridLayout(4, 1));
		leftPanel.add(person1.getViewListPlanel());
		leftPanel.add(person2.getViewListPlanel());
		leftPanel.add(person3.getViewListPlanel());
		leftPanel.add(person4.getViewListPlanel());
		biggerPanel.add(leftPanel, BorderLayout.WEST);
		
		/******************************************************************
		 *  create rightPanel                                             *
		 *  setting panel size                                            *
		 *  setting null layout (we can arrange our component ourself)    *
		 ******************************************************************/
		rightPanel = new JLayeredPane();
		rightPanel.setPreferredSize(new Dimension(rightWIDTH, rightHEIGHT));
		rightPanel.setLayout(null);
		ImageIcon back = new ImageIcon("cat.gif");
		JLabel rightback = new JLabel(back);
		rightPanel.add(rightback,2,0);
		rightback.setBounds(120,120,840,480);
		
		rightback1 = new JLabel(back1);
		rightPanel.add(rightback1,3,0);
		rightback1.setBounds(360,170,400,400);
		
		/******************************************************************
		 *  add person into map                                           *
		 ******************************************************************/
		rightPanel.add(person1, 2, 0);
		rightPanel.add(person2, 2, 0);
		rightPanel.add(person3, 2, 0);
		rightPanel.add(person4, 2, 0);
		
		/******************************************************************
		 *  create a button and get it a listener to do what it should do *
		 *  and locate where is it                                        *
		 ******************************************************************/
		diceButton = new JButton("Start");
		diceButton.addActionListener(new DiceListener());
		
		rightPanel.add(diceButton, 2, 0);
		this.diceButton.setBounds(520, 300, 100, 100);
		
		/******************************************************************
		 *  add into right panel                                          *
		 ******************************************************************/

		rightPanel.add(map, 1, 0);
		
		/******************************************************************
		 *  add right panel into bigger panel                             *
		 ******************************************************************/
		biggerPanel.add(rightPanel, BorderLayout.EAST);
		add(biggerPanel, BorderLayout.CENTER);
	}
	
	/******************************************************************
	 *  dice button listener                                          *
	 ******************************************************************/
	public class DiceListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Random ran = new Random();
			int toss = ran.nextInt(6) + 1;
			if(toss == 1) {
				rightback1.setIcon(dice1);
			}else if(toss == 2) {
				rightback1.setIcon(dice2);
			}else if(toss == 3) {
				rightback1.setIcon(dice3);
			}else if(toss == 4) {
				rightback1.setIcon(dice4);
			}else if(toss == 5) {
				rightback1.setIcon(dice5);
			}else if(toss == 6) {
				rightback1.setIcon(dice6);
			}
			/*for(int i=0;i<=60000;i++) {
				for(int j=0;j<=60000;j++) {
					
				}
			}*/
			//rightback1.setIcon(back1);
			
			if(TwiceMoving)	
				TwiceMoving = false;
			else{
				if(Now.equals(person1)) Next = person2;
				else if(Now.equals(person2)) Next = person3;
				else if(Now.equals(person3)) Next = person4;
				else if(Now.equals(person4)) Next = person1;
			}
			
			/*******************************************
			 * Check if the player is in the prison,
			 * if he does then he can't move this turn. 
			 *******************************************/
			if(Now.getPrison()){
				diceButton.setText("Pass "+String.valueOf(Now.getPrisonCount())+" turns");
				Now.setPrison(); 
			}
			else{
				diceButton.setText(String.valueOf(toss)+" steps");
				Now.Move(toss, Now, map);
			}
			
			Now = Next;
		}
	}
}