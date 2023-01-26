package MemberFrame;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import LoginFrame.DB;

public class GamePanel extends JPanel {
	
	private Color blueGreen = new Color(50, 142, 160);
	private Color lightBlue = new Color(224, 239, 242);
	private Color deepBlue = new Color(161, 173, 186);
	private Color likeBlack = new Color(38, 38, 38);
	private Color darkGray = new Color(127, 127, 127);
	private Color lightGray = new Color(235, 235, 235);
	
	private LineBorder border = new LineBorder(lightGray, 2);
	private LineBorder submitBorder = new LineBorder(lightBlue, 2, true);
	private LineBorder retryBorder = new LineBorder(deepBlue, 2, true);
	
	private JLabel chance;
	private JLabel question;
	
	private String questionStr;
	private RandomQuestion randomStr;
	
	private DB db = new DB();
	private RetryButtonListener listener;
	
	private JTextField quiz;
	private JTextField answer;
	private JTextField inputAnswer;
	private JButton checkAnswerBtn;
	private JButton retryBtn;
	
	public GamePanel() {
		setBackground(blueGreen);
		setLayout(null);
		
		chance = new JLabel("* 기회: 10번");
		chance.setForeground(Color.WHITE);
		chance.setFont(new Font("SanSerif", Font.BOLD, 20));
		chance.setBounds(350, 40, 150, 25);
		
		quiz = new JTextField("Quiz");
		quiz.setEditable(false);
		quiz.setBackground(lightGray);
		quiz.setForeground(darkGray);
		quiz.setFont(new Font("SanSerif", Font.BOLD, 18));
		quiz.setHorizontalAlignment(JTextField.CENTER);
		quiz.setBorder(border);
		quiz.setBounds(40, 80, 80, 20);
		
		// [문제뽑아내기]
		question = new JLabel();
		try {	// 1) 단어 랜덤 추출
			questionStr = db.randomWord();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2) 추출된 단어 철자 랜덤으로 바꾸기
		randomStr = new RandomQuestion(questionStr);
		question.setText(randomStr.getRandomStr());
		// 3) 세부설정
		question.setForeground(Color.WHITE);
		question.setFont(new Font("SanSerif", Font.BOLD, 35));
		question.setBounds(40, 100, 410, 150);
		question.setHorizontalAlignment(JLabel.CENTER);
		question.setBorder(border);
		
		answer = new JTextField("정답");
		answer.setEditable(false);
		answer.setBackground(lightGray);
		answer.setForeground(likeBlack);
		answer.setFont(new Font("SanSerif", Font.BOLD, 16));
		answer.setHorizontalAlignment(JTextField.CENTER);
		answer.setBorder(border);
		answer.setBounds(98, 295, 70, 20);
		
		inputAnswer = new JTextField();
		inputAnswer.setBorder(border);
		inputAnswer.setFont(new Font("SanSerif", Font.PLAIN, 14));
		inputAnswer.setBounds(170, 295, 150, 20);
		
		checkAnswerBtn = new JButton("확인");
		checkAnswerBtn.setBackground(lightBlue);
		checkAnswerBtn.setForeground(likeBlack);
		checkAnswerBtn.setFont(new Font("SanSerif", Font.BOLD, 16));
		checkAnswerBtn.setBorder(submitBorder);
		checkAnswerBtn.setBounds(335, 295, 70, 20);
		
		retryBtn = new JButton("Retry");
		retryBtn.setBackground(deepBlue);
		retryBtn.setForeground(Color.WHITE);
		retryBtn.setFont(new Font("SanSerif", Font.BOLD, 18));
		retryBtn.setBorder(retryBorder);
		retryBtn.setBounds(190, 350, 120, 28);
		
		// 리스너 달기
		listener = new RetryButtonListener(question, chance, inputAnswer, questionStr, checkAnswerBtn);
		retryBtn.addActionListener(listener);
		checkAnswerBtn.addActionListener(listener);
		
		this.add(chance);
		this.add(question);
		this.add(quiz);
		this.add(answer);
		this.add(inputAnswer);
		this.add(checkAnswerBtn);
		this.add(retryBtn);
		
	}
}
