package ManagerFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RequestCheckFrame extends JFrame {
	
	// 지정 폰트 및 컬러
	private Color lightGray = new Color(242, 242, 242);
	private Color darkGray = new Color(127, 127, 127);
	private Font sanserifSmall = new Font("SanSerif", Font.BOLD, 15);
	private Font sanserifBig = new Font("SanSerif", Font.BOLD, 20);
	
	// 내용물
	private JButton okBtn = new JButton("확인");
	private JTextField goalTxt = new JTextField(" 단어 ");
	private JTextArea request = new JTextArea(" 요청사항" + "\n");
	
	public RequestCheckFrame() {
		setLayout(new FlowLayout());
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(lightGray);
		
		goalTxt.setFont(sanserifBig);
		goalTxt.setBackground(Color.WHITE);
		goalTxt.setBounds(15, 20, 400, 35);
		goalTxt.setEditable(false);
		c.add(goalTxt);
		
		request.setFont(sanserifBig);
		request.setBackground(Color.WHITE);
		request.setBounds(15, 60, 400, 150);
		request.setEditable(false);
		c.add(request);
		
		okBtn.setBackground(darkGray);
		okBtn.setForeground(Color.WHITE);
		okBtn.setFont(sanserifSmall);
		okBtn.setBounds(350, 220, 65, 20);
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
		});
		c.add(okBtn);
		
		setLocation(725, 400);
		setSize(450, 300);
		setResizable(false);
	}
	

}
