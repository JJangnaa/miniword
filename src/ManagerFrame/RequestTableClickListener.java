package ManagerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import LoginFrame.DB;

public class RequestTableClickListener extends MouseAdapter implements ActionListener{
	
	private RequestAdminPanel requestPanel;
	private RequestCheckDialog requestDialog;
	private DBListPanel wordList;
	private DB db = new DB();
	
	// 문자열 뽑아내기, 분리, 담기 위한 것들
	private int index = 0;
	private String [] selectRequest = new String[4];
	private String [] pushRequest = new String[4];
	private String strRequest;
	private StringTokenizer tokenRequest;
	private String id;
	private String addDelete;
	private String content;
	
	private int yesNo;
	
	private JTextField goalTxt;	// " 단어 "
	private JTextArea request;	// " 요청사항" + "\n"
		
	public RequestTableClickListener(RequestAdminPanel requestPanel, RequestCheckDialog requestDialog) {
		this.requestPanel = requestPanel;
		this.requestDialog = requestDialog;
		this.wordList = requestPanel.getWordList();
		this.goalTxt = requestDialog.getGoalTxt();
		this.request = requestDialog.getRequest();
	}
	
	public void mouseClicked(MouseEvent e) {
		rowInfo();
		// RequestTable 의 column 인 add/delete 및 content 를 String에 담아주기~
		addDelete = pushRequest[2];
		content = pushRequest[3];
		// 다이얼로그에 채워주기
		goalTxt.setText(goalTxt.getText()+" "+addDelete);
		request.setText(" " + content);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		yesNo = JOptionPane.showConfirmDialog(null, "삭제되면 복구가 불가합니다.\n정말 삭제하시겠습니까?", "Warning", JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION) {
			rowInfo();
			id = pushRequest[0];
			content = pushRequest[3];
			db.deleteRequest(id, content);
			JOptionPane.showMessageDialog(null, "삭제되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
			db.recallRequest(wordList.getModel(), wordList.getTable());
		}
	}
	
	// 클릭한 행의 정보 뽑아내기
	public void rowInfo() {
		int row = wordList.getTable().getSelectedRow();
		for(int i=0; i<wordList.getTable().getColumnCount(); i++) {
			selectRequest[index] = wordList.getTable().getModel().getValueAt(row, i) + "!";
			index++;
		}
		// 뽑아낸 것 중 필요한 것만 문자열에 담기
		strRequest = selectRequest[0].concat(selectRequest[1].concat(selectRequest[2].concat(selectRequest[3])));
		// 초기화
		index = 0;
		// 바로 전에 담은 문자열 분리하여 담기
		tokenRequest = new StringTokenizer(strRequest, "!");
		while(tokenRequest.hasMoreElements()) {
			pushRequest[index] = tokenRequest.nextToken();
			index++;
		}
		// 초기화
		index = 0;
	}

	public int getYesNo() {
		return yesNo;
	}
}
