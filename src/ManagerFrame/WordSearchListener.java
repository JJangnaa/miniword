package ManagerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import LoginFrame.DB;

public class WordSearchListener extends KeyAdapter implements ActionListener{
	private JTextField [] inputTxt;
	private WordAdminPanel adminPanelWordList;
	private String alphabet;
	private DB wordDB;
	
	private boolean flag;
	
	public WordSearchListener(JTextField [] inputTxt, WordAdminPanel adminPanelWordList) {
		this.inputTxt = inputTxt;
		this.adminPanelWordList = adminPanelWordList;
		wordDB = new DB();
		
	}
	public void keyReleased(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		alphabet = txt.getText();
		// 입력값 있을 때만
		if(!(txt.getText().equals(""))) {
			if(onlyEng(alphabet)) {
				try {
					wordDB.searchAlphabet(adminPanelWordList.getWordList().getModel(), alphabet, adminPanelWordList.getWordList().getTable());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "영어만 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
				txt.setText("");
			}
		// 입력값 없으면 전체 단어리스트 호출
		} else {
			wordDB.selectAllword(adminPanelWordList.getWordList().getModel(), adminPanelWordList.getWordList().getTable());
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		alphabet = inputTxt[2].getText();
		try {
			wordDB.searchAlphabet(adminPanelWordList.getWordList().getModel(), alphabet, adminPanelWordList.getWordList().getTable());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// 영어 및 검색할때 자주사용하는 키코드 만 사용가능하게끔
	public boolean onlyEng(String eng) {
		char rep;
		for(int i=0; i<eng.length(); i++) {
			rep = eng.charAt(i);
			if(rep >= 0x61 && rep <=0x7A) { // 영어 소문자
				flag = true;
			} else if (rep >=0x41 && rep <=0x5A) { // 영어 소문자
				flag = true;
			} else if (rep == '\t' || rep == '\b' || rep == '\n' || rep ==0x10 || rep == 0x11 || rep == 0x20 || (rep >= 0x23 && rep <= 0x28) || rep == 0x7F) {
				flag = true;
			} else {
				flag = false;
			}
		}
		
		return flag;
	}
	
}
