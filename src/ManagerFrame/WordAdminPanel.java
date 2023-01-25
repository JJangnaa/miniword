package ManagerFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import LoginFrame.DB;

// 단어관리 패널
public class WordAdminPanel extends JPanel {
	// 단어목록 불러오기 위함.
	private DBListPanel wordList;
	// 단어목록 붙일 리스너
	private WordTableClickListener listener;
	private WordSearchListener searchWordListener;
	private WordModifyListener modifyListener;
	// 지정 폰트 및 컬러
	private Color skyBlue = new Color(220, 240, 244);
	private Color darkGray = new Color(127, 127, 127);
	private Color lightGray = new Color(191, 191, 191);
	private Font sanserifBig = new Font("SanSerif", Font.BOLD, 25);
	private Font sanserifMid = new Font("SanSerif", Font.BOLD, 17);
	private Font sanserifSmall = new Font("SanSerif", Font.PLAIN, 15);
	// 위치
	private int startX = 20, startY = 40, startW = 150, startH = 30;
	private int txtStartX = 20, txtStartY = 70, txtStartW = 180, txtStartH = 30;
	private int btnStartX = 55, btnStartY = 280, btnStartW = 100, btnStartH = 20;
	private int labelPY = 65 , inTxtPY = 65, btnPY = 35;
	// 내용물들
	private String [] nameStr = {"     단어정보", "Eng", "Kor",  /**/ "추가", "수정", "삭제", "검색"};
	private JLabel [] label = new JLabel[3];
	private JButton [] btn = new JButton[4];
	private JTextField [] inputTxt = new JTextField[3];
	// 생성자
	public WordAdminPanel() {
		int index = 0;
		setBackground(skyBlue);
		setLayout(null);
		
		searchWordListener = new WordSearchListener(inputTxt, this);
		modifyListener = new WordModifyListener(inputTxt, this);
		
		for(int i=0; i<nameStr.length; i++) {
			if(i<3) {
				label[i] = new JLabel(nameStr[i]);
				inputTxt[i] = new JTextField(10);
				inputTxt[i].setFont(sanserifSmall);
				if(i==0) {
					// "단어정보" 라벨
					label[i].setFont(sanserifBig);
					label[i].setBounds(startX, startY, startW, startH);
					inputTxt[i].setBounds(txtStartX, txtStartY += inTxtPY, txtStartW, txtStartH);
				} else {
					// eng, kor 라벨
					label[i].setFont(sanserifMid);
					label[i].setBounds(startX, startY += labelPY, startW, startH);
					if(i<2) {
						// eng, kor 텍스트필드
						inputTxt[i].setBounds(txtStartX, txtStartY += inTxtPY, txtStartW, txtStartH);
					} else {
						// 검색을 위한 텍스트필드
						inputTxt[i].setBounds(240, txtStartY += 150, txtStartW -= 25, txtStartH);
						inputTxt[i].addKeyListener(searchWordListener);
					}
				}
				this.add(inputTxt[i]);
				this.add(label[i]);
			} else {
				btn[index] = new JButton(nameStr[i]);
				btn[index].setForeground(Color.WHITE);
				btn[index].setBackground(darkGray);
				btn[index].setFont(sanserifMid);
				if(i<(nameStr.length-1)) {
					btn[index].addActionListener(modifyListener);
					if(i==3) {
						// 추가 버튼
						btn[index].setBounds(btnStartX, btnStartY, btnStartW, btnStartH);
					} else {
						// 수정, 삭제 버튼
						btn[index].setBounds(btnStartX, btnStartY += btnPY, btnStartW, btnStartH);
					}
				} else {
					// 검색 버튼
					btn[index].setBounds(400, txtStartY, 70, txtStartH);
					btn[index].addActionListener(searchWordListener);
				}
				this.add(btn[index]);
				index++;
			}
		}
		// 단어목록
		wordList = new DBListPanel();
		wordList.setBounds(230, 20, 240, 310);
//		wordList.setBounds(230, 60, 240, 270);		selEngKorCombo.setBounds(420, 20, 50, 25);
		listener = new WordTableClickListener(inputTxt, wordList);
		wordList.getTable().addMouseListener(listener);
		this.add(wordList);
	}
	

	// 분리선
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(lightGray);
		g.drawLine(220, 0, 220, getHeight());
		
	}
	
	public DBListPanel getWordList() {
		return wordList;
	}

}
