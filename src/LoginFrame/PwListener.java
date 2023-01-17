package LoginFrame;

import java.awt.Color;
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
	private JLabel warnLabel;
	private int yesNo;
	private String repPwStr;
	private String repCheStr;
	
	
	public PwListener(JPasswordField [] repPw, JTextField [] repTxt, JLabel warnLabel){
		this.repPw = repPw;
		this.repTxt = repTxt;
		this.warnLabel = warnLabel;
	}
	public void keyPressed(KeyEvent e) {
		Object obj = e.getSource();
		repPwStr = new String(repPw[0].getPassword());
		repCheStr = new String(repPw[1].getPassword());
		Pattern pwPat = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
		Matcher pwMat = pwPat.matcher(repPwStr);
		
		if(obj == repPw[1]) {
			if(repPwStr.equals(repCheStr)) {
				warnLabel.setText("비밀번호 일치");
				warnLabel.setForeground(Color.GREEN);
			} else {
				warnLabel.setText("비밀번호 불일치");
				warnLabel.setForeground(Color.RED);
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(obj == repPw[0] && !pwMat.find()) {
				JOptionPane.showMessageDialog(null, "PW는 영문+특수문자+숫자 8~20자로 구성되어야 합니다.\n다시 입력해주세요.", "WARN", JOptionPane.WARNING_MESSAGE);
				repPw[0].setText("");
			} else if(obj == repPw[1] && repPwStr.equals(repCheStr)) {
				yesNo = JOptionPane.showConfirmDialog(null, "입력하신 PW를 사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
				if(yesNo == JOptionPane.NO_OPTION) {
					repPw[0].setText("");
					repPw[1].setText("");
				}
//				if(yesNo == JOptionPane.YES_OPTION) {
//					repPw[0].setEditable(false);
//					repPw[1].setEditable(false);
//					repDB.updateValue("pw", repPwStr, "name", this.repTxt[0].getText());
//				} else {
//					repPw[0].setText("");
//					repPw[1].setText("");
//				}
			}
			
		}
		
	}

}
