package LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ButtonClickListener extends KeyAdapter implements ActionListener{
	
	private DB repDB = new DB();
	private String res;
	private int yesNo;
	private JTextField [] repTxt;
	private JButton [] repBtn;
	
	public ButtonClickListener(JTextField [] repTxt, JButton[] repBtn){
		this.repBtn = repBtn;
		this.repTxt = repTxt;
	}
	
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
							yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 name 입니다.\n입력하신 name을 사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
							if(yesNo == JOptionPane.YES_OPTION) {
								repDB.insertValue("name", repTxt[0].getText());
								repTxt[0].setEnabled(false);
								repBtn[1].setEnabled(false);
							} else {
								repTxt[0].setText("");
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
							yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 id 입니다.\n입력하신 id를 사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
							if(yesNo == JOptionPane.YES_OPTION) {
								repTxt[1].setEnabled(false);
								repBtn[2].setEnabled(false);
								repDB.updateValue("id", this.repTxt[1].getText(), "name", this.repTxt[0].getText());
							} else {
								repTxt[1].setText("");
							}
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			
		}
	}

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
						yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 name 입니다.\n입력하신 name을 사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
						if(yesNo == JOptionPane.YES_OPTION) {
							repTxt[0].setEnabled(false);
							btn.setEnabled(false);
							repDB.insertValue("name", this.repTxt[0].getText());
						} else {
							this.repTxt[0].setText("");
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
						yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 id 입니다.\n입력하신 id를 사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
						if(yesNo == JOptionPane.YES_OPTION) {
							repTxt[1].setEnabled(false);
							btn.setEnabled(false);
							repDB.updateValue("id", this.repTxt[1].getText(), "name", this.repTxt[0].getText());
						} else {
							this.repTxt[1].setText("");
						}
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
