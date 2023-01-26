package MemberFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// 전체적으로 손봐야함
public class MemberRequestListener extends MouseAdapter implements ActionListener{
	
	private RequestDialog requestDialog;
	private JButton submitBtn;
	private JLabel unUsedRequest;
	public MemberRequestListener(JButton submitBtn) {
		this.submitBtn = submitBtn;
	}
	public MemberRequestListener(RequestDialog requestDialog) {
		this.requestDialog = requestDialog;
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==1) {
			requestDialog.setVisible(true);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("submit")) {
			int yesNo = JOptionPane.showConfirmDialog(null, "요청 하시겠습니까?\n*주의: 제출기회 하루에 한번", "Warning", JOptionPane.YES_NO_OPTION);
			if(yesNo == JOptionPane.YES_OPTION) {
				requestDialog.getRequestLabel().setText(("* 단어 추가/수정 요청하기 << 사용불가(요청기회 소진)"));
				requestDialog.getRequestLabel().removeMouseListener(this);
				requestDialog.setVisible(false);
			}
		}
	}

}
