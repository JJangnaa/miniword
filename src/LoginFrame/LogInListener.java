package LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ManagerFrame.ManagerFrame;
import MemberFrame.MemberFrame;

public class LogInListener extends KeyAdapter implements ActionListener {
	
	private DB repDB = new DB();
	private ManagerFrame managerFrame;
	private MemberFrame memberFrame;
	private LogInFrame logInFrame;
	private String resId;
	private String resPw;
	private JTextField idTxt;
	private JPasswordField pwTxt;
	
	private String name;
	private JLabel tmpLabel;
	
	public LogInListener(JTextField idTxt, JPasswordField pwTxt, LogInFrame logInFrame) {
		this.idTxt = idTxt;
		this.pwTxt = pwTxt;
		this.logInFrame = logInFrame;
		this.managerFrame = new ManagerFrame();
		this.memberFrame = new MemberFrame();
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
						// 로그인 후 화면 연결
						if(idTxt.getText().equals("ManagerJang")) {
							JOptionPane.showMessageDialog(null, "Hello, Manager !", "Manager Mode", JOptionPane.PLAIN_MESSAGE);
							logInFrame.setVisible(false);
							managerFrame.setVisible(true);
						} else {
							name = repDB.infoSurfID("name", "id", idTxt.getText());
							JOptionPane.showMessageDialog(null, name + "님 환영합니다 :)", "LogIn Success !!!", JOptionPane.PLAIN_MESSAGE);
							tmpLabel = memberFrame.getNameLabel();
							tmpLabel.setText(name);
							logInFrame.setVisible(false);
							memberFrame.setVisible(true);
						}
						idTxt.setText("");
						pwTxt.setText("");
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
					// 로그인 후 화면 연결
					// + 로그인 정보에 따라 띄우는 창이 달라야 함. (관리자모드, 회원모드)
					// ++ 회원모드로 로그인할 경우, 로그인 정보가 회원모드 내의 회원정보와 동일 해야함.
					logInFrame.setVisible(false);
					managerFrame.setVisible(true);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	

}
