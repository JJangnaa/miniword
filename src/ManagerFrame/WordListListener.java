package ManagerFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class WordListListener extends MouseAdapter{
	
	private JTextField [] inputTxt;
	private JButton [] btn;
	private DBListPanel wordList;
	// 문자열 뽑아내기, 분리, 담기 위한 것들
	private int index = 0;
	private String [] selectEngKor = new String[2];
	private String [] setEngKor = new String[2];
	private String strEngKor;
	private StringTokenizer sepEngKor;
	
	public WordListListener(JTextField [] inputTxt, JButton [] btn, DBListPanel wordList) {
		this.inputTxt = inputTxt;
		this.btn = btn;
		this.wordList = wordList;
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

}
