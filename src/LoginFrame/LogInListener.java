package LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInListener extends KeyAdapter implements ActionListener {
	
	private DB repDB = new DB();
	private String resId;
	private String resPw;
	private JTextField idTxt;
	private JPasswordField pwTxt;
	
	public LogInListener(JTextField idTxt, JPasswordField pwTxt) {
		this.idTxt = idTxt;
		this.pwTxt = pwTxt;
	}
	public void keyPressed(KeyEvent e) {
		Object obj = e.getSource();
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				resId = repDB.memberSurf("ID", idTxt.getText());
				resPw = repDB.memberSurf("PW", pwTxt.getText());
				
				if((idTxt.getText()).equals("")|| (pwTxt.getText()).equals("")) {
					JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\nID 및 PW를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
				} 
				else {
					if(resId == null || resPw == null) {
						JOptionPane.showMessageDialog(null, "일치하는 회원정보가 없습니다.", "LogIn Fail ;(", JOptionPane.WARNING_MESSAGE);
						idTxt.setText("");
						pwTxt.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "환영합니다 :)", "LogIn Success !!!", JOptionPane.PLAIN_MESSAGE);
						idTxt.setText("");
						pwTxt.setText("");
						// ++ 로그인 후 화면 연결해야함!
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			resId = repDB.memberSurf("ID", idTxt.getText());
			resPw = repDB.memberSurf("PW", pwTxt.getText());
			
			if((idTxt.getText()).equals("")|| (pwTxt.getText()).equals("")) {
				JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\nID 및 PW를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
			} 
			else {
				if(resId == null || resPw == null) {
					JOptionPane.showMessageDialog(null, "일치하는 회원정보가 없습니다.\n재시도 또는 회원가입이 필요합니다.", "Warning", JOptionPane.WARNING_MESSAGE);
					idTxt.setText("");
					pwTxt.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "환영합니다 :)", "LogIn Success !!!", JOptionPane.PLAIN_MESSAGE);
					idTxt.setText("");
					pwTxt.setText("");
					// ++ 로그인 후 화면 연결해야함!
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
