package ManagerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import LoginFrame.DB;

public class WordSearchListener extends KeyAdapter implements ActionListener{
	private JTextField [] inputTxt;
	private WordAdminPanel adminPanelWordList;
	private String alphabet;
	private DB wordDB;
	
	public WordSearchListener(JTextField [] inputTxt, WordAdminPanel adminPanelWordList) {
		this.inputTxt = inputTxt;
		this.adminPanelWordList = adminPanelWordList;
		wordDB = new DB();
		
	}
	// (나중에) 영어랑 한글만 입력받을 수 있게 제한하는 식 만들기.
	public void keyPressed(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		alphabet = txt.getText();
		try {
			// (문제있음) 입력키 하나 더 있어야 원하는 값 검색 가능
			wordDB.searchAlphabet(adminPanelWordList.getWordList().getModel(), alphabet, adminPanelWordList.getWordList().getTable());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

}
