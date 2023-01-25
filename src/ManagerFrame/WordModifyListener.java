package ManagerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import LoginFrame.DB;

public class WordModifyListener implements ActionListener{
	
	private JTextField [] inputTxt;
	private String inputEng;
	private String inputKor;
	private String result;
	private String existKor;
	private String newKor;
	private DB wordDB;
	private WordAdminPanel adminPanelWordList;
	public WordModifyListener(JTextField [] inputTxt, WordAdminPanel adminPanelWordList) {
		this.inputTxt = inputTxt;
		this.adminPanelWordList = adminPanelWordList;
		wordDB = new DB();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		inputEng = inputTxt[0].getText();
		inputKor = inputTxt[1].getText();
		
		if(!(inputTxt[0].getText().equals("")) && !(inputTxt[1].getText().equals(""))) {
			try {
				result = wordDB.wordSurf("eng", inputEng);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if(e.getActionCommand().equals("추가")) {
				if(result != null) {
					JOptionPane.showMessageDialog(null, "이미 있는 단어 입니다.", "", JOptionPane.ERROR_MESSAGE);
				} else {
					int answer = JOptionPane.showConfirmDialog(null, "추가 하시겠습니까?", "", JOptionPane.YES_NO_OPTION);
					if(answer == JOptionPane.YES_OPTION) {
						wordDB.insertValue(inputEng, inputKor);
						JOptionPane.showMessageDialog(null, "추가 되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
					} 
				}
				
			} else if (e.getActionCommand().equals("수정")) {
				if(result != null) {
					int answer = JOptionPane.showConfirmDialog(null, "정말로 수정 하시겠습니까?", "", JOptionPane.ERROR_MESSAGE);
					if(answer == JOptionPane.YES_OPTION) {
						
						try {
							existKor = wordDB.existKorSurf("kor", inputEng);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						newKor = existKor + ", " + inputKor;
						wordDB.updateValue(newKor, inputEng);
						JOptionPane.showMessageDialog(null, "수정 되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "없는 단어 입니다.\n(뜻 만 수정 가능)", "수정 불가", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				if(result != null) {
					int answer = JOptionPane.showConfirmDialog(null, "정말로 삭제 하시겠습니까?", "", JOptionPane.ERROR_MESSAGE);
					if(answer == JOptionPane.YES_OPTION) {
						wordDB.deleteValue(inputEng);
						JOptionPane.showMessageDialog(null, "삭제 되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "없는 단어 입니다.", "삭제 불가", JOptionPane.WARNING_MESSAGE);
				}
			}
			inputTxt[0].setText("");
			inputTxt[1].setText("");
			wordDB.selectAllword(adminPanelWordList.getWordList().getModel(), adminPanelWordList.getWordList().getTable());
		} else if(!(inputTxt[0].getText().equals("")) && e.getActionCommand().equals("삭제")){
			try {
				result = wordDB.wordSurf("eng", inputEng);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if(result != null) {
				int answer = JOptionPane.showConfirmDialog(null, "정말로 삭제 하시겠습니까?", "", JOptionPane.ERROR_MESSAGE);
				if(answer == JOptionPane.YES_OPTION) {
					wordDB.deleteValue(inputEng);
					JOptionPane.showMessageDialog(null, "삭제 되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "없는 단어 입니다.", "삭제 불가", JOptionPane.WARNING_MESSAGE);
			}
			inputTxt[0].setText("");
			inputTxt[1].setText("");
			wordDB.selectAllword(adminPanelWordList.getWordList().getModel(), adminPanelWordList.getWordList().getTable());
		} else {
			if(inputTxt[0].getText().equals("") && e.getActionCommand().equals("삭제")) {
				JOptionPane.showMessageDialog(null, "삭제할 영단어를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "-추가 및 수정\n    : eng, kor 필수입력\n-삭제\n    : eng 필수입력", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			inputTxt[0].setText("");
			inputTxt[1].setText("");
			
		}
		
	}

}
