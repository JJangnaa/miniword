package LoginFrame;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ButtonClickListener extends KeyAdapter implements ActionListener{
	
	private DB repDB = new DB();
	private Dialog JoinDialog;
	private String res;
	private int yesNo;
	private JLabel[] noticeLabel;
	private JTextField [] repTxt;
	private JPasswordField [] repPw;
	private JButton [] repBtn;
	
	public ButtonClickListener(JTextField [] repTxt, JButton[] repBtn, JPasswordField [] repPw, JLabel[] noticeLabel, Dialog JoinDialog){
		this.repBtn = repBtn;
		this.repTxt = repTxt;
		this.repPw = repPw;
		this.noticeLabel = noticeLabel;
		this.JoinDialog = JoinDialog;
	}
	
	// key 리스너(적용항목: id 및 name 라벨)
	public void keyPressed(KeyEvent e) {
		Object obj = e.getSource();
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(obj == repTxt[0]) {
				try {
					if(repTxt[0].getText().equals("")) {
						JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\n원하시는 name을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						repTxt[0].setText("");
						
					} else {
						res = repDB.memberSurf("name", repTxt[0].getText());
						if(res != null) {
							JOptionPane.showMessageDialog(null, "이미 있는 name 입니다.\n 다른 name을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
							repTxt[0].setText("");
						} else if (res == null){
							yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 name 입니다.\n사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
							if(yesNo == JOptionPane.NO_OPTION) {
								repTxt[0].setText("");
							} else {
								noticeLabel[0].setText("중복확인 완료");
								repTxt[0].setEditable(false);
							}
						}
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			} else {
				try {
					if(repTxt[1].getText().equals("")) {
						JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\n원하시는 id를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						repTxt[1].setText("");
						
					} else {
						res = repDB.memberSurf("id", repTxt[1].getText());
						if(res != null) {
							JOptionPane.showMessageDialog(null, "이미 있는 id 입니다.\n 다른 id를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
							repTxt[1].setText("");
						} else if (res == null){
							yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 id 입니다\n사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
							if(yesNo == JOptionPane.NO_OPTION) {
								repTxt[1].setText("");
							} else {
								noticeLabel[1].setText("ID 중복확인 완료");
								repTxt[1].setEditable(false);
							}
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
				if(this.repTxt[0].getText().equals("")) {
					JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\n원하시는 name을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					this.repTxt[0].setText("");
					
				} else {
					res = repDB.memberSurf("name", this.repTxt[0].getText());
					if(res != null) {
						JOptionPane.showMessageDialog(null, "이미 있는 name 입니다.\n 다른 name을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						this.repTxt[0].setText("");
					} else if (res == null){
						yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 name 입니다.\n사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
						if(yesNo == JOptionPane.NO_OPTION) {
							this.repTxt[0].setText("");
						} else {
							noticeLabel[0].setText("중복확인 완료");
							repTxt[0].setEditable(false);
						}
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getActionCommand()=="ID 중복확인") {	// id
			try {
				if(this.repTxt[1].getText().equals("")) {
					JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\n원하시는 id를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					this.repTxt[1].setText("");
					
				} else {
					res = repDB.memberSurf("name", this.repTxt[1].getText());
					if(res != null) {
						JOptionPane.showMessageDialog(null, "이미 있는 id 입니다.\n 다른 id를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						this.repTxt[1].setText("");
					} else if (res == null){
						yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 id 입니다.\n사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
						if(yesNo == JOptionPane.NO_OPTION) {
							this.repTxt[1].setText("");
						} else {
							noticeLabel[1].setText("ID 중복확인 완료");
							repTxt[1].setEditable(false);
						}
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(e.getActionCommand() == "JOIN") {
			if(essentialValue()==true) {
				yesNo = JOptionPane.showConfirmDialog(null, "입력한 정보로 회원가입 하시겠습니까?", "", JOptionPane.YES_NO_OPTION);
				if(yesNo == JOptionPane.YES_OPTION) {
					repDB.firstValue(repTxt, repPw);
					JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다.", "Congratulation!!!", JOptionPane.PLAIN_MESSAGE);
					reset();
				} 
			}
		} else if(e.getActionCommand() == "reset") {
			reset();
		}
	}
	public void reset() {
		repTxt[0].setEditable(true);
		repTxt[0].setText("");
		repTxt[1].setEditable(true);
		repTxt[1].setText("");
		noticeLabel[0].setText("");
		noticeLabel[1].setText("");
		repPw[0].setText("");
		repPw[1].setText("");
		noticeLabel[3].setText(">> 비밀번호 확인");
		noticeLabel[3].setForeground(new Color(127, 127, 127));
		noticeLabel[3].setForeground(new Color(127, 127, 127));
		noticeLabel[3].setFont(new Font("SanSerif", Font.ITALIC, 12));
		repTxt[3].setText("");
		repTxt[4].setText("");
		
	}
	// 올바른 JOIN을 위한 필터
	public boolean essentialValue() {
		boolean judge = true;
		
		if(!(noticeLabel[0].getText()).equals("") && !(noticeLabel[1].getText()).equals("")) {
			if((noticeLabel[3].getText()).equals("비밀번호 일치")) {
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호 재확인이 되지 않았습니다.\n다시 시도해주세요.", "비밀번호 재확인 필요", JOptionPane.WARNING_MESSAGE);
				judge = false;
			} 
		} else {
			JOptionPane.showMessageDialog(null, "중복확인이 되지 않았습니다.\n다시 시도해주세요.", "중복확인 필요", JOptionPane.WARNING_MESSAGE);
			judge = false;
		}
		return judge;
	}

}
