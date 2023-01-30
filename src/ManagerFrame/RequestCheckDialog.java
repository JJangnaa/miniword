package ManagerFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RequestCheckDialog extends JDialog {
	// 지정 폰트 및 컬러
	private Color lightGray = new Color(242, 242, 242);
	private Color darkGray = new Color(127, 127, 127);
	private Font sanserifSmall = new Font("SanSerif", Font.BOLD, 15);
	private Font sanserifBig = new Font("SanSerif", Font.BOLD, 20);
	
	// 내용물
	private JButton okBtn = new JButton("확인");
	private JLabel addNdel = new JLabel("단어 : ");
	private JTextArea request = new JTextArea("");
	
	private RequestAdminPanel requestPanel;
	private RequestTableClickListener listener;
	// 생성자
	public RequestCheckDialog(JFrame frame, RequestAdminPanel requestPanel) {
		super(frame, true);
		setTitle("Check the request ");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(lightGray);
		
		this.requestPanel = requestPanel;
		
		addNdel.setFont(sanserifBig);
		addNdel.setBackground(Color.WHITE);
		addNdel.setBounds(15, 20, 400, 35);
		addNdel.setEnabled(false);
		c.add(addNdel);
		
		request.setFont(sanserifBig);
		request.setBackground(Color.WHITE);
		request.setBounds(15, 60, 400, 150);
		request.setEditable(false);
		c.add(request);
		
		okBtn.setBackground(darkGray);
		okBtn.setForeground(Color.WHITE);
		okBtn.setFont(sanserifSmall);
		okBtn.setBounds(350, 220, 65, 20);
		
		listener = new RequestTableClickListener(requestPanel, this);
		okBtn.addActionListener(listener);
//		okBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				setVisible(false);
//				addNdel.setText("단어 : ");
//				request.setText("");
//				listener.getPushRequest().clear();
//			}
//			
//		});
		
		if(this.isVisible() == false) {
			addNdel.setText("단어 : ");
			request.setText("");
			listener.getPushRequest().clear();
		}
		
		
		c.add(okBtn);
		
		setLocation(725, 400);
		setSize(450, 300);
		setResizable(false);
	}
	
	public JLabel getGoalTxt() {
		return addNdel;
	}
	public JTextArea getRequest() {
		return request;
	}
	
	public void setRequest(JTextArea request) {
		this.request = request;
	}
	
	public void setAddNdel(JLabel addNdel) {
		this.addNdel = addNdel;
	}

	public JLabel getAddNdel() {
		return addNdel;
	}

	public JButton getOkBtn() {
		return okBtn;
	}

}
