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

// 관리자 창 메인프레임
public class ManagerFrame extends JFrame{
	// 단어관리 및 요청사항 패널 객체 생성
	private WordAdminPanel adminPanel = new WordAdminPanel();
	private RequestAdminPanel requestPanel = new RequestAdminPanel();
	
	private JButton logOutBtn;
	//생성자
	public ManagerFrame() {
		setTitle("Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		JTabbedPane pane = tab();
		pane.setBounds(0, 0, 500, 500);
		
//		logOutBtn = new JButton("logout");
//		logOutBtn.setBounds(410, 2, 70, 18);
//		logOutBtn.setBackground(new Color(191, 191, 191));
//		logOutBtn.setForeground(Color.WHITE);
//		logOutBtn.setBorderPainted(false);
//		logOutBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				int yesNo = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Are you sure?", JOptionPane.YES_NO_OPTION);
//				if(yesNo == JOptionPane.YES_OPTION) {
//					System.exit(0);
//				}
//			}
//		});
		
//		c.add(logOutBtn);
		c.add(pane); 
		
		setLocation(700, 300);
		setSize(500, 500);
		setResizable(false);
//		setVisible(true);
	}
	// 탭팬
	private JTabbedPane tab() {
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.add("단어 관리", adminPanel);
		tabPane.add("요청사항", requestPanel);
		return tabPane; 
	}
	// test용 메인메소드
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new ManagerFrame();
//	}

}
