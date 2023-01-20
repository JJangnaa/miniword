package ManagerFrame;

import java.awt.Font;

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
	
	public DBListPanel(String col) {
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
		
		if(col.equals("word")) {	// word List up
			model = new DefaultTableModel(ob, wordStr);
			table = new JTable(model);
			js = new JScrollPane(table);
			js.setBounds(0, 0, 240, 270);
			wordDB.selectAllword(model, col);
			this.add(js);
		} else {	// request List up
			model = new DefaultTableModel(ob, requestStr);
			table = new JTable(model);
			js = new JScrollPane(table);
			js.setBounds(0, 0, 420, 250);
			wordDB.selectAllrequest(model, col);
			this.add(js);
		}
		// 하나의 행만 선택할 수 있게 하기 위함.
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public JTable getTable() {
		return table;
	}
	
	

}
