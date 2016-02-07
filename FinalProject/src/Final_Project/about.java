package Final_Project;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class about extends JFrame implements ActionListener{

	public static final int WIDTH = 500;
	public static final int HEIGHT = 400;
		
	public about(){
		super();
		setSize(WIDTH,HEIGHT);
		
		setTitle("ABOUT");
		setLayout(new FlowLayout());
		JLabel aLabel = new JLabel("組員:白孟哲，邵致翔，張廷維，羅子勝");
		add(aLabel);
		JLabel bLabel = new JLabel("遊戲內容:大富翁");
		add(bLabel);
		JLabel cLabel = new JLabel("說明:本遊戲支援買地賣地和某些特殊格會觸發特殊功能");
		add(cLabel);
		JButton endButton = new JButton("Close ABOUT");
		endButton.addActionListener(this);
		
		add(endButton);
	}
	
	public void actionPerformed(ActionEvent e){
		String buttonString = e.getActionCommand();
			
			dispose();
		
	}
}
