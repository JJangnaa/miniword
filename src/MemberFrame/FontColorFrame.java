package MemberFrame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

// 다시 생각해보는 것도 좋음
public class FontColorFrame extends JFrame{
	
	private Font sanserifBig = new Font("SanSerif", Font.BOLD, 18);
	private Font sanserifNormal = new Font("SanSerif", Font.BOLD, 16);
	private Font sanserifsmall = new Font("SanSerif", Font.PLAIN, 14);
	
	private Font sanserifnotice = new Font("SanSerif", Font.ITALIC, 13);
	
	private Color darkGray = new Color(127, 127, 127);
	private Color littlelightGray = new Color(191, 191, 191);
	private Color lightGray = new Color(242, 242, 242);
	private Color navy = new Color(0, 32, 96);
	
	public Font getSanserifBig() {
		return sanserifBig;
	}
	public Font getSanserifNormal() {
		return sanserifNormal;
	}
	public Font getSanserifsmall() {
		return sanserifsmall;
	}
	public Font getSanserifnotice() {
		return sanserifnotice;
	}
	public Color getDarkGray() {
		return darkGray;
	}
	public Color getLittlelightGray() {
		return littlelightGray;
	}
	public Color getLightGray() {
		return lightGray;
	}
	public Color getNavy() {
		return navy;
	}
	
	
	
}
