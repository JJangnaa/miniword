package ManagerFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import LoginFrame.LogInFrame;

// 요청사항 패널
public class RequestAdminPanel extends JPanel {
	// 요청사항 불러오기 위함.
	private DBListPanel wordList = new DBListPanel("request");
	// 지정 폰트 및 컬러
	private Color blueGreen = new Color(50, 142, 160);
	private Color darkGray = new Color(127, 127, 127);
	private Font sanserifBig = new Font("SanSerif", Font.BOLD, 25);
	private Font sanserifSmall = new Font("SanSerif", Font.BOLD, 15);
	// 배열로 넣기
	private String [] nameStr = {"삭제", "확인"};
	private JButton [] btn = new JButton[2];
	// 버튼 위치
	private int btnX = 230, btnY = 320, btnW = 65, btnH = 25;
	private int pX = 70;
	// 안내 라벨
	private JLabel noticeLa = new JLabel("⚠ 요청 사항 확인 후 진행 ⚠");
	// 상세 요청사항 확인가능한 프레임
	private RequestCheckDialog checkDialog;
	// 요청사항 리스트 클릭 및 버튼 클릭시의 리스너
	private RequestTableClickListener listener;
	
	private LogInFrame logInFrame;
	
	// 생성자
	public RequestAdminPanel(LogInFrame logInFrame) {
		
		this.logInFrame = logInFrame;
		
		setBackground(blueGreen);
		setLayout(null);
		
		for(int i=0; i<nameStr.length; i++) {
			btn[i] = new JButton(nameStr[i]);
			btn[i].setForeground(Color.WHITE);
			btn[i].setFont(sanserifSmall);
			btn[i].setBackground(darkGray);
			btn[i].setBounds(btnX += pX, btnY, btnW, btnH);
			
			this.add(btn[i]);
		}
		noticeLa.setForeground(Color.WHITE);
		noticeLa.setFont(sanserifBig);
		noticeLa.setBounds(75, 70, 400, 30);
		
		checkDialog = new RequestCheckDialog(logInFrame, this);
		listener = new RequestTableClickListener(this, checkDialog);
		
		wordList.setBounds(25, 110, 420, 200);
		wordList.getTable().addMouseListener(listener);
		
		btn[0].addActionListener(listener);
		btn[1].addActionListener(listener);
		
		this.add(wordList);
		this.add(noticeLa);
	}
	public DBListPanel getWordList() {
		return wordList;
	}
	public RequestCheckDialog getCheckDialog() {
		return checkDialog;
	}
	public JButton[] getBtn() {
		return btn;
	}

}
