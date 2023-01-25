package MemberFrame;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

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
	private DB db = new DB();
	private GameListener listener;
	
	private JTextField quiz;
	private JTextField answer;
	private JTextField inputAnswer;
	private JButton submit;
	private JButton retry;
	
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
		
		question = new JLabel();
		try {
			questionStr = db.randomWord();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		question.setText(questionStr);
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
		
		submit = new JButton("확인");
		submit.setBackground(lightBlue);
		submit.setForeground(likeBlack);
		submit.setFont(new Font("SanSerif", Font.BOLD, 16));
		submit.setBorder(submitBorder);
		submit.setBounds(335, 295, 70, 20);
		
		retry = new JButton("Retry");
		retry.setBackground(deepBlue);
		retry.setForeground(Color.WHITE);
		retry.setFont(new Font("SanSerif", Font.BOLD, 18));
		retry.setBorder(retryBorder);
		retry.setBounds(190, 350, 120, 28);
		
		listener = new GameListener(question, chance);
		submit.addActionListener(listener);
		retry.addActionListener(listener);
		
		
		this.add(chance);
		this.add(question);
		this.add(quiz);
		this.add(answer);
		this.add(inputAnswer);
		this.add(submit);
		this.add(retry);
		
	}
}
