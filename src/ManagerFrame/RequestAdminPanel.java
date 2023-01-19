package ManagerFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RequestAdminPanel extends JPanel {
	
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
	
	private RequestCheckFrame check = new RequestCheckFrame();
	
	// 생성자
	public RequestAdminPanel() {
		setBackground(blueGreen);
		setLayout(null);
		
		for(int i=0; i<nameStr.length; i++) {
			btn[i] = new JButton(nameStr[i]);
			btn[i].setForeground(Color.WHITE);
			btn[i].setFont(sanserifSmall);
			btn[i].setBackground(darkGray);
			btn[i].setBounds(btnX += pX, btnY, btnW, btnH);
			if(i==1) {	// 임시 리스너(추후 수정 필요!!)
				btn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						check.setVisible(true);
					}
					
				});
			}
			this.add(btn[i]);
		}
		
		noticeLa.setForeground(Color.WHITE);
		noticeLa.setFont(sanserifBig);
		noticeLa.setBounds(75, 70, 400, 30);
		this.add(noticeLa);
	}

}
