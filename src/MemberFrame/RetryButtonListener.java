package MemberFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import LoginFrame.DB;

public class RetryButtonListener implements ActionListener{
	
	private DB db = new DB();
	private JLabel question;
	private JLabel chance;
	private JTextField inputAnswer;
	private JButton checkAnswerBtn;
	
	private int chanceInt = 9;
	private boolean disposable = true;
	private boolean multiuse = true;
	private String disposableStr;
	
	private String questionStr;
	private RandomQuestion randomStr;
	
	public RetryButtonListener(JLabel question, JLabel chance, JTextField inputAnswer, String disposableStr, JButton checkAnswerBtn) {
		this.question = question;
		this.chance = chance;
		this.disposableStr = disposableStr;
		this.inputAnswer = inputAnswer;
		this.checkAnswerBtn = checkAnswerBtn;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// [Retry 버튼]
		if(e.getActionCommand().equals("Retry")) {
			// * 기회소진 및 정답일 경우에만 작동하게 하기 위함
			if(!inputAnswer.isEnabled()) {
				// 단어 랜덤 추출
				try {
					questionStr = db.randomWord();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 추출된 단어 철자 순서변경 및 set
				randomStr = new RandomQuestion(questionStr);
				question.setText(randomStr.getRandomStr());
				
				reset();
			// ** 그렇지 않을 경우 경고창 띄우기
			} else {
				JOptionPane.showMessageDialog(null, "게임을 다 끝낸 후 눌러주세요.", "Cheer up!", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		// [확인] 버튼
		if(e.getActionCommand().equals("확인")) {
			// 1. GamePanel 내 questionStr(==disposableStr) 용도 (일회성)
			if(disposable) {
				chance.setText("* 기회: " + chanceInt + "번");
				// 1-1) 오답일 경우
				if(!inputAnswer.getText().equals(disposableStr)) {
					if(chanceInt==0 && !(chanceInt<0)) {
						JOptionPane.showMessageDialog(null, "Fail ;(\nCorrect answer: " + disposableStr, "Try once more !", JOptionPane.ERROR_MESSAGE);
						displsafinishSetting();
					} else {
						JOptionPane.showMessageDialog(null, "That's not correct answer.\nPlease try again", ";(", JOptionPane.ERROR_MESSAGE);
						inputAnswer.setText("");
						chanceInt--;
						
					}
				// 1-2) 정답일 경우
				} else {
					JOptionPane.showMessageDialog(null, "🎊 Congratulations :) ❤ \nthe answer in your " + (10-chanceInt) + " attempt", "Correct Answer", JOptionPane.PLAIN_MESSAGE);
					displsafinishSetting();
				}
			// 2. Retry 버튼 클릭시 추출되는 questionStr(==questionStr) 용도 (다회성)
			} else if(!multiuse){
				chance.setText("* 기회: " + chanceInt + "번");
				// 2-1) 오답일 경우
				if(!inputAnswer.getText().equals(questionStr)) {
					if(chanceInt==0 && !(chanceInt<0)) {
						JOptionPane.showMessageDialog(null, "Fail ;(\nCorrect answer: " + questionStr, "Try once more !", JOptionPane.ERROR_MESSAGE);
						finishSetting();
					} else {
						JOptionPane.showMessageDialog(null, "That's not correct answer.\nPlease try again", ";(", JOptionPane.ERROR_MESSAGE);
						inputAnswer.setText("");
						chanceInt--;
					}
				// 2-2) 정답일 경우
				} else {
					JOptionPane.showMessageDialog(null, "🎊 Congratulations :) ❤ \nthe answer in your " + (10-chanceInt) + " attempt", "Correct Answer", JOptionPane.PLAIN_MESSAGE);
					finishSetting();
				} 
			}
		}
		
	}
	// Retry 누를 경우, 필요한 기본세팅
	public void reset() {
		chanceInt = 9;
		chance.setText("* 기회: 10번");
		multiuse = false;
		checkAnswerBtn.setEnabled(true);
		inputAnswer.setEnabled(true);
	}
	// 기회소진 및 정답일 경우, 동일하게 필요한 세팅 (다회성용 메소드)
	public void finishSetting() {
		inputAnswer.setText("");
		inputAnswer.setEnabled(false);
		checkAnswerBtn.setEnabled(false);
		multiuse = true;	// 차이점
	}
	// 기회소진 및 정답일 경우, 동일하게 필요한 세팅 (일회성용 메소드)
	public void displsafinishSetting() {
		inputAnswer.setText("");
		inputAnswer.setEnabled(false);
		checkAnswerBtn.setEnabled(false);
		disposable = false;	// 차이점
	}
	

}
