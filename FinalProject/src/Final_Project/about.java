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
		JLabel aLabel = new JLabel("�խ�:�թs���A��P���A�i�ʺ��Aù�l��");
		add(aLabel);
		JLabel bLabel = new JLabel("�C�����e:�j�I��");
		add(bLabel);
		JLabel cLabel = new JLabel("����:���C���䴩�R�a��a�M�Y�ǯS���|Ĳ�o�S��\��");
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
