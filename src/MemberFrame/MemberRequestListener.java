package MemberFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import LoginFrame.DB;

public class MemberRequestListener extends MouseAdapter implements ActionListener{
	
	private DB db = new DB();
	private RequestDialog requestDialog;
	private GamePanel gamePanel;
	
	private JRadioButton addRadioBtn;
	private JRadioButton modifyRadioBtn;
	private JTextArea opnionArea;
	private JLabel requestLabel;
	
	private JLabel nameLabel;
	private String idStr;
	
	private JLabel unUsedRequest;
	
	public MemberRequestListener(RequestDialog requestDialog, GamePanel gamePanel) {
		this.requestDialog = requestDialog;
		this.gamePanel = gamePanel;
		this.addRadioBtn= requestDialog.getAddRadioBtn();
		this.modifyRadioBtn = requestDialog.getModifyRadioBtn();
		this.opnionArea = requestDialog.getOpnionArea();
		this.requestLabel = requestDialog.getRequestLabel();
	}
	// 클릭시 다이얼로그 창 visible(true)
	public void mouseClicked(MouseEvent e) {
		if(requestLabel.getText().equals("* 단어 추가/수정 요청하기")) {
			requestDialog.setVisible(true);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		nameLabel = gamePanel.getNameLabel();
		if(!(opnionArea.getText().equals(""))) {
			int yesNo = JOptionPane.showConfirmDialog(null, "요청 하시겠습니까?", "Warning", JOptionPane.YES_NO_OPTION);
			if(yesNo == JOptionPane.YES_OPTION) {
				try {
					idStr = db.infoSurfID("id", "name", nameLabel.getText());
					if(addRadioBtn.isSelected()) {
						db.insertRequest(idStr, nameLabel.getText(), "추가", opnionArea.getText());
					} else if(modifyRadioBtn.isSelected()) {
						db.insertRequest(idStr, nameLabel.getText(), "수정", opnionArea.getText());
					}
					JOptionPane.showMessageDialog(null, "요청사항이 정상적으로 제출되었습니다 :)", "Thank you", JOptionPane.INFORMATION_MESSAGE);
					
					requestDialog.setVisible(false);
					requestLabel.setText(("* 요청 완료"));
					requestLabel.setEnabled(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "요청사항을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

}
