package LoginFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PhoneListener extends KeyAdapter {
	
	private DB repDB = new DB();
	private JTextField [] repTxt;
	private int yesNo;
	
	public PhoneListener(JTextField [] repTxt) {
		this.repTxt = repTxt;
	}
	
	public boolean checkNum(String txt) {
		char chRepTxt;
		boolean judge = true;
		for (int i=0; i<txt.length(); i++) {
			chRepTxt = txt.charAt(i);
			if(!(chRepTxt >= 0x30 && chRepTxt <= 0x39)) {
				judge = false;
				break;
			} else {
				judge = true;
			}
		}
		
		return judge;
	}
	
	public boolean checkNumLength(String txt) {
		boolean judge = true;
		if (txt.length() != 4 || txt.equals("")) {
			judge = false;
		}
		return judge;
	}
	
	public void keyPressed(KeyEvent e) {
		Object obj = e.getSource();
		
		if(e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
			String phoneTxt1 = repTxt[3].getText();
			String phoneTxt2 = repTxt[4].getText();
			
			if(obj == repTxt[3] && (checkNum(phoneTxt1) == false || checkNumLength(phoneTxt1) == false)) {
				JOptionPane.showMessageDialog(null, "숫자 4개를 입력해주세요.", "WARN", JOptionPane.WARNING_MESSAGE);
				repTxt[3].setText("");
			}
			
			if(obj == repTxt[4]) {
				if(checkNum(phoneTxt2) == false || checkNumLength(phoneTxt2) == false) {
					JOptionPane.showMessageDialog(null, "숫자 4개를 입력해주세요.", "WARN", JOptionPane.WARNING_MESSAGE);
					repTxt[4].setText("");
				} else{
					yesNo = JOptionPane.showConfirmDialog(null, "입력하신 번호를 사용하시겠습니까?", "OK", JOptionPane.YES_NO_OPTION);
					if(yesNo == JOptionPane.NO_OPTION) {
						repTxt[3].setText("");
						repTxt[4].setText("");
					}
//					if(yesNo == JOptionPane.YES_OPTION) {
//						repTxt[3].setEditable(false);
//						repTxt[4].setEditable(false);
//						repDB.updateValue("phonenumber", "010"+phoneTxt1+""+phoneTxt2, "name", this.repTxt[0].getText());
//					} else {
//						repTxt[3].setText("");
//						repTxt[4].setText("");
//					}
				}
				
			} 
		}
		

		 
		
	}
}
