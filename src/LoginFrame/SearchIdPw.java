package LoginFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SearchIdPw extends JDialog {
	
	// 리스너 객체 생성
	private SearchIdPwListener listener;
	private SearchInfoListener infoListener;
	// 네이밍 문자 배열
	private String[] nameStr = {" * 찾는 정보 선택: ", " - name? ", " - ID? ", "-",/**/ " PW ", " ID ",/**/ "search", "close"};
	// nameStr[0]~[3]
	private JLabel [] QnALabel = new JLabel[4];
	// nameStr[4]~[5] 및 버튼 그룹
	private JRadioButton [] selIdPwBtn = new JRadioButton[2];
	private ButtonGroup radioBtnGroup = new ButtonGroup();
	
	// name 및 id 입력할 텍스트필드 배열
	private JTextField [] wantTxt = new JTextField[2];
	// nameStr[6]~[7]
	private JButton [] btn = new JButton[2];
	
	// 위치 조정 위한 변수 (추후.. 보완 해보기...)
	private int fixX = 40, laY = 0, fixW = 150, fixH = 20;
	private int fixBtnX = 210, fixBtnY = 40, fixBtnW = 80, fixBtnH = 20;
	private int fixTxtX = 130, fixTxtY = 83, fixTxtW = 150, fixTxtH = 20; 
	private int chX = 90, chY = 40, chTxtY = 39, ch = 43;
	private int index = 0;
	
	// 지정 FONT
	private Font sanserifNormal = new Font("SanSerif", Font.BOLD, 17);
	private Font sanserifsmall = new Font("SanSerif", Font.BOLD, 15);
	private Font sanserifnotice = new Font("SanSerif", Font.ITALIC, 10);
	private Color darkGray = new Color(127, 127, 127);
	private Color lightGray = new Color(242, 242, 242);
	private Color navy = new Color(0, 32, 96);
		
	public SearchIdPw(JFrame frame, String title) {
		super(frame, title, true);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(lightGray);
		
		listener = new SearchIdPwListener(selIdPwBtn, QnALabel, wantTxt, btn, this);
		infoListener = new SearchInfoListener(QnALabel, wantTxt);
		
		for(int i=0; i<nameStr.length; i++) {
			// *name 라벨 및 텍스트필드 y값 변경 필요 (리스너를 통해 위치변경 필요 << id visible이 true가 될 경우 위로 조금 움직여줘야 함.)
			// *id 라벨 및 텍스트필드는 setVisible(false)로 설정 필요 (리스너를 통해 true로 변경 필요)
			if(i<4) {
				QnALabel[i] = new JLabel(nameStr[i]);
				QnALabel[i].setFont(sanserifNormal);
				QnALabel[i].setForeground(darkGray);
				if(i==3) {
					// 찾는 값 나타내는 라벨 (리스너 부착후 리스너 내에서 setText 하여 내용변경 필요!)
					QnALabel[i].setBounds(40, laY += chY, 360, fixH);
					QnALabel[i].setHorizontalAlignment(JLabel.CENTER);
				} else {
					// Q 라벨(찾는정보?, name?, id?)
					QnALabel[i].setBounds(fixX, laY += chY, fixW, fixH);
					// name 라벨 위치: 40, 80, 150, 20
					// id 라벨 위치: 40, 120, 150, 20
				}
				c.add(QnALabel[i]);
			} else if(i<6) {
				// id 및 pw 라디오버튼
				selIdPwBtn[index] = new JRadioButton(nameStr[i]);
				selIdPwBtn[index].setFont(sanserifNormal);
				selIdPwBtn[index].setForeground(darkGray);
				selIdPwBtn[index].addActionListener(infoListener);
				// name 및 id 입력 텍스트필드  
				wantTxt[index] = new JTextField(10);
				wantTxt[index].setFont(sanserifsmall);
				wantTxt[index].setForeground(darkGray);
				// 위치 설정
				if(i==4) {
					selIdPwBtn[index].setBounds(fixBtnX, fixBtnY, fixBtnW, fixBtnH);
					selIdPwBtn[index].setSelected(true);
					// name 텍스트필드 ( ★★★★★ 추후 리스너로 위치 조정 필요 ★★★★★ )
					wantTxt[index].setBounds(fixTxtX, fixTxtY, fixTxtW, fixTxtH);  // 130, 83, 150, 20
					
				} else {
					selIdPwBtn[index].setBounds(fixBtnX += chX, fixBtnY, fixBtnW, fixBtnH);  
					// id 텍스트필드 ( ★★★★★ 추후 리스너로 visible(false) 등 필요 ★★★★★ )
					wantTxt[index].setBounds(fixTxtX, fixTxtY += chTxtY, fixTxtW, fixTxtH);	// 130, 161, 150, 20
				}
				radioBtnGroup.add(selIdPwBtn[index]);
				c.add(selIdPwBtn[index]);
				c.add(wantTxt[index]);
				index++;
				// 초기화
				if(index > 1) {
					index = 0;
					fixTxtY = 83;
				}
			} else {
				// search 및 close 버튼
				btn[index] = new JButton(nameStr[i]);
				btn[index].setFont(sanserifsmall);
				btn[index].setForeground(Color.white);
				btn[index].setBackground(darkGray);
				btn[index].addActionListener(listener);
				if(i==7) {
					// close btn
					btn[index].setBounds(180, laY += chY, fixBtnW, fixBtnH);
				} else {
					// search btn 
					btn[index].setBounds(fixBtnX, 88, 90, 45);
				}
				c.add(btn[index]);
			}
		}

		setLocation(725, 400);
		setSize(450, 300);
		setResizable(false);
	}
}

// 한 클래스 내에 ActionListener 두 개 사용 안되서 하나 만듦.. 
class SearchInfoListener implements ActionListener{
	
	private JLabel [] QnALabel;
	private JTextField [] wantTxt;
	
	public SearchInfoListener(JLabel [] QnALabel, JTextField [] wantTxt) {
		this.QnALabel = QnALabel;
		this.wantTxt = wantTxt;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JRadioButton radioBtn = (JRadioButton) e.getSource();
		if(radioBtn.isSelected() == true) {
			if((radioBtn.getText()).equals(" ID ")) {
				QnALabel[1].setBounds(40, 100, 150, 20);
				wantTxt[0].setBounds(130, 104, 150, 20);
				QnALabel[2].setVisible(false);
				wantTxt[1].setVisible(false);
				
			} else {
				QnALabel[1].setBounds(40, 80, 150, 20);
				wantTxt[0].setBounds(130, 83, 150, 20);
				QnALabel[2].setVisible(true);
				wantTxt[1].setVisible(true);		
			}
		} 
	}
	
}