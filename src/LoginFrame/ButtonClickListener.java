package LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ButtonClickListener extends KeyAdapter implements ActionListener{
	
	private DB repDB = new DB();
	private String res;
	private int yesNo;
	
	public void keyPressed(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				if(txt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "입력된 것이 없습니다.\n원하시는 name을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					txt.setText("");
					
				} else {
					res = repDB.memberSurf("name", txt.getText());
					txt.setText("");
					System.out.println(res);
					System.out.println(txt.getText());
					if(res != null && txt.getText()!=null) {
						JOptionPane.showMessageDialog(null, "이미 있는 name 입니다.\n 다른 name을 입력해수제요.", "Warning", JOptionPane.WARNING_MESSAGE);
						txt.setText("");
					} else if (res == null && txt.getText()!=null){
						yesNo = JOptionPane.showConfirmDialog(null, "사용 가능한 name 입니다.\n입력하신 name을 사용하시겠습니까?", "Correct", JOptionPane.YES_NO_OPTION);
						if(yesNo == JOptionPane.YES_OPTION) {
							txt.setEnabled(false);
							repDB.insertValue("name", txt.getText());
						} else {
							txt.setText("");
						}
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
		
	}

}
