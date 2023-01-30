package LoginFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ManagerFrame.ManagerFrame;
import ManagerFrame.RequestAdminPanel;
import ManagerFrame.RequestCheckDialog;
import ManagerFrame.WordAdminPanel;
import MemberFrame.GamePanel;
import MemberFrame.MemberFrame;
import MemberFrame.MemberPanel;
import MemberFrame.RandomQuestion;
import MemberFrame.RequestDialog;
import MemberFrame.RetryButtonListener;

public class LogOutListener extends MouseAdapter implements ActionListener{
	
	private LogInFrame logInFrame;
	private DB db = new DB();
	
	private ManagerFrame managerFrame;
	private WordAdminPanel adminPanel;
	private RequestAdminPanel requestPanel;
	private DefaultTableModel requestModel;
	private JTable requestTable;
	private JTextField [] inputTxt;
	private RequestCheckDialog checkDialog;
	
	private MemberFrame memberFrame;
	private MemberPanel memberPanel;
	private GamePanel gamePanel;
	private RetryButtonListener listener;
	private JLabel question;
	private String questionStr;
	private RandomQuestion randomStr;
	private JTextField inputAnswer;
	private JButton checkAnswerBtn;
	private JLabel chance;
	private RequestDialog request;
	private JLabel hint;
	
	// 관리자 창 생성자
	public LogOutListener(LogInFrame logInFrame, ManagerFrame managerFrame) {
		this.logInFrame = logInFrame;
		this.managerFrame = managerFrame;
		
		this.adminPanel = managerFrame.getAdminPanel();
		this.inputTxt = adminPanel.getInputTxt();
		
		this.requestPanel = managerFrame.getRequestPanel();
		this.checkDialog = requestPanel.getCheckDialog();
	}
	// 회원 창 생성자
	public LogOutListener(LogInFrame logInFrame, MemberFrame memberFrame) {
		this.logInFrame = logInFrame;
		this.memberFrame = memberFrame;
		
		this.memberPanel = memberFrame.getMemberPanel();
		this.request = memberPanel.getRequest();
		
		this.gamePanel = memberFrame.getGamePanel();
		this.listener = gamePanel.getListener();
		this.question = gamePanel.getQuestion();
		this.questionStr = gamePanel.getQuestionStr();
		this.randomStr = gamePanel.getRandomStr();
		this.inputAnswer = gamePanel.getInputAnswer();
		this.checkAnswerBtn = gamePanel.getCheckAnswerBtn();
		this.chance = gamePanel.getChance();
		this.hint = gamePanel.getHint();
		
	}
	// 관리자 창 로그아웃 버튼 클릭시
	public void mouseClicked(MouseEvent e) {
		int yesNo = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Are you sure?", JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION) {
			// 1. 관리자 창 안보이게 하고 로그인 창 띄우기
			managerFrame.setVisible(false);
			logInFrame.setVisible(true);
			// 2. 단어관리 탭
			//  2-1) 후에 관리자창 띄웠을 때, 단어관리 탭으로 설정
			managerFrame.getPane().setSelectedIndex(0);
			//  2-2) 단어리스트 전부 나오게
			adminPanel.getWordList().getSelEngKorCombo().setSelectedIndex(0);
			//  2-3) 텍스트필드 리셋
			for(int i=0; i<inputTxt.length; i++) {
				inputTxt[i].setText("");
			}
			// 3. 요청사항 탭: 요청사항 리스트 다시 띄우기
			requestTable = requestPanel.getWordList().getTable();
			requestModel = requestPanel.getWordList().getModel();
			db.selectAllrequest(requestModel, requestTable);
			// 4. 상세요청사항(RequestCheckDialog) 리셋
			checkDialog.getAddNdel().setText("단어 : ");
			checkDialog.getRequest().setText("");
		}
	}
	// 회원창 로그아웃 버튼 클릭시
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int yesNo = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Are you sure?", JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION) {
			// 1. 회원 창 안보이게 하고 로그인 창 띄우기
			memberFrame.setVisible(false);
			logInFrame.setVisible(true);
			memberFrame.getPane().setSelectedIndex(0);
			// 2. 단어검색 탭
			//  2-1) 단어 검색 텍스트필드 리셋
			memberPanel.getSearchWordTxt().setText("");
			//  2-2) 요청사항 라벨 리셋
			memberPanel.getRequestLabel().setText("* 단어 추가/수정 요청하기");
			//  2-3) 단어리스트 전부 나오게
			memberPanel.getSelEngKorCombo().setSelectedIndex(0);
			// 3. 미니게임 탭
			//  3-1) 문제 새롭게 내기
			try {
				questionStr = db.randomWord();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			randomStr = new RandomQuestion(questionStr);
			question.setText(randomStr.getRandomStr());
			listener.setDisposableStr(questionStr);
			//  3-2) 답 텍스트필드 editable(true) 변경
			inputAnswer.setEnabled(true);
			inputAnswer.setText("");
			//  3-3) 확인 버튼 enable(true) 변경
			checkAnswerBtn.setEnabled(true);
			//  3-4) 기회 10번으로 변경
			listener.setChanceInt(9);
			chance.setText("* 기회: 10번");
			//  3-5) RetryBtnListener 클래스 리셋해주기
			listener.setDisposable(true);
			listener.setMultiuse(true);
			// 4. RequestCheckDialog 요청사항 란 비우기
			request.getOpnionArea().setText("");
			// 5. hint 초기화
			hint.setEnabled(true);
			hint.setText("hint");
			hint.setFont(new Font("SanSerif", Font.PLAIN, 13));
		}
		
	}

}
