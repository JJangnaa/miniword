package ManagerFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import LoginFrame.LogInFrame;
import LoginFrame.LogOutListener;

// 관리자 창 메인프레임
public class ManagerFrame extends JFrame{
	// 탭
	JTabbedPane pane;
	// 단어관리 및 요청사항 패널 객체 생성
	private WordAdminPanel adminPanel;
	private RequestAdminPanel requestPanel;
	// 로그아웃 버튼
	private JButton logOutBtn;
	// 로그인 프레임 객체 생성
	private LogInFrame logInFrame;
	// 로그아웃 리스너
	private LogOutListener logOutListener;
	//생성자 (생성되어 있는 로그인프레임 받기)
	public ManagerFrame(LogInFrame logInFrame) {
		setTitle("Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		this.logInFrame = logInFrame;
		adminPanel = new WordAdminPanel();
		requestPanel = new RequestAdminPanel(logInFrame);
		
		tab();
		
		logOutBtn = new JButton("logout");
		logOutBtn.setBounds(410, 2, 70, 18);
		logOutBtn.setBackground(new Color(191, 191, 191));
		logOutBtn.setForeground(Color.WHITE);
		logOutBtn.setBorderPainted(false);
		
		logOutListener = new LogOutListener(logInFrame, this); //, adminPanel, requestPanel
		logOutBtn.addMouseListener(logOutListener);
		c.add(logOutBtn);
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
		pane.addTab("단어 관리", adminPanel);
		pane.addTab("요청사항", requestPanel);
		return pane; 
	}

	public void setPane(JTabbedPane pane) {
		this.pane = pane;
	}
	public JTabbedPane getPane() {
		return pane;
	}
	public JButton getLogOutBtn() {
		return logOutBtn;
	}
	public WordAdminPanel getAdminPanel() {
		return adminPanel;
	}
	public RequestAdminPanel getRequestPanel() {
		return requestPanel;
	}
	
	// test용 메인메소드
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new ManagerFrame();
//	}

}
