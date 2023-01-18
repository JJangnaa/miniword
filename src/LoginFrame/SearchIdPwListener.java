package LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SearchIdPwListener extends KeyAdapter implements ActionListener {
	
	private DB repDB = new DB();
	private JRadioButton [] selIdPwBtn;
	private JLabel [] QnALabel;
	private JTextField [] wantTxt;
	private JButton [] btn;
	private SearchIdPw searchIdPw;
	private String resName;
	private String resId;
	
	public SearchIdPwListener(JRadioButton [] selIdPwBtn, JLabel [] QnALabel, JTextField [] wantTxt, JButton [] btn, SearchIdPw searchIdPw) {
		this.selIdPwBtn = selIdPwBtn;
		this.QnALabel = QnALabel;
		this.wantTxt = wantTxt;
		this.btn = btn;
		this.searchIdPw = searchIdPw;
	}
	
	// 버튼 클릭시 작동
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton)e.getSource();
		if((btn.getText()).equals("search")) {
			if(selectedRaBtn(selIdPwBtn) == true) {	// 라디오버튼이 ID 에 선택되어 있음
				if(!(wantTxt[0].getText()).equals("")){
					try {
						resName = repDB.memberSurf("name", wantTxt[0].getText());
						if(resName != null) {
							String ID = repDB.infoSurfID("id", "name", wantTxt[0].getText());
							QnALabel[3].setText(ID);
						} else {
							JOptionPane.showMessageDialog(null, "일치하는 회원정보가 없습니다.\n다시 시도해주세요.", "Search failed", JOptionPane.WARNING_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "입력된 정보가 없습니다.", "Please input information", JOptionPane.WARNING_MESSAGE);
				}
				
			} else {	// 라디오버튼이 PW 에 선택되어 있음
				if(!(wantTxt[0].getText()).equals("") && !(wantTxt[1].getText()).equals("")) {
					try {
						resName = repDB.memberSurf("name", wantTxt[0].getText());
						resId = repDB.memberSurf("id", wantTxt[1].getText());
						if(resName != null && resId != null) {
							String ID = repDB.infoSurfPW("pw", "name", "id", wantTxt[0].getText(), wantTxt[1].getText());
							QnALabel[3].setText(ID);
						} else {
							JOptionPane.showMessageDialog(null, "일치하는 회원정보가 없습니다.\n다시 시도해주세요.", "Search failed", JOptionPane.WARNING_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "입력된 정보가 없습니다.", "Please input information", JOptionPane.WARNING_MESSAGE);
				}
			}
		} else {
			searchIdPw.setVisible(false);
			reset();
		}
		
	}
	
	// 텍스트 필드 창 엔터
	public void keyPressed(KeyEvent e) {
		// 나중에..^^..........
	}
	
	// return(true): pw X & id O
		// return(false): pw O & id X
		public boolean selectedRaBtn(JRadioButton [] selIdPwBtn) {
			boolean judge = true;
			if(selIdPwBtn[0].isSelected() == true) {
				// PW 라디오버튼이 선택되어 있으면(= ID 라디오버튼은 선택X) false 리턴.
				judge = false;
			} // else { // PW 라디오버튼이 선택되어 있지 않으면(= ID 라디오버튼은 선택O) true 리턴.}
			
			return judge;
		}
		
		public void reset() {
			// 화면 초기화
			wantTxt[0].setText("");
			wantTxt[1].setText("");
			selIdPwBtn[0].setSelected(true);
			QnALabel[1].setBounds(40, 80, 150, 20);
			wantTxt[0].setBounds(130, 83, 150, 20);
			QnALabel[2].setVisible(true);
			wantTxt[1].setVisible(true);
			QnALabel[3].setText("-");
		}
		
}
