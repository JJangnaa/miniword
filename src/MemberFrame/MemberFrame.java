package MemberFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import LoginFrame.LogInFrame;
import LoginFrame.LogInListener;

public class MemberFrame extends JFrame{
	
	private MemberPanel memberPanel;
	private GamePanel gamePanel;
	
	private JLabel nameLabel;
	private JLabel gradeLabel;
	private JTextField gradeTxt;

	private JButton logoutBtn;
	private JLabel seperateLa1 = new JLabel("|");
	private JLabel seperateLa2 = new JLabel("|");
	
	private Color darkGray = new Color(127, 127, 127);
	private Color littlelightGray = new Color(191, 191, 191);
	private Color lightGray = new Color(210, 210, 210);
	
	public MemberFrame() {
		setTitle("MiniWord");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		memberPanel = new MemberPanel();
		gamePanel = new GamePanel();
		
		JTabbedPane pane = tab();
		pane.setBounds(0, 0, 500, 500);
		
		nameLabel = gamePanel.getNameLabel();
		
		seperateLa1.setBounds(304, 0, 3, 20);
		seperateLa1.setForeground(lightGray);
		
		gradeLabel = gamePanel.getGradeLabel();
		gradeTxt = gamePanel.getGradeTxt();
		
		seperateLa2.setBounds(399, 0, 3, 20);
		seperateLa2.setForeground(lightGray);
		// 리스너 달아야함
		logoutBtn = new JButton("logout");
		logoutBtn.setBounds(410, 2, 70, 18);
		logoutBtn.setBackground(littlelightGray);
		logoutBtn.setForeground(Color.WHITE);
		
		c.add(seperateLa1);
		c.add(seperateLa2);
		
		c.add(nameLabel);
		c.add(gradeLabel);
		c.add(gradeTxt);
		c.add(logoutBtn);
		
		c.add(pane); 
		
		setLocation(700, 300);
		setSize(500, 500);
		setResizable(false);
//		setVisible(true);
		
	}
	
	// 탭팬
	private JTabbedPane tab() {
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.add("단어검색", memberPanel);
		tabPane.add("미니게임", gamePanel);
		return tabPane; 
		
	}
	
	public void setNameLabel(String name) {
		this.nameLabel.setText(name);
	}
	public JLabel getNameLabel() {
		return nameLabel;
	}
	public JTextField getGradeTxt() {
		return gradeTxt;
	}
	// test용 메인메소드
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new MemberFrame();
//	}

}
