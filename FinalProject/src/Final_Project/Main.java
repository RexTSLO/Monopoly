package Final_Project;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Main implements Serializable {
	public static final int SMALL_WIDTH = 200;
	public static final int SMALL_HEIGHT = 100;
	
	public static MyFrame Window = new MyFrame();
	
	public Main() {
		Window.pack();
		Window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Window.setVisible(true);
		Window.addWindowListener(new CheckOnExit());
	}
	
	private class CheckOnExit implements WindowListener {
		public void windowOpened(WindowEvent e) {
			//LoadWindow reminder = new LoadWindow();
			//reminder.setVisible(true);
		}
		
		public void windowClosing(WindowEvent e) {
			ConfirmWindow checkers = new ConfirmWindow();
			checkers.setVisible(true);
		}
		
		public void windowClosed(WindowEvent e){}
		public void windowIconified(WindowEvent e){}
		public void windowDeiconified(WindowEvent e){}
		public void windowActivated(WindowEvent e){}
		public void windowDeactivated(WindowEvent e){}
	}
	
	private class ConfirmWindow extends JFrame implements ActionListener {
		public ConfirmWindow() {
			setSize(SMALL_WIDTH,SMALL_HEIGHT);
			getContentPane().setBackground(Color.YELLOW);
			setLayout(new BorderLayout());
			
			JLabel confirmLabel = new JLabel("Are you sure you want to exit?");
			add(confirmLabel,BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.ORANGE);
			buttonPanel.setLayout(new FlowLayout());
			
			JButton exitButton = new JButton("Yes");
			exitButton.addActionListener(this);
			buttonPanel.add(exitButton);
			
			JButton cancelButton = new JButton("No");
			cancelButton.addActionListener(this);
			buttonPanel.add(cancelButton);
			
			add(buttonPanel,BorderLayout.SOUTH);
		}
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("Yes")) {
				dispose();
				(new SaveWindow()).setVisible(true);
				//System.exit(0);
			}else if(actionCommand.equals("No")) {
				dispose();
			}else {
				System.out.println("Unexpected Error in ConfirmWindow.");
			}
		}
	}
	private class SaveWindow extends JFrame implements ActionListener {
		public SaveWindow() {
			setSize(SMALL_WIDTH,SMALL_HEIGHT);
			getContentPane().setBackground(Color.YELLOW);
			setLayout(new BorderLayout());
			
			JLabel saveLabel = new JLabel("Do you want to save?");
			add(saveLabel,BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.ORANGE);
			buttonPanel.setLayout(new FlowLayout());
			
			JButton saveButton = new JButton("Yes");
			saveButton.addActionListener(this);
			buttonPanel.add(saveButton);
			
			JButton cancelButton = new JButton("No");
			cancelButton.addActionListener(this);
			buttonPanel.add(cancelButton);
			
			add(buttonPanel,BorderLayout.SOUTH);
		}
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("Yes")) {
				try { 
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Game.obj"));
					oos.writeObject(Window.map);
					oos.writeObject(Window.leftPanel);
					oos.writeObject(Window.rightPanel);
					oos.writeObject(Window.person1);
					oos.writeObject(Window.person2);
					oos.writeObject(Window.person3);
					oos.writeObject(Window.person4);
					oos.writeObject(Window.diceButton);
					oos.writeObject(Window.Now);
					oos.writeObject(Window.Next);
					oos.writeObject(Window.TwiceMoving);
					oos.flush();
					oos.close();
					System.out.println("Successfully saved!");
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}else if(actionCommand.equals("No")) {
				System.exit(0);
			}else {
				System.out.println("Unexpected Error in ConfirmWindow.");
			}
		}
	}
	private class LoadWindow extends JFrame implements ActionListener {
		public LoadWindow() {
			setSize(SMALL_WIDTH,SMALL_HEIGHT);
			getContentPane().setBackground(Color.YELLOW);
			setLayout(new BorderLayout());
			
			JLabel loadLabel = new JLabel("Do you want to load last record?");
			add(loadLabel,BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.ORANGE);
			buttonPanel.setLayout(new FlowLayout());
			
			JButton loadButton = new JButton("Yes");
			loadButton.addActionListener(this);
			buttonPanel.add(loadButton);
			
			JButton cancelButton = new JButton("No");
			cancelButton.addActionListener(this);
			buttonPanel.add(cancelButton);
			
			add(buttonPanel,BorderLayout.SOUTH);
		}
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("Yes")) {
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Game.obj"));
					Window = (MyFrame)ois.readObject();
					ois.close();
					System.out.println("Successfully Loaded");
				}catch(Exception e2) {
					System.out.println("No records!");
					e2.printStackTrace();
				}
				dispose();
			}else if(actionCommand.equals("No")) {
				//System.exit(0);
				dispose();
			}else {
				System.out.println("Unexpected Error in ConfirmWindow.");
			}
		}
	}
	
}