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
import LoginFrame.LogOutListener;

public class MemberFrame extends JFrame{
	
	private JTabbedPane pane;
	
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

	private LogInFrame logInFrame;
	private LogOutListener logOutListener;
	
	public MemberFrame(LogInFrame logInFrame) {
		this.logInFrame = logInFrame;
		
		setTitle("MiniWord");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		gamePanel = new GamePanel();
		memberPanel = new MemberPanel(this, gamePanel);
		
		tab();
		
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
		
		logOutListener = new LogOutListener(logInFrame, this); //, memberPanel, gamePanel
		logoutBtn.addActionListener(logOutListener);
		
		c.add(seperateLa1);
		c.add(seperateLa2);
		
		c.add(nameLabel);
		c.add(gradeLabel);
		c.add(gradeTxt);
		c.add(logoutBtn);
		
		c.add(tab()); 
		
		setLocation(700, 300);
		setSize(500, 500);
		setResizable(false);
//		setVisible(true);
		
	}
	
	// 탭팬
	private JTabbedPane tab() {
		pane = new JTabbedPane();
		pane.setBounds(0, 0, 500, 500);
		pane.addTab("단어검색", memberPanel);
		pane.addTab("미니게임", gamePanel);
		
		return pane; 
		
	}
	
	public JTabbedPane getPane() {
		return pane;
	}
	
	public void setLogoutBtn(JButton logoutBtn) {
		this.logoutBtn = logoutBtn;
	}
	
	public JButton getLogoutBtn() {
		return logoutBtn;
	}

	public void setNameLabel(String name) {
		this.nameLabel.setText(name);
	}
	
	public JLabel getNameLabel() {
		return nameLabel;
	}
	
	public void setGradeTxt(JTextField gradeTxt) {
		this.gradeTxt = gradeTxt;
	}
	
	public JTextField getGradeTxt() {
		return gradeTxt;
	}
	// test용 메인메소드
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new MemberFrame();
//	}

	public MemberPanel getMemberPanel() {
		return memberPanel;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

}
