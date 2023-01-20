package ManagerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import LoginFrame.DB;

public class WordListListener extends MouseAdapter implements ActionListener{
	
	private JTextField [] inputTxt;
	private JButton [] btn;
	private DBListPanel wordList;
	private String[] selEngKorStr = {"All", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
									"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
									"w", "x", "y", "z"};
	private String alphabet;
	private DBListPanel tempDBList;
//	private DB tmpDB = new DB();
	
	// 문자열 뽑아내기, 분리, 담기 위한 것들
	private int index = 0;
	private String [] selectEngKor = new String[2];
	private String [] setEngKor = new String[2];
	private String strEngKor;
	private StringTokenizer sepEngKor;
	// 추가 수정 삭제를 위함
	private DB btnDB = new DB();
	
	public WordListListener(JTextField [] inputTxt, JButton [] btn, DBListPanel wordList) {
		this.inputTxt = inputTxt;
		this.btn = btn;
		this.wordList = wordList;
	}
	
	public WordListListener(String alphabet) {
		// TODO Auto-generated constructor stub
		this.alphabet = alphabet;
	}
	public void mouseClicked(MouseEvent e) {
		// 클릭한 행의 정보 뽑아내기
		int row = wordList.getTable().getSelectedRow();
		for(int i=0; i<wordList.getTable().getColumnCount(); i++) {
			selectEngKor[index] = wordList.getTable().getModel().getValueAt(row, i) + "!";
			index++;
		}
		// 뽑아낸 단어 및 뜻을 문자열에 담기
		strEngKor = selectEngKor[0].concat(selectEngKor[1]);
		// 초기화
		index = 0;
		// 바로 전에 담은 문자열 분리하여 담기
		sepEngKor = new StringTokenizer(strEngKor, "!");
		while(sepEngKor.hasMoreElements()) {
			setEngKor[index] = sepEngKor.nextToken();
			index++;
		}
		// 초기화
		index = 0;
		// eng 및 kor에 분리한 문자열 set 해주기
		inputTxt[0].setText(setEngKor[0]);
		inputTxt[1].setText(setEngKor[1]);
	}
	
	// 버튼에 붙일 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("추가")) {
			
		} else if(e.getActionCommand().equals("수정")) {
			
		} else if(e.getActionCommand().equals("삭제")) {
			
		} else if(e.getActionCommand().equals("검색")){
			
		} else {
			JComboBox cb = (JComboBox) e.getSource();
			int cbIndex = cb.getSelectedIndex();
			this.alphabet = selEngKorStr[cbIndex];
		}
	}
	public String getAlphabet() {
		return alphabet;
	}

}
