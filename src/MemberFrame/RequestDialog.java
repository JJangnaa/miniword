package MemberFrame;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RequestDialog extends JDialog{
	
	private JLabel selectLabel;
	private JLabel opnionLabel;
	private JRadioButton addRadioBtn;
	private JRadioButton modifyRadioBtn;
	private ButtonGroup radioBtnGroup;
	private JTextArea opnionArea;
	private JScrollPane scrollPane;
	private JLabel noticeLabel;
	private JButton submitBtn;
	
	private JLabel requestLabel;
	private MemberRequestListener requestListener;
	
	private FontColorFrame fontNcolor = new FontColorFrame();
	
	private JLabel nameLabel;
	
	public RequestDialog(MemberFrame memberFrame, GamePanel gamePanel, JLabel requestLabel) {
		super(memberFrame);
		this.requestLabel = requestLabel;
		
		nameLabel = gamePanel.getNameLabel();
		
		setTitle("Request");
		Container c = getContentPane();
		c.setBackground(fontNcolor.getLightGray());
		c.setLayout(null);
		
		selectLabel = new JLabel("1. 원하시는 것을 선택해주세요.");
		selectLabel.setFont(fontNcolor.getSanserifBig());
		selectLabel.setForeground(fontNcolor.getDarkGray());
		selectLabel.setBounds(20, 45, 300, 30);
		
		radioBtnGroup = new ButtonGroup();
		addRadioBtn = new JRadioButton("추가");
		addRadioBtn.setFont(fontNcolor.getSanserifNormal());
		addRadioBtn.setForeground(fontNcolor.getDarkGray());
		addRadioBtn.setSelected(true);
		addRadioBtn.setBounds(40, 85, 75, 25);
		radioBtnGroup.add(addRadioBtn);
		
		modifyRadioBtn = new JRadioButton("수정");
		modifyRadioBtn.setFont(fontNcolor.getSanserifNormal());
		modifyRadioBtn.setForeground(fontNcolor.getDarkGray());
		modifyRadioBtn.setBounds(120, 85, 75, 25);
		radioBtnGroup.add(modifyRadioBtn);
		
		opnionLabel = new JLabel("2. 요청사항을 입력해주세요.");
		opnionLabel.setFont(fontNcolor.getSanserifBig());
		opnionLabel.setForeground(fontNcolor.getDarkGray());
		opnionLabel.setBounds(20, 135, 300, 30);
		
		opnionArea = new JTextArea();
		opnionArea.setFont(fontNcolor.getSanserifsmall());
		opnionArea.setLineWrap(true);
		scrollPane = new JScrollPane(opnionArea);
		scrollPane.setBounds(20, 175, 400, 100);
		
		noticeLabel = new JLabel("* 요청사항이 무조건 반영되는 것은 아닌 점 양해 부탁드립니다.");
		noticeLabel.setFont(fontNcolor.getSanserifnotice());
		noticeLabel.setForeground(fontNcolor.getLittlelightGray());
		noticeLabel.setHorizontalAlignment(JLabel.CENTER);
		noticeLabel.setBounds(20, 290, 400, 30);
		
		submitBtn = new JButton("submit");
		submitBtn.setFont(fontNcolor.getSanserifNormal());
		submitBtn.setBackground(fontNcolor.getNavy());
		submitBtn.setForeground(Color.WHITE);
		submitBtn.setBounds(150, 330, 150, 30);
		
		requestListener = new MemberRequestListener(this, gamePanel);
		submitBtn.addActionListener(requestListener);
		
		c.add(selectLabel);
		c.add(addRadioBtn);
		c.add(modifyRadioBtn);
		c.add(opnionLabel);
		c.add(scrollPane);
		c.add(noticeLabel);
		c.add(submitBtn);
		
		setLocation(725, 320);
		setSize(450, 450);
		setResizable(false);
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JRadioButton getAddRadioBtn() {
		return addRadioBtn;
	}

	public JRadioButton getModifyRadioBtn() {
		return modifyRadioBtn;
	}

	public JTextArea getOpnionArea() {
		return opnionArea;
	}

	public JButton getSubmitBtn() {
		return submitBtn;
	}

	public JLabel getRequestLabel() {
		return requestLabel;
	}

	public void setRequestLabel(JLabel requestLabel) {
		this.requestLabel = requestLabel;
	}

	public JLabel getOpnionLabel() {
		return opnionLabel;
	}
}
