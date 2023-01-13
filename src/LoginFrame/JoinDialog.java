package LoginFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinDialog extends JDialog {
	
	// 위치 설정 위한 변수설정
	private int y=0, widthNx=170;
	private int txtHeight=32, pwTxtX=170, pwTxtPlusX=73, pwTxtTmpX = 0;
	
	// 네이밍 배열
	private String [] nameStr = {"Profile Photo", "*Name", "*ID", "*PW", "      *└check", "Phone", "select", "중복확인", "ID 중복확인", "JOIN"};
	// 라벨 배열
	private JLabel [] logLabel = new JLabel[6];
	// 텍스트필드 배열
	private JTextField [] joinTxt = new JTextField[5];
	// PW 필드 배열
	private JPasswordField [] pwJoinTxt = new JPasswordField[2];
	// 버튼 배열
	private JButton [] joinBtn = new JButton[4];
	// 안내문구 라벨
	private JLabel noticeLabel = new JLabel("* 필수 작성");
	private JLabel warnLabel = new JLabel(">> 비밀번호 확인");
	
	// 지정 FONT
	private Font sanserifNormal = new Font("SanSerif", Font.BOLD, 25);
	private Font sanserifsmall = new Font("SanSerif", Font.BOLD, 15);
	private Color darkGray = new Color(127, 127, 127);
	private Color lightGray = new Color(242, 242, 242);
	
	public JoinDialog (JFrame frame, String title) {
		super(frame, title, true);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(lightGray);
		
		for(int i=0; i<nameStr.length; i++) {
			if(i<6) {
				logLabel[i] = new JLabel(nameStr[i]);
				logLabel[i].setFont(sanserifNormal);
				logLabel[i].setForeground(darkGray);
				logLabel[i].setBounds(0, y += 70, widthNx, 25);
				logLabel[i].setHorizontalAlignment(JLabel.CENTER);
				c.add(logLabel[i]);
				if(0<i) {
					joinTxt[i-1] = new JTextField(10);
					joinTxt[i-1].setFont(sanserifNormal);
					joinTxt[i-1].setForeground(darkGray);
					
					if(i==1 || i==2) {
						joinTxt[i-1].setBounds(widthNx, y, widthNx+50, txtHeight);
					} else if(i==3||i==4) {
						joinTxt[i-1].setBounds(pwTxtX += pwTxtTmpX, 420, 68, txtHeight);
						joinTxt[i-1].setHorizontalAlignment(JTextField.CENTER);
						pwTxtTmpX += pwTxtPlusX;
						
						pwJoinTxt[i-3] = new JPasswordField(10);
						pwJoinTxt[i-3].setFont(sanserifNormal);
						pwJoinTxt[i-3].setForeground(darkGray);
						pwJoinTxt[i-3].setBounds(widthNx, y, widthNx+50, txtHeight);
						c.add(pwJoinTxt[i-3]);
					} else if(i==5) {
						
						joinTxt[i-1].setBounds(pwTxtX += pwTxtPlusX, 420, 68, txtHeight);
						joinTxt[i-1].setHorizontalAlignment(JTextField.CENTER);
						// y 값 초기화
						y=0;
					}
					
					c.add(joinTxt[i-1]);
					
				} 
			} else if(i>5) {
				int index = 0;
				joinBtn[index] = new JButton(nameStr[i]);
				joinBtn[index].setFont(sanserifsmall);
				joinBtn[index].setForeground(Color.WHITE);
				
				if(i<9) {
					joinBtn[index].setBackground(darkGray);
					joinBtn[index].setBounds((widthNx*2)+70, y += 70, 150, 30);
				} else if(i==9) {
					noticeLabel.setFont(new Font("SanSerif", Font.BOLD, 12));
					noticeLabel.setForeground(darkGray);
					noticeLabel.setBounds(270, 470, 100, 20);
					c.add(noticeLabel);
					
					warnLabel.setFont(new Font("SanSerif", Font.BOLD, 12));
					warnLabel.setForeground(darkGray);
					warnLabel.setBounds((widthNx*2)+70, 360, 150, 20);
					c.add(warnLabel);
					
					joinBtn[index].setBackground(new Color(0, 32, 96));
					joinBtn[index].setBounds(225, 500, 150, 30);
					joinBtn[index].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							setVisible(false);
//							if(joinTxt[0].getHorizontalVisibility() != null)
						}
						
					});
				}
				
				c.add(joinBtn[index]);
				index++;
			}
		}
		
		c.add(new JLabel("* 필수 작성"));
		
		setLocation(700, 300);
		setSize(600, 600);
		setResizable(true);
//		setVisible(true);
	}
	

}
