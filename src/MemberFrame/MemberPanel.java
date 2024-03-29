package MemberFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import LoginFrame.DB;

public class MemberPanel extends JPanel {
	
	private Color skyBlue = new Color(220, 240, 244);
	private Color darkGray = new Color(127, 127, 127);
	private Color lightGray = new Color(191, 191, 191);
	
	private String[] selEngKorStr = {"All", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	private JComboBox<String> selEngKorCombo = new JComboBox<String>(selEngKorStr);
	
	private JLabel requestLabel;
	private JTextField searchWordTxt;
	private JButton searchBtn;
	
	private Object ob[][] = new Object[0][2];
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane js;
	private String [] wordStr = {"Eng", "Kor"};
	private DB wordDB = new DB();
	private String alphabet;
	
	private SearchListener listener;
	
	private FontColorFrame fc = new FontColorFrame();
	private RequestDialog request;
	private MemberRequestListener requestListener;
	
	private GamePanel gamePanel;
	private JLabel nameLabel;
	
	public MemberPanel(MemberFrame memberFrame, GamePanel gamePanel) {
		setBackground(skyBlue);
		setLayout(null);
		
		this.gamePanel = gamePanel;
		nameLabel = gamePanel.getNameLabel();
		
		model = new DefaultTableModel(ob, wordStr);
		table = new JTable(model);
		wordDB.selectAllword(model, table);
		js = new JScrollPane(table);
		js.setBounds(20, 50, 450, 300);
		selEngKorCombo.setBounds(419, 15, 50, 25);
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
		
		requestLabel = new JLabel("* 단어 추가/수정 요청하기");
		requestLabel.setForeground(lightGray);
		requestLabel.setBounds(20, 350, 200, 25);
		
		searchWordTxt = new JTextField();
		searchWordTxt.setBounds(240, 370, 150, 25);
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(390, 370, 80, 25);
		searchBtn.setBackground(darkGray);
		searchBtn.setForeground(Color.WHITE);
		
		request = new RequestDialog(memberFrame, gamePanel, requestLabel);
		
		requestListener = new MemberRequestListener(request, gamePanel);
		requestLabel.addMouseListener(requestListener);
		
		listener = new SearchListener(searchWordTxt, this);
		searchWordTxt.addKeyListener(listener);
		searchBtn.addActionListener(listener);
		
		this.add(js);
		this.add(selEngKorCombo);
		
		this.add(requestLabel);
		this.add(searchWordTxt);
		this.add(searchBtn);
		
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JTable getTable() {
		return table;
	}

	public JTextField getSearchWordTxt() {
		return searchWordTxt;
	}
	
	public JLabel getRequestLabel() {
		return requestLabel;
	}

	public JComboBox<String> getSelEngKorCombo() {
		return selEngKorCombo;
	}

	public RequestDialog getRequest() {
		return request;
	}
	
}
