package ManagerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	private String [] selectRequest = new String[4];
	private ArrayList<String> pushRequest = new ArrayList<String>();
	private String id;
	private String addDelete;
	private String content;
	
	private int yesNo;
	
	private JLabel addNdel;	// "단어 : "
	private JTextArea request;	// ""
	
	private JButton [] btn;
	private JButton okBtn;
		
	public RequestTableClickListener(RequestAdminPanel requestPanel, RequestCheckDialog requestDialog) {
		this.requestPanel = requestPanel;
		this.requestDialog = requestDialog;
		this.wordList = requestPanel.getWordList();
		this.addNdel = requestDialog.getGoalTxt();
		this.request = requestDialog.getRequest();
		this.btn = requestPanel.getBtn();
		this.okBtn = requestDialog.getOkBtn();
	}
	
	// [RequestAdminPanel > 리스트]
	public void mouseClicked(MouseEvent e) {
		// 리스트 클릭 시, 클릭한 행의 정보 뽑아내는 메소드 호출
		rowInfo();
	}
	// [버튼]
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton btn = (JButton) e.getSource();
		
		// [ RequestAdminPanel > 삭제(this.btn[0]) : 선택한 요청사항 삭제]
		if(this.btn[0] == btn) {
			// 1. 요청사항 리스트 선택 없이 버튼 누른 경우 경고창
			if(requestPanel.getWordList().getTable().getSelectedRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "확인하고 싶은 요청사항을 선택 후 눌러주세요.", "Warning", JOptionPane.ERROR_MESSAGE);
			// 2. 요청사항 리스트 선택 후 버튼 클릭
			} else {
				yesNo = JOptionPane.showConfirmDialog(null, "삭제되면 복구가 불가합니다.\n정말 삭제하시겠습니까?", "Warning", JOptionPane.YES_NO_OPTION);
				if(yesNo == JOptionPane.YES_OPTION) {
					// 2-1) 뽑아낸 정보 배열 담기
					for(int i=0; i<selectRequest.length; i++) {
						pushRequest.add(selectRequest[i]);
					}
					// 2-2) 배열에 담긴 정보 중, 필요한 것만 String 에 담기
					id = pushRequest.get(0);
					content = pushRequest.get(3);
					// 2-3) 배열 비우기
					pushRequest.clear();
					// 2-4) 데이터 지우기
					db.deleteRequest(id, content);
					JOptionPane.showMessageDialog(null, "삭제되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
					// 2-5) 요청사항 리스트 다시 불러오기 & 요청사항 다이얼로그 내용 초기화
					db.selectAllrequest(wordList.getModel(), wordList.getTable());
					addNdel.setText("단어 : ");
					request.setText("");
				}
			}
			
		// [ RequestAdminPanel > 확인(this.btn[1]) : 선택한 요청사항 상세정보 확인]
		} else if(this.btn[1] == btn) {
			// 1. 요청사항 리스트 선택 없이 버튼 누른 경우 경고창
			if(requestPanel.getWordList().getTable().getSelectedRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "확인하고 싶은 요청사항을 선택 후 눌러주세요.", "Warning", JOptionPane.ERROR_MESSAGE);
			// 2. 요청사항 리스트 선택 후 버튼 클릭
			} else {
				// 2-1) 뽑아낸 정보 배열 담기
				for(int i=0; i<selectRequest.length; i++) {
					pushRequest.add(selectRequest[i]);
				}
				// 2-2) 배열에 담긴 정보 중, 필요한 것만 String 에 담기 & set 해주기
				addDelete = pushRequest.get(2);
				content = pushRequest.get(3);
				addNdel.setText("단어 : "+addDelete);
				request.setText(content);
				// 2-3) 배열 비우기
				pushRequest.clear();
				// 2-4) 요청사항 다이얼로그
				requestDialog.setVisible(true);
			}
			
		// [RequestCheckDialog > 확인(okBtn)]
		} else if(this.okBtn == btn) {
			// 요청사항 다이얼로그 false 및 내용물 초기화
			requestDialog.setVisible(false);
			addNdel.setText("단어 : ");
			request.setText("");
		}
		
	}
	
	// 클릭한 행의 정보 뽑아내기
	public void rowInfo() {
		int row = wordList.getTable().getSelectedRow();
		for(int i=0; i<wordList.getTable().getColumnCount(); i++) {
			selectRequest[i] = wordList.getTable().getModel().getValueAt(row, i) +"";
		}
	}

	public int getYesNo() {
		return yesNo;
	}

	public ArrayList<String> getPushRequest() {
		return pushRequest;
	}

	public void setPushRequest(ArrayList<String> pushRequest) {
		this.pushRequest = pushRequest;
	}
}
