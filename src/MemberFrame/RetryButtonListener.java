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
	private RandomQuestion randomStr;
	private GamePanel gamePanel;
	
	private JLabel question;
	private JLabel chance;
	private JTextField inputAnswer;
	private JButton checkAnswerBtn;
	
	private int chanceInt = 9;
	private boolean disposable = true;
	private boolean multiuse = true;
	private String disposableStr;
	private String questionStr;
	private int pullGrade;
	private int pushGrade;
	private JLabel logInName;
	
	public RetryButtonListener(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.question = gamePanel.getQuestion();
		this.chance = gamePanel.getChance();
		this.disposableStr = gamePanel.getQuestionStr();
		this.inputAnswer = gamePanel.getInputAnswer();
		this.checkAnswerBtn = gamePanel.getCheckAnswerBtn();
		this.logInName =gamePanel.getNameLabel(); 
		
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
					// (기회소진 O)
					if(chanceInt==0 && !(chanceInt<0)) {
						// a) 점수 뽑기 및 db에 넣기
						gamePanel.getGradeTxt().setText(Integer.toString(chanceInt));
						db.inputGrade(failGrade(pullGrade), logInName.getText());
						// b) 알림창 및 게임끝일 때의 세팅
						JOptionPane.showMessageDialog(null, "Fail ;(\nCorrect answer: " + disposableStr, "Try once more !", JOptionPane.ERROR_MESSAGE);
						displsafinishSetting();
					// (기회소진 X)
					} else {
						JOptionPane.showMessageDialog(null, "That's not correct answer.\nPlease try again", ";(", JOptionPane.ERROR_MESSAGE);
						inputAnswer.setText("");
						chanceInt--;
					}
				// 1-2) 정답일 경우
				} else {
					// a) 점수 뽑기 및 db에 넣기
					pullGrade = (10-chanceInt);
					gamePanel.getGradeTxt().setText(Integer.toString(successGrade(pullGrade)));
					db.inputGrade(successGrade(pullGrade), logInName.getText());
					// b) 알림창 및 게임끝일 때의 세팅
					JOptionPane.showMessageDialog(null, "🎊 Congratulations :) ❤ \nthe answer in your " + (10-chanceInt) + " attempt", "Correct Answer", JOptionPane.PLAIN_MESSAGE);
					displsafinishSetting();
				}
			// 2. Retry 버튼 클릭시 추출되는 questionStr(==questionStr) 용도 (다회성)
			} else if(!multiuse){
				chance.setText("* 기회: " + chanceInt + "번");
				// 2-1) 오답일 경우
				if(!inputAnswer.getText().equals(questionStr)) {
					// (기회소진 O)
					if(chanceInt==0 && !(chanceInt<0)) {
						// a) 점수 뽑기 및 db에 넣기
						gamePanel.getGradeTxt().setText(Integer.toString(chanceInt));
						db.inputGrade(failGrade(pullGrade), logInName.getText());
						// b) 알림창 및 게임끝일 때의 세팅
						JOptionPane.showMessageDialog(null, "Fail ;(\nCorrect answer: " + questionStr, "Try once more !", JOptionPane.ERROR_MESSAGE);
						finishSetting();
					// (기회소진 X)
					} else {
						JOptionPane.showMessageDialog(null, "That's not correct answer.\nPlease try again", ";(", JOptionPane.ERROR_MESSAGE);
						inputAnswer.setText("");
						chanceInt--;
					}
				// 2-2) 정답일 경우
				} else {
					// a) 점수 뽑기 및 db에 넣기
					pullGrade = (10-chanceInt);
					gamePanel.getGradeTxt().setText(Integer.toString(successGrade(pullGrade)));
					db.inputGrade(successGrade(pullGrade), logInName.getText());
					// b) 알림창 및 게임끝일 때의 세팅
					JOptionPane.showMessageDialog(null, "🎊 Congratulations :) ❤ \nthe answer in your " + (10-chanceInt) + " attempt", "Correct Answer", JOptionPane.PLAIN_MESSAGE);
					finishSetting();
				} 
			}
		}
		
	}
	// 정답일 때 점수
	public int successGrade(int pullGrade) {
		switch(pullGrade) {
		case 1: pushGrade = 100;
			break;
		case 2: pushGrade = 90;
			break;
		case 3: pushGrade = 80;
			break;
		case 4: pushGrade = 70;
			break;
		case 5: pushGrade = 60;
			break;
		case 6: pushGrade = 50;
			break;
		case 7: pushGrade = 40;
			break;
		case 8: pushGrade = 30;
			break;
		case 9: pushGrade = 20;
			break;
		case 10: pushGrade = 10;	// chanceInt = 0
			break;
		default : pushGrade = 000;
			break;
			
		}
		return pushGrade;
	}
	// 오답일 때 점수
	public int failGrade(int chanceInt) {
		switch(chanceInt) {
			case 0 : pushGrade = 0;
					break;
			default : pushGrade = 000;
					break;
		}
		return pushGrade;
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
	
	public JLabel getChance() {
		return chance;
	}

	public JTextField getInputAnswer() {
		return inputAnswer;
	}

	public JButton getCheckAnswerBtn() {
		return checkAnswerBtn;
	}

	public void setChanceInt(int chanceInt) {
		this.chanceInt = chanceInt;
	}

	public int getChanceInt() {
		return chanceInt;
	}

	public void setDisposable(boolean disposable) {
		this.disposable = disposable;
	}
	
	public boolean isDisposable() {
		return disposable;
	}
	
	public void setMultiuse(boolean multiuse) {
		this.multiuse = multiuse;
	}

	public boolean isMultiuse() {
		return multiuse;
	}

	public String getDisposableStr() {
		return disposableStr;
	}

	public void setDisposableStr(String disposableStr) {
		this.disposableStr = disposableStr;
	}
	

}
