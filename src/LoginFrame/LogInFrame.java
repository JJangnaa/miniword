package LoginFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ManagerFrame.ManagerFrame;
import MemberFrame.MemberFrame;

public class LogInFrame extends JFrame {
	
	// 회원가입 및 id/pw 찾기 다이얼로그 객체 생성
	private JoinDialog join;
	private SearchIdPw seaIdPw;
	
	// 네이밍 배열
	private String [] nameStr = {"ID", "PW", "LogIn", "search for id/pw", "Join"};
	// ID 및 PW Label 배열
	private JLabel [] logLabel = new JLabel[2];
	// BUTTON 배열
	private JButton [] logBtn = new JButton[3];
	// ID 및 PW 입력 필드 
	private JTextField idTxt = new JTextField(10);
	private JPasswordField pwTxt = new JPasswordField(10);
	
	// 지정 FONT
	private Font sanserifNormal = new Font("SanSerif", Font.BOLD, 40);
	private Font sanserifsmall = new Font("SanSerif", Font.BOLD, 25);
	private Color darkGray = new Color(127, 127, 127);
	private Color lightGray = new Color(242, 242, 242);
	
	// 상단 중앙부분 이미지 (아직)
//	private ImageIcon bulbImage = new ImageIcon("images/bulb.png");
//	private JLabel bulbImgLabel = new JLabel(bulbImage);
	
	// 로그인 리스너
	private LogInListener listener;
	
	private ManagerFrame managerFrame;
	private MemberFrame memberFrame;

	public LogInFrame() {
		setTitle("MiniWord_LogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(lightGray);
		
		managerFrame = new ManagerFrame(this);
		memberFrame = new MemberFrame(this);
		listener = new LogInListener(this, managerFrame, memberFrame);
		
		// 회원가입 및 id/pw 찾기 다이얼로그 매개변수 입력.
		join = new JoinDialog(this, "Join");
		seaIdPw = new SearchIdPw(this, "search for id/password");
		
		// 네이밍 및 폰트 설정
		for(int i=0; i<nameStr.length; i++) {
			if(i<2) {	// ID 및 PW 라벨 설정 & 컨테이너 부착
				idTxt.setFont(new Font("SanSerif", Font.BOLD, 30));
				pwTxt.setFont(new Font("SanSerif", Font.BOLD, 30));
				logLabel[i] = new JLabel(nameStr[i]);
				logLabel[i].setFont(sanserifNormal);
				logLabel[i].setForeground(darkGray);
				if(i==0) {
					logLabel[i].setBounds(40, 155, 40, 40);
					idTxt.setBounds(115, 155, 210, 40);
					idTxt.addKeyListener(listener);
					c.add(logLabel[i]);
					c.add(idTxt);
				} else if(i==1) {
					logLabel[i].setBounds(25, 220, 90, 40);
					pwTxt.setBounds(115, 225, 210, 40);
					pwTxt.addKeyListener(listener);
					c.add(logLabel[i]);
					c.add(pwTxt);
				}
			} else if(i>1) {	// BUTTON 설정 & 컨테이너 부착
				int index = 0;
				logBtn[index] = new JButton(nameStr[i]);
				logBtn[index].setFont(sanserifsmall);
				logBtn[index].setBackground(darkGray);
				logBtn[index].setForeground(Color.white);
				if(i==2) {
					logBtn[index].setBounds(350, 170, 110, 80);	 // 로그인 버튼
					logBtn[index].addActionListener(listener);
				} else if(i==3) {
					logBtn[index].setBounds(30, 300, 230, 30);	// ID 및 PW 찾기 버튼
					logBtn[index].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							seaIdPw.setVisible(true);
						}
						
					});
				} else if(i==4) {
					logBtn[index].setBounds(300, 300, 150, 30);	// 회원가입 버튼
					logBtn[index].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							join.setVisible(true);
						}
					});
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

	public ManagerFrame getManagerFrame() {
		return managerFrame;
	}

	public MemberFrame getMemberFrame() {
		return memberFrame;
	}
	
	public JTextField getIdTxt() {
		return idTxt;
	}

	public JPasswordField getPwTxt() {
		return pwTxt;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LogInFrame();
	}

}
