package MemberFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import LoginFrame.DB;

public class GameListener implements ActionListener{
	
	private DB db = new DB();
	private JLabel question;
	private JLabel chance;
	private int chanceInt = 9;
	
	public GameListener(JLabel question, JLabel chance) {
		this.question = question;
		this.chance = chance;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Retry") && chance.getText().equals("* 기회: 0번")) {
			try {
				question.setText(db.randomWord());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			chanceInt = 9;
			chance.setText("* 기회: 10번");
		} else {
			chance.setText("* 기회: " + chanceInt + "번");
			if(chanceInt >= 0) {
				chanceInt--;
			}
			if(chanceInt < 0) {
				JOptionPane.showMessageDialog(null, "Fail ;(", "Try once more !", JOptionPane.ERROR_MESSAGE);
				chanceInt = 0;
				
			}
			
		}
		
	}

}
