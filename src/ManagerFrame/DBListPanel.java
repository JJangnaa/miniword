package ManagerFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
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
	private Font sanserifMid = new Font("SanSerif", Font.PLAIN, 17);
	private String tables;
	private String alphabet;
	
	private Color skyBlue = new Color(220, 240, 244);
	
	private String[] selEngKorStr = {"All", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	private JComboBox<String> selEngKorCombo = new JComboBox<String>(selEngKorStr);
	
	private WordTableClickListener listener;
	
	public DBListPanel(String tables) {
		// request List up
		model = new DefaultTableModel(ob, requestStr);
		table = new JTable(model);
		js = new JScrollPane(table);
		js.setBounds(0, 0, 420, 250);
		wordDB.selectAllrequest(model, tables);
		this.add(js);
		// 하나의 행만 선택할 수 있게 하기 위함.
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public DBListPanel() {
		this.setBackground(skyBlue);
		renewalList();
	}
	public void renewalList() {
		setLayout(null);
		for(int i=0; i<requestStr.length; i++) {
			if(i<2) {
				JLabel changeFontWord = new JLabel(wordStr[i]);
				changeFontWord.setFont(sanserifMid);
				JLabel changeFontRequest = new JLabel(requestStr[i]);
				changeFontRequest.setFont(sanserifMid);
			} else {
				JLabel changeFontRequest = new JLabel(requestStr[i]);
				changeFontRequest.setFont(sanserifMid);
			}
		}
		
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
	public Object[][] getOb() {
		return ob;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public JScrollPane getJs() {
		return js;
	}
	
	

}
