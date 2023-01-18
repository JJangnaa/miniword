package ManagerFrame;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class ManagerFrame extends JFrame{
	
	public ManagerFrame() {
		setTitle("Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("test"));
		
		setLocation(700, 300);
		setSize(500, 500);
		setResizable(false);
	}
	
	// test용 메인메소드
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
