package MemberFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JTextField;

import LoginFrame.DB;

public class SearchListener extends KeyAdapter implements ActionListener {
	
	private String alphabet;
	private DB wordDB = new DB();
	private MemberPanel memberPanel;
	private JTextField searchWordTxt;
	
	public SearchListener(JTextField searchWordTxt, MemberPanel memberPanel) {
		this.searchWordTxt = searchWordTxt;
		this.memberPanel = memberPanel;
	}
	
	public void keyPressed(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		alphabet = txt.getText();
		try {
			// (문제있음) 입력키 하나 더 있어야 원하는 값 검색 가능
			wordDB.searchAlphabet(memberPanel.getModel(), alphabet, memberPanel.getTable());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		alphabet = searchWordTxt.getText();
		try {
			wordDB.searchAlphabet(memberPanel.getModel(), alphabet, memberPanel.getTable());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
