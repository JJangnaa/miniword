package LoginFrame;

import java.awt.Color;
import java.awt.Container;
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
	private int y=30, widthNx=170;
	private int txtHeight=32, pwTxtX=170, pwTxtPlusX=73, pwTxtTmpX = 0;
	
	// 네이밍 배열
	private String [] nameStr = {"*Name", "*ID", "*PW", "      *└check", "Phone"};
	private String [] noticeStr = {"", "", "영문+특수문자+숫자 8자↑", ">> 비밀번호 확인", "* 필수 작성"};
	
	// 라벨 배열
	private JLabel [] logLabel = new JLabel[5];
	// 텍스트필드 배열
	private JTextField [] joinTxt = new JTextField[5];
	// PW 필드 배열
	private JPasswordField [] pwJoinTxt = new JPasswordField[2];
	// 버튼
	private JButton dupBtn;
	private JButton idDupBtn;
	private JButton joinBtn;
	private JButton resetBtn;
	// 안내문구 라벨
	private JLabel [] noticeLabel = new JLabel[5];
	
	// 지정 FONT
	private Font sanserifNormal = new Font("SanSerif", Font.BOLD, 25);
	private Font sanserifsmall = new Font("SanSerif", Font.BOLD, 15);
	private Font sanserifnotice = new Font("SanSerif", Font.ITALIC, 12);
	private Color darkGray = new Color(127, 127, 127);
	private Color lightGray = new Color(242, 242, 242);
	private Color navy = new Color(0, 32, 96);
	
	// 리스너 객체 생성
	private ButtonClickListener listener;
	private PwListener pwListener = new PwListener(pwJoinTxt, joinTxt, noticeLabel);
	private PhoneListener phoneListener = new PhoneListener(joinTxt);
	
	public JoinDialog (JFrame frame, String title) {
		super(frame, title, true);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(lightGray);
		
		dupBtn = new JButton("중복확인");
		dupBtn.setFont(sanserifsmall);
		dupBtn.setForeground(Color.WHITE);
		dupBtn.setBackground(darkGray);
		dupBtn.setBounds(410, 100, 150, 30);
		
		idDupBtn = new JButton("ID 중복확인");
		idDupBtn.setFont(sanserifsmall);
		idDupBtn.setForeground(Color.WHITE);
		idDupBtn.setBackground(darkGray);
		idDupBtn.setBounds(410, 170, 150, 30);
		
		joinBtn = new JButton("JOIN");
		joinBtn.setFont(sanserifsmall);
		joinBtn.setBackground(navy);
		joinBtn.setForeground(Color.WHITE);
		joinBtn.setBounds(225, 460, 150, 30);
		
		resetBtn = new JButton("reset");
		resetBtn.setFont(new Font("SanSerif", Font.BOLD, 12));
		resetBtn.setBackground(navy);
		resetBtn.setForeground(Color.WHITE);
		resetBtn.setBounds(263, 495, 72, 20);
		
		listener = new ButtonClickListener(this);
		dupBtn.addActionListener(listener);
		idDupBtn.addActionListener(listener);
		joinBtn.addActionListener(listener);
		resetBtn.addActionListener(listener);
		
		for(int i=0; i<nameStr.length; i++) {
			logLabel[i] = new JLabel(nameStr[i]);
			logLabel[i].setFont(sanserifNormal);
			logLabel[i].setForeground(darkGray);
			logLabel[i].setBounds(0, y += 70, widthNx, 25);
			logLabel[i].setHorizontalAlignment(JLabel.CENTER);
			c.add(logLabel[i]);
			
			joinTxt[i] = new JTextField(10);
			joinTxt[i].setFont(sanserifNormal);
			joinTxt[i].setForeground(darkGray);
			
			if(i<2) {	// Name 및 ID 텍스트필드
				joinTxt[i].setBounds(widthNx, y, widthNx+50, txtHeight);
				joinTxt[i].addKeyListener(listener);	// 리스너
			} else if(i==2||i==3) {	
				// Phone 1~2 텍스트필드
				joinTxt[i].setBounds(pwTxtX += pwTxtTmpX, 380, 68, txtHeight);
				joinTxt[i].setHorizontalAlignment(JTextField.CENTER);
				pwTxtTmpX += pwTxtPlusX;
				// Pw & check 패스워드필드
				pwJoinTxt[i-2] = new JPasswordField(10);
				pwJoinTxt[i-2].setFont(sanserifNormal);
				pwJoinTxt[i-2].setForeground(darkGray);
				pwJoinTxt[i-2].setBounds(170, y, widthNx+50, txtHeight);
				pwJoinTxt[i-2].addKeyListener(pwListener);
				c.add(pwJoinTxt[i-2]);
			} else if(i==4) {
				// Phone 3번째 텍스트 필드 
				joinTxt[i].setBounds(pwTxtX += pwTxtPlusX, 380, 68, txtHeight);
				joinTxt[i].setHorizontalAlignment(JTextField.CENTER);
			}
			c.add(joinTxt[i]);
		}
		// 재사용 위해 y 값 초기화
		y = 60;
		// 안내라벨 배열
		for(int i=0; i<noticeStr.length; i++) {
			noticeLabel[i] = new JLabel(noticeStr[i]);
			noticeLabel[i].setFont(sanserifnotice);
			noticeLabel[i].setForeground(darkGray);
			if(i<4) {
				if(i<2) {
					// name 및 id 중복확인 텍스트라벨
					noticeLabel[i].setBounds((widthNx*2)+70, y+=70, 150, 20);
//					noticeLabel[i].setVisible(false);
				} else {
					// 비밀번호 안내 텍스트 라벨
					noticeLabel[i].setBounds((widthNx*2)+70, y+=58, 150, 20);
					noticeLabel[i].addKeyListener(pwListener);
				}
			}
			if(i==4) {
				// 필수작성 텍스트라벨
				noticeLabel[i].setFont(sanserifnotice);
				noticeLabel[i].setForeground(darkGray);
				noticeLabel[i].setBounds(270, 430, 100, 20);
			}
			c.add(noticeLabel[i]);
		}
		
		// phone 텍스트필드 설정 및 리스너 붙이기
		joinTxt[2].setText("010");
		joinTxt[2].setEditable(false);
		joinTxt[3].addKeyListener(phoneListener);
		joinTxt[4].addKeyListener(phoneListener);
		
		c.add(dupBtn);
		c.add(idDupBtn);
		c.add(joinBtn);
		c.add(resetBtn);
		
		setLocation(700, 300);
		setSize(600, 600);
		setResizable(true);
	}

	public JTextField[] getJoinTxt() {
		return joinTxt;
	}

	public JPasswordField[] getPwJoinTxt() {
		return pwJoinTxt;
	}

	public void setPwJoinTxt(JPasswordField[] pwJoinTxt) {
		this.pwJoinTxt = pwJoinTxt;
	}

	public JLabel[] getNoticeLabel() {
		return noticeLabel;
	}

	public JButton getDupBtn() {
		return dupBtn;
	}

	public JButton getIdDupBtn() {
		return idDupBtn;
	}
	

}
