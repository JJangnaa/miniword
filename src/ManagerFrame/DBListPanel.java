package ManagerFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import LoginFrame.DB;

// JTable 이용한 word 및 request List
public class DBListPanel extends JPanel {
	
	private Object ob[][] = new Object[0][2];
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane js;
	
	private String [] wordStr = {"Eng", "Kor"};
	private String [] requestStr = {"ID", "NAME", "ADD/DELETE", "CONTENT"};
	private DB wordDB = new DB();
	private String alphabet;
	
	private Color blueGreen = new Color(50, 142, 160);
	private Color skyBlue = new Color(220, 240, 244);
	
	private String[] selEngKorStr = {"All", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	private JComboBox<String> selEngKorCombo = new JComboBox<String>(selEngKorStr);
	
	// WordList
	public DBListPanel() {
		this.setBackground(skyBlue);
		renewalList();
	}
	// RequestList
	public DBListPanel(String tables) {
		this.setBackground(blueGreen);
		setLayout(null);
		model = new DefaultTableModel(ob, requestStr);
		table = new JTable(model);
		wordDB.selectAllrequest(model, tables);
		js = new JScrollPane(table);
		js.setBounds(0, 0, 420, 200);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	// 하나의 행만 선택할 수 있게 하기 위함.
		this.add(js);
	}
	// WordList: 테이블 생성 메소드
	public void renewalList() {
		setLayout(null);
		model = new DefaultTableModel(ob, wordStr);
		table = new JTable(model);
		wordDB.selectAllword(model, table);
		js = new JScrollPane(table);
		js.setBounds(0, 40, 240, 270);
		selEngKorCombo.setBounds(190, 0, 50, 25);
		selEngKorCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox) e.getSource();
				int cbIndex = cb.getSelectedIndex();
				alphabet = selEngKorStr[cbIndex];
				wordDB.selectAlphabet(model, "word", alphabet, table);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(js);
		this.add(selEngKorCombo);
	}
	
	
	public JTable getTable() {
		return table;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	
	

}
