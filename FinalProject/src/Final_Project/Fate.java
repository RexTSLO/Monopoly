package Final_Project;

import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;

import javax.swing.*;

public class Fate extends JFrame implements Serializable{
	
	private JLabel Fatelabel = null;
	private JFrame Frame2 = null;
	public Person forcheck = null;
	
	public Fate(int i,Person person){
		
		Frame2 = new JFrame();
		Frame2.setSize(1200,200);
		Frame2.setTitle("Fate Event");
		forcheck = person;
		
		/**********************************************************
		 * Disadvantage event 
		 * i = 0 player -2000$
		 * i = 1 player -5000$
		 * i = 2 other players +2000$
		 * i = 3 player go to prison 3 turns
		 * i = 4 next player can move twice
		 * i = 5 player go to bank -4000$ & next player +4000$
		 **********************************************************/
		
		switch(i){
			case 0:
				Fatelabel = new JLabel(person.getName()+" Lose 2000 dollars",JLabel.CENTER);
				Fatelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				person.setMoney(person.getMoney() - 2000);
				person.Label2.setText(String.valueOf(person.getMoney()));
				break;
			case 1:
				Fatelabel = new JLabel(person.getName()+" Lose 5000 dollars",JLabel.CENTER);
				Fatelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				person.setMoney(person.getMoney() - 5000);
				person.Label2.setText(String.valueOf(person.getMoney()));
				break;
			case 2:
				Fatelabel = new JLabel("Other players get 2000 dollars",JLabel.CENTER);
				Fatelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				Main.Window.person1.setMoney(Main.Window.person1.getMoney() +2000);
				Main.Window.person1.Label2.setText(String.valueOf(Main.Window.person1.getMoney()));
				Main.Window.person2.setMoney(Main.Window.person2.getMoney() +2000);
				Main.Window.person2.Label2.setText(String.valueOf(Main.Window.person2.getMoney()));
				Main.Window.person3.setMoney(Main.Window.person3.getMoney() +2000);
				Main.Window.person3.Label2.setText(String.valueOf(Main.Window.person3.getMoney()));
				Main.Window.person4.setMoney(Main.Window.person4.getMoney() +2000);
				Main.Window.person4.Label2.setText(String.valueOf(Main.Window.person4.getMoney()));
				person.setMoney(person.getMoney() - 2000);
				person.Label2.setText(String.valueOf(person.getMoney()));
				break;
			case 3:
				Fatelabel = new JLabel(person.getName()+" Go to prison for 3 turns",JLabel.CENTER);
				Fatelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				person.setPrison();
				person.gotoPrison();
				break;
			case 4:
				Fatelabel = new JLabel("Next player can move twice continuously",JLabel.CENTER);
				Fatelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				MyFrame.TwiceMoving = true;
				break;
			case 5:
				Fatelabel = new JLabel(person.getName()+" Go to bank & pay Next player 4000 dollars",JLabel.CENTER);
				Fatelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
				person.gotoBank();
				person.setMoney(person.getMoney() - 4000);
				person.Label2.setText(String.valueOf(person.getMoney()));
				if(person.equals(Main.Window.person1)){
					Main.Window.person2.setMoney(Main.Window.person2.getMoney() +4000);
					Main.Window.person2.Label2.setText(String.valueOf(Main.Window.person2.getMoney()));
				}
				else if(person.equals(Main.Window.person2)){
					Main.Window.person3.setMoney(Main.Window.person3.getMoney() +4000);
					Main.Window.person3.Label2.setText(String.valueOf(Main.Window.person3.getMoney()));
				}
				else if(person.equals(Main.Window.person3)){
					Main.Window.person4.setMoney(Main.Window.person4.getMoney() +4000);
					Main.Window.person4.Label2.setText(String.valueOf(Main.Window.person4.getMoney()));
				}
				else if(person.equals(Main.Window.person4)){
					Main.Window.person1.setMoney(Main.Window.person1.getMoney() +4000);
					Main.Window.person1.Label2.setText(String.valueOf(Main.Window.person1.getMoney()));
				}
				break;
			default:
				System.out.println("Fate Error");
				break;
		}
		Frame2.add(Fatelabel);
		Frame2.setVisible(true);
		Frame2.addWindowListener(new CheckOnExit());
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

