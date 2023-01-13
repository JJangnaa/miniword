package LoginFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LogInFrame extends JFrame {
	
	// 네이밍 배열
	private String [] nameStr = {"ID", "PW", "LogIn", "search for id/pw", "Join"};
	// ID 및 PW 배열
	private JLabel [] logLabel = new JLabel[2];
	private JTextField [] logTxt = new JTextField[2];
	// BUTTON 배열
	private JButton [] logBtn = new JButton[3];
	
	// 지정 FONT
	private Font sanserifNormal = new Font("SanSerif", Font.BOLD, 40);
	private Font sanserifsmall = new Font("SanSerif", Font.BOLD, 25);
	private Color darkGray = new Color(127, 127, 127);
	private Color lightGray = new Color(242, 242, 242);
	
	// 상단 중앙부분 이미지
	private ImageIcon bulbImage = new ImageIcon("images/bulb.png");
	private JLabel bulbImgLabel = new JLabel(bulbImage);
	
	public LogInFrame() {
		setTitle("MiniWord_LogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(lightGray);
		
		// 네이밍 및 폰트 설정
		for(int i=0; i<nameStr.length; i++) {
			if(i<2) {	// ID 및 PW 라벨 설정 & 컨테이너 부착
				logTxt[i] = new JTextField(10);
				logLabel[i] = new JLabel(nameStr[i]);
				logLabel[i].setFont(sanserifNormal);
				logLabel[i].setForeground(darkGray);
				if(i==0) {
					logTxt[i].setBounds(115, 155, 210, 40);
					logLabel[i].setBounds(40, 155, 40, 40);
				} else if(i==1) {
					logTxt[i].setBounds(115, 225, 210, 40);
					logLabel[i].setBounds(25, 220, 90, 40);
				}
				c.add(logLabel[i]);
				c.add(logTxt[i]);
			} else if(i>1) {	// BUTTON 설정 & 컨테이너 부착
				int index = 0;
				logBtn[index] = new JButton(nameStr[i]);
				logBtn[index].setFont(sanserifsmall);
				logBtn[index].setBackground(darkGray);
				logBtn[index].setForeground(Color.white);
				if(i==2) {
					logBtn[index].setBounds(350, 170, 110, 80);
				} else if(i==3) {
					logBtn[index].setBounds(30, 300, 230, 30);
				} else if(i==4) {
					logBtn[index].setBounds(300, 300, 150, 30);
				}
				c.add(logBtn[index]);
				index++;
			}
		}
		
		setLocation(700, 300);
		setSize(500, 500);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LogInFrame();
	}

}
