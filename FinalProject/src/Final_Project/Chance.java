package Final_Project;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.Random;
import javax.swing.*;

public class Chance extends JFrame{
	
	private JLabel Chancelabel = null;
	//private JFrame Frame1 = null;
	public Person forcheck = null;
	
	public Chance(Person person){
		
	    Random ran = new Random();
	    int i = ran.nextInt(6);
		setSize(800,200);
		setTitle("Chance Event");
		forcheck = person;
		
		/****************************************
		 * Advantage event 
		 * i = 0 player go to park & +2000$
		 * i = 1 player +5000$
		 * i = 2 other players -2000$
		 * i = 3 player move again
		 * i = 4 next player can't move once
		 * i = 5 next player -9000$
		 ****************************************/
		
		switch(i){
			case 0:
				Chancelabel = new JLabel(person.getName()+" Go to park & find 2000 dollars",JLabel.CENTER);
				Chancelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				person.gotoPark();
				person.setMoney(person.getMoney() + 2000);
				person.Label2.setText(String.valueOf(person.getMoney()));
				break;
			case 1:
				Chancelabel = new JLabel(person.getName()+" Get 5000 dollars",JLabel.CENTER);
				Chancelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				person.setMoney(person.getMoney() + 5000);
				person.Label2.setText(String.valueOf(person.getMoney()));
				break;
			case 2:
				Chancelabel = new JLabel("Other players lose 2000 dollars",JLabel.CENTER);
				Chancelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				Main.Window.person1.setMoney(Main.Window.person1.getMoney() -2000);
				Main.Window.person1.Label2.setText(String.valueOf(Main.Window.person1.getMoney()));
				Main.Window.person2.setMoney(Main.Window.person2.getMoney() -2000);
				Main.Window.person2.Label2.setText(String.valueOf(Main.Window.person2.getMoney()));
				Main.Window.person3.setMoney(Main.Window.person3.getMoney() -2000);
				Main.Window.person3.Label2.setText(String.valueOf(Main.Window.person3.getMoney()));
				Main.Window.person4.setMoney(Main.Window.person4.getMoney() -2000);
				Main.Window.person4.Label2.setText(String.valueOf(Main.Window.person4.getMoney()));
				person.setMoney(person.getMoney() + 2000);
				person.Label2.setText(String.valueOf(person.getMoney()));
				Main.Window.person1.checkBankrupt();
				Main.Window.person2.checkBankrupt();
				Main.Window.person3.checkBankrupt();
				Main.Window.person4.checkBankrupt();
				break;
			case 3:
				Chancelabel = new JLabel(person.getName()+" Get a chance to move again",JLabel.CENTER);
				Chancelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				MyFrame.Next = person;
				break;
			case 4:
				Chancelabel = new JLabel("Skip next player",JLabel.CENTER);
				Chancelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				if(person.equals(Main.Window.person1)) MyFrame.Next = Main.Window.person3;
				else if(person.equals(Main.Window.person2)) MyFrame.Next = Main.Window.person4;
				else if(person.equals(Main.Window.person3)) MyFrame.Next = Main.Window.person1;
				else if(person.equals(Main.Window.person4)) MyFrame.Next = Main.Window.person2;
				break;
			case 5:
				Chancelabel = new JLabel("Next player lose 9000 dollars",JLabel.CENTER);
				Chancelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				if(person.equals(Main.Window.person1)){
					Main.Window.person2.setMoney(Main.Window.person2.getMoney() -9000);
					Main.Window.person2.Label2.setText(String.valueOf(Main.Window.person2.getMoney()));
					Main.Window.person2.checkBankrupt();
				}
				else if(person.equals(Main.Window.person2)){
					Main.Window.person3.setMoney(Main.Window.person3.getMoney() -9000);
					Main.Window.person3.Label2.setText(String.valueOf(Main.Window.person3.getMoney()));
					Main.Window.person3.checkBankrupt();
				}
				else if(person.equals(Main.Window.person3)){
					Main.Window.person4.setMoney(Main.Window.person4.getMoney() -9000);
					Main.Window.person4.Label2.setText(String.valueOf(Main.Window.person4.getMoney()));
					Main.Window.person4.checkBankrupt();
				}
				else if(person.equals(Main.Window.person4)){
					Main.Window.person1.setMoney(Main.Window.person1.getMoney() -9000);
					Main.Window.person1.Label2.setText(String.valueOf(Main.Window.person1.getMoney()));
					Main.Window.person1.checkBankrupt();
				}
				break;
			default:
				System.out.println("Chance Error");
				break;
		}
		add(Chancelabel);
		setVisible(true);
		addWindowListener(new CheckOnExit());
	}
	private class CheckOnExit implements WindowListener {
		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			Main.Window.rightback1.setIcon(Main.Window.back1);
		}
		public void windowClosed(WindowEvent e){
			forcheck.checkBankrupt();
		}
		public void windowIconified(WindowEvent e){}
		public void windowDeiconified(WindowEvent e){}
		public void windowActivated(WindowEvent e){}
		public void windowDeactivated(WindowEvent e){}
	}
}