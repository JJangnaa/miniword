package LoginFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DB {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet srs;
	private String sql;
	private String res = null;
	private int result;

// --------------------------------------------------------------
	// DB 연결 메소드
	private void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniworddb", "root", "test123");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
// --------------------------------------------------------------
/*memberDB section*/
	// DB의 중복값 유무 확인
	public String memberSurf(String col, String value) throws SQLException {	// 메소드 호출 시 검색하고 싶은 column 과 중복여부를 확인하고 싶은 값을 매개 변수로 받는다.
		// [X]이 경우, 중복 값 없을 경우 반환 값이 없어 null 이 되는데, 이 null 은 아예 값이 없는 경우로 값 비교가 불가. 아래와 같이 해야함
//		srs = stmt.executeQuery("select " + col + " from member where " + col + " = '" + value + "'");	
		// [O] value가 DB에 없다면 null == 중복값 없다는 뜻 , 있다면 문자열 담김 == 중복값 있다는 뜻!
		connect();
		srs = stmt.executeQuery("select max(" + col + ") " + col + " from member where " + col + " = '" + value + "'");
		while(srs.next()) {
			res = srs.getString(col);
		}
		return res;
		
	}
	// ID 찾기
	public String infoSurfID(String col1, String col2, String value) throws SQLException {
		connect();
		srs = stmt.executeQuery("select " + col1 + " from member where " + col2 + " = '" + value + "'");
		while(srs.next()) {
			res = srs.getString(col1);
		}
		return res;
		
	}
	// PW 찾기
	public String infoSurfPW(String col1, String col2, String col3, String value1, String value2) throws SQLException {
		connect();
		srs = stmt.executeQuery("select " + col1 + " from member where " + col2 + " = '" + value1 + "' and " + col3 + " = '" + value2 + "'");
		while(srs.next()) {
			res = srs.getString(col1);
		}
		return res;
		
	}
	// 회원가입시 insert
	public void firstValue(JTextField [] firTxt, JPasswordField [] firPw) {
		connect();
		String pw = new String(firPw[0].getPassword());
		try {
			sql = "insert into member values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for(int i=1; i<6; i++) {
				if(i<3) {
					pstmt.setString(i, firTxt[i-1].getText());
				} else if(i==3) {
					pstmt.setString(i, pw);
				} else if(i==4){
					if(!(firTxt[i-1].getText()).equals("") && !(firTxt[i].getText()).equals("")) {
						pstmt.setString(i, "010 "+firTxt[i-1].getText()+" "+firTxt[i].getText());
					} else {
						pstmt.setString(i, "-");
					}
					
				} else {
					pstmt.setInt(i, 0);
				}
			} 
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// insert
	public void insertValue(String col, String value) {
		connect();
			try {
				stmt.executeUpdate("insert into member (" + col + ") values ('" + value + "');");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
	// edit
	public void updateValue(String col, String value, String col2, String value2) {
		connect();
		try {
			stmt.executeUpdate("update member set " + col + " = '" + value + "' where " + col2 + " = '"+ value2 +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// --------------------------------------------------------------
/*wordDB section*/
	// 전체 단어 select
	public void selectAllword(DefaultTableModel model, String col) {
		connect();
		try {
			ResultSet srs = stmt.executeQuery("select * from " + col);
			while(srs.next()) {
				String eng = srs.getString("eng");
				String kor = srs.getString("kor");
				Object data [] = {eng, kor};
				model.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// --------------------------------------------------------------
/*requestDB section*/
	public void selectAllrequest(DefaultTableModel model, String col) {
		connect();
		try {
			ResultSet srs = stmt.executeQuery("select * from " + col);
			while(srs.next()) {
				String id = srs.getString("id");
				String name = srs.getString("name");
				String addOrdelete = srs.getString("add/delete");
				String content = srs.getString("content");
				Object data [] = {id, name, addOrdelete, content};
				model.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// test용 main 메소드
//	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		DB db = new DB();
//		
//	}
}
