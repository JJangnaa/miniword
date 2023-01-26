package MemberFrame;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

// 다시 생각해보는 것도 좋음
public class RequestDialog extends JDialog{
	
	private JLabel selectAD = new JLabel("1. 원하시는 것을 선택해주세요.");
	private JLabel opnionAD = new JLabel("2. 요청사항을 입력해주세요.");
	private JRadioButton addRadioBtn = new JRadioButton("추가");
	private JRadioButton modifyRadioBtn = new JRadioButton("수정");
	private ButtonGroup radioBtnGroup = new ButtonGroup();
	private JTextArea opnionArea = new JTextArea(10, 10);
	private JLabel noticeLabel = new JLabel("* 요청사항이 무조건 반영되는 것은 아닌 점 양해 부탁드립니다.");
	private JButton submitBtn = new JButton("submit");
	
	private JLabel requestLabel;
	private MemberRequestListener requestListener;
	
	private FontColorFrame fontNcolor = new FontColorFrame();
	
	public RequestDialog(JFrame frame, JLabel requestLabel) {
		super(frame);
		this.requestLabel = requestLabel;
		
		requestListener = new MemberRequestListener(this);
		
		setTitle("Request");
		Container c = getContentPane();
		c.setBackground(fontNcolor.getLightGray());
		c.setLayout(null);
		
		selectAD.setFont(fontNcolor.getSanserifBig());
		selectAD.setForeground(fontNcolor.getDarkGray());
		selectAD.setBounds(20, 45, 300, 30);
		
		addRadioBtn.setFont(fontNcolor.getSanserifNormal());
		addRadioBtn.setForeground(fontNcolor.getDarkGray());
		addRadioBtn.setSelected(true);
		addRadioBtn.setBounds(40, 85, 75, 25);
		radioBtnGroup.add(addRadioBtn);
		modifyRadioBtn.setFont(fontNcolor.getSanserifNormal());
		modifyRadioBtn.setForeground(fontNcolor.getDarkGray());
		modifyRadioBtn.setBounds(120, 85, 75, 25);
		radioBtnGroup.add(modifyRadioBtn);
		
		opnionAD.setFont(fontNcolor.getSanserifBig());
		opnionAD.setForeground(fontNcolor.getDarkGray());
		opnionAD.setBounds(20, 135, 300, 30);
		
		opnionArea.setFont(fontNcolor.getSanserifsmall());
		opnionArea.setBounds(20, 175, 400, 100);
		
		noticeLabel.setFont(fontNcolor.getSanserifnotice());
		noticeLabel.setForeground(fontNcolor.getLittlelightGray());
		noticeLabel.setHorizontalAlignment(JLabel.CENTER);
		noticeLabel.setBounds(20, 290, 400, 30);
		
		submitBtn.setFont(fontNcolor.getSanserifNormal());
		submitBtn.setBackground(fontNcolor.getNavy());
		submitBtn.setForeground(Color.WHITE);
		submitBtn.setBounds(150, 330, 150, 30);
		
		submitBtn.addActionListener(requestListener);
		
		c.add(selectAD);
		c.add(addRadioBtn);
		c.add(modifyRadioBtn);
		c.add(opnionAD);
		c.add(opnionArea);
		c.add(noticeLabel);
		c.add(submitBtn);
		
		setLocation(725, 320);
		setSize(450, 450);
		setResizable(false);
	}

	public JLabel getRequestLabel() {
		return requestLabel;
	}

	public void setRequestLabel(JLabel requestLabel) {
		this.requestLabel = requestLabel;
	}
}
