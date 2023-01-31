package LoginFrame;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ButtonClickListener extends KeyAdapter implements ActionListener{
	
	private DB repDB = new DB();
	private Dialog JoinDialog;
	private JoinDialog joinDialog;
	private String res;
	private int yesNo;
	private JLabel[] noticeLabel;
	private JTextField [] joinTxt;
	private JPasswordField [] pwJoinTxt;
	private JButton dupBtn;
	private JButton idDupBtn;
	
	private boolean flag;
	
	public ButtonClickListener(JoinDialog joinDialog){
		this.joinDialog = joinDialog;
		this.joinTxt = joinDialog.getJoinTxt();
		this.pwJoinTxt = joinDialog.getPwJoinTxt();
		this.noticeLabel = joinDialog.getNoticeLabel();
		this.dupBtn = joinDialog.getDupBtn();
		this.idDupBtn = joinDialog.getIdDupBtn();
	}
	
	// key 리스너(적용항목: id 및 name 라벨)
	public void keyPressed(KeyEvent e) {
		Object obj = e.getSource();
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(obj == joinTxt[0]) {
				try {
					if(joinTxt[0].getText().equals("")) {
						JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\n원하시는 name을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						joinTxt[0].setText("");
						
					} else {
						if(notSpace(joinTxt[0].getText())) {
							res = repDB.memberSurf("name", joinTxt[0].getText());
							if(res != null) {
								JOptionPane.showMessageDialog(null, "이미 있는 name 입니다.\n 다른 name을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
								joinTxt[0].setText("");
							} else if (res == null){
								yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 name 입니다.\n사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
								if(yesNo == JOptionPane.NO_OPTION) {
									joinTxt[0].setText("");
								} else {
									noticeLabel[0].setText("중복확인 완료");
									joinTxt[0].setEditable(false);
									dupBtn.setEnabled(false);
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "공백은 사용불가 입니다.", "Warning", JOptionPane.WARNING_MESSAGE);
							joinTxt[0].setText("");
						}
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			} else {
				try {
					if(joinTxt[1].getText().equals("")) {
						JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\n원하시는 id를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						joinTxt[1].setText("");
					} else {
						if(onlyEngNum(joinTxt[1].getText())) {
							res = repDB.memberSurf("id", joinTxt[1].getText());
							if(res != null) {
								JOptionPane.showMessageDialog(null, "이미 있는 id 입니다.\n 다른 id를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
								joinTxt[1].setText("");
							} else if (res == null){
								yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 id 입니다\n사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
								if(yesNo == JOptionPane.NO_OPTION) {
									joinTxt[1].setText("");
								} else {
									noticeLabel[1].setText("ID 중복확인 완료");
									joinTxt[1].setEditable(false);
									idDupBtn.setEnabled(false);
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "영문과 숫자만 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
							joinTxt[1].setText("");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
			
		}
	}
	// action 리스너(적용항목: id 및 name 중복확인 버튼, JOIN 버튼)
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		if(e.getActionCommand()=="중복확인") {	  // name
			try {
				if(this.joinTxt[0].getText().equals("")) {
					JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\n원하시는 name을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					this.joinTxt[0].setText("");
					
				} else {
					if(notSpace(joinTxt[0].getText())) {
						res = repDB.memberSurf("name", this.joinTxt[0].getText());
						if(res != null) {
							JOptionPane.showMessageDialog(null, "이미 있는 name 입니다.\n 다른 name을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
							this.joinTxt[0].setText("");
						} else if (res == null){
							yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 name 입니다.\n사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
							if(yesNo == JOptionPane.NO_OPTION) {
								this.joinTxt[0].setText("");
							} else {
								noticeLabel[0].setText("중복확인 완료");
								joinTxt[0].setEditable(false);
								dupBtn.setEnabled(false);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "공백은 사용불가 입니다.", "Warning", JOptionPane.WARNING_MESSAGE);
						joinTxt[0].setText("");
					}
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getActionCommand()=="ID 중복확인") {	// id
			try {
				if(this.joinTxt[1].getText().equals("")) {
					JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\n원하시는 id를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					this.joinTxt[1].setText("");
					
				} else {
					if(onlyEngNum(joinTxt[1].getText())) {
						res = repDB.memberSurf("name", this.joinTxt[1].getText());
						if(res != null) {
							JOptionPane.showMessageDialog(null, "이미 있는 id 입니다.\n 다른 id를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
							this.joinTxt[1].setText("");
						} else if (res == null){
							yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 id 입니다.\n사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
							if(yesNo == JOptionPane.NO_OPTION) {
								this.joinTxt[1].setText("");
							} else {
								noticeLabel[1].setText("ID 중복확인 완료");
								joinTxt[1].setEditable(false);
								idDupBtn.setEnabled(false);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "영문과 숫자만 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						joinTxt[1].setText("");
					}
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getActionCommand() == "JOIN") {
			if(essentialValue()==true) {
				yesNo = JOptionPane.showConfirmDialog(null, "입력한 정보로 회원가입 하시겠습니까?", "", JOptionPane.YES_NO_OPTION);
				if(yesNo == JOptionPane.YES_OPTION) {
					repDB.firstValue(joinTxt, pwJoinTxt);
					JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다.", "Congratulation!!!", JOptionPane.PLAIN_MESSAGE);
					joinDialog.setVisible(false);
					reset();
				} 
			}
		} else if(e.getActionCommand() == "reset") {
			reset();
		}
	}
	public void reset() {
		joinTxt[0].setEditable(true);
		joinTxt[0].setText("");
		joinTxt[1].setEditable(true);
		joinTxt[1].setText("");
		noticeLabel[0].setText("");
		noticeLabel[1].setText("");
		pwJoinTxt[0].setText("");
		pwJoinTxt[1].setText("");
		noticeLabel[3].setText(">> 비밀번호 확인");
		noticeLabel[3].setForeground(new Color(127, 127, 127));
		noticeLabel[3].setForeground(new Color(127, 127, 127));
		noticeLabel[3].setFont(new Font("SanSerif", Font.ITALIC, 12));
		joinTxt[3].setText("");
		joinTxt[4].setText("");
		dupBtn.setEnabled(true);
		idDupBtn.setEnabled(true);
		
	}
	// 올바른 JOIN을 위한 필터
	public boolean essentialValue() {
		boolean judge = true;
		String pw = new String(pwJoinTxt[0].getPassword());
		String pwCh = new String(pwJoinTxt[1].getPassword());
		Pattern pwPat = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
		Matcher pwMat = pwPat.matcher(pw);
		Matcher pwCheckMat = pwPat.matcher(pwCh);
		
		if(!(noticeLabel[0].getText()).equals("") && !(noticeLabel[1].getText()).equals("")) {
			if(!(noticeLabel[3].getText()).equals("비밀번호 일치")) {
				JOptionPane.showMessageDialog(null, "비밀번호 재확인이 되지 않았습니다.\n다시 시도해주세요.", "비밀번호 재확인 필요", JOptionPane.WARNING_MESSAGE);
				judge = false;
			} else if(!pwMat.find() || !pwCheckMat.find()){
				JOptionPane.showMessageDialog(null, "PW는 영문+특수문자+숫자 8~20자로 구성되어야 합니다.\n다시 입력해주세요.", "WARN", JOptionPane.WARNING_MESSAGE);
				pwJoinTxt[0].setText("");
				pwJoinTxt[1].setText("");
				judge = false;
			} 
		} else {
			JOptionPane.showMessageDialog(null, "중복확인이 되지 않았습니다.\n다시 시도해주세요.", "중복확인 필요", JOptionPane.WARNING_MESSAGE);
			judge = false;
		}
		return judge;
	}
	// 영어랑 숫자만 받기
	public boolean onlyEngNum(String id) {
		char rep;
		for(int i=0; i<id.length(); i++) {
			rep = id.charAt(i);
			if(rep >= 0x61 && rep <=0x7A) { // 영어 소문자
				flag = true;
			} else if (rep >=0x41 && rep <=0x5A) { // 영어 소문자
				flag = true;
			} else if (rep >= 0x30 && rep <= 0x39) { // 숫자
				flag = true;
			} else { // 그 외
				flag = false;
			}
		}
		return flag;
	}
	// 공백 제외한 값만 받기
	public boolean notSpace(String name) {
		char rep;
		for(int i=0; i<name.length(); i++) {
			rep = name.charAt(i);
			if(rep == 0x20) {
				flag = false;
			} else {
				flag = true;
			}
		}
		return flag;
	}
}
