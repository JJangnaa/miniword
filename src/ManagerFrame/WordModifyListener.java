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
		// 변수에 값 담기
		inputEng = inputTxt[0].getText();
		inputKor = inputTxt[1].getText();
		// [[ 빈 값이 아닐 경우 ]]
		if(!(inputTxt[0].getText().equals("")) && !(inputTxt[1].getText().equals(""))) {
			// 입력된 영단어 기등록 유무 판별 (null: 기등록 X, !null: 기등록 O)
			try {
				result = wordDB.wordSurf("eng", inputEng);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// [추가 버튼]
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
			// [수정 버튼] 
			} else if (e.getActionCommand().equals("수정")) {
				// 기등록 뜻 추출
				try {
					existKor = wordDB.existKorSurf("kor", inputEng);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 영단어 존재 O and 입력값과 기등록 뜻이 같지 않을 때
				if(result != null && !(existKor.equals(inputKor))) {
					int answer = JOptionPane.showConfirmDialog(null, "정말로 수정 하시겠습니까?", "", JOptionPane.ERROR_MESSAGE);
					if(answer == JOptionPane.YES_OPTION) {
						newKor = existKor + ", " + inputKor;
						wordDB.updateValue(newKor, inputEng);
						JOptionPane.showMessageDialog(null, "수정 되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "[하기 사항 확인 필요]\n1. 기등록되어 있는 것과 차이가 없는 경우\n2. 미등록 단어인 경우", "수정 불가", JOptionPane.WARNING_MESSAGE);
				}
				
			// [삭제 버튼_1] 테이블 클릭하여 텍스트 채운 후 버튼 눌렀을 때를 위함.
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
			// 텍스트필드 초기화 및 단어리스트 업데이트
			inputTxt[0].setText("");
			inputTxt[1].setText("");
			wordDB.selectAllword(adminPanelWordList.getWordList().getModel(), adminPanelWordList.getWordList().getTable());
			
		// [삭제 버튼_2] eng 필수 입력을 위해 조건문 추가.
		} else if(!(inputTxt[0].getText().equals("")) && e.getActionCommand().equals("삭제")){
			// 입력된 영단어 기등록 유무 판별 (null: 기등록 X, !null: 기등록 O)
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
			// 텍스트필드 초기화 및 단어리스트 업데이트
			inputTxt[0].setText("");
			inputTxt[1].setText("");
			wordDB.selectAllword(adminPanelWordList.getWordList().getModel(), adminPanelWordList.getWordList().getTable());
		
		// 빈 값일 경우 경고창
		} else {  
			if(inputTxt[0].getText().equals("") && e.getActionCommand().equals("삭제")) {
				JOptionPane.showMessageDialog(null, "삭제할 영단어를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "-추가 및 수정\n    : eng, kor 필수입력\n-삭제\n    : eng 필수입력", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			// 텍스트필드 초기화
			inputTxt[0].setText("");
			inputTxt[1].setText("");
			
		}
		
	}

}
