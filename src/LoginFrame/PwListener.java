package LoginFrame;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PwListener extends KeyAdapter {
	
	private DB repDB = new DB();
	private JPasswordField [] repPw;
	private JTextField [] repTxt;
	private JLabel[] noticeLabel;
	private int yesNo;
	private String repPwStr;
	private String repCheStr;
	private Color darkGray = new Color(127, 127, 127);
	
	public PwListener(JPasswordField [] repPw, JTextField [] repTxt, JLabel[] noticeLabel){
		this.repPw = repPw;
		this.repTxt = repTxt;
		this.noticeLabel = noticeLabel;
	}
	public void keyReleased(KeyEvent e) {
		repPwStr = new String(repPw[0].getPassword());
		repCheStr = new String(repPw[1].getPassword());
		if(repPwStr.equals("") && repCheStr.equals("")) {
			noticeLabel[3].setText(">> 비밀번호 확인");
			noticeLabel[3].setForeground(darkGray);
		} else {
			if(repPwStr.equals(repCheStr)) {
				noticeLabel[3].setText("비밀번호 일치");
				noticeLabel[3].setForeground(Color.GREEN);
			} else {
				noticeLabel[3].setText("비밀번호 불일치");
				noticeLabel[3].setForeground(Color.RED);
			}
		}
		
		
	}
	

}
