package ManagerFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

// 관리자 창 메인프레임
public class ManagerFrame extends JFrame{
	// 단어관리 및 요청사항 패널 객체 생성
	private WordAdminPanel adminPanel = new WordAdminPanel();
	private RequestAdminPanel requestPanel = new RequestAdminPanel();
	
	//생성자
	public ManagerFrame() {
		setTitle("Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		JTabbedPane pane = tab();
		c.add(pane, BorderLayout.CENTER); 
		
		setLocation(700, 300);
		setSize(500, 500);
		setResizable(false);
		setVisible(true);
	}
	// 탭팬
	private JTabbedPane tab() {
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.add("단어 관리", adminPanel);
		tabPane.add("요청사항", requestPanel);
		return tabPane; 
	}
	// test용 메인메소드
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ManagerFrame();
	}

}
