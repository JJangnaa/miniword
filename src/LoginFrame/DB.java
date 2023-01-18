package LoginFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

// memberDB
public class DB {
	
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet srs;
	String sql;
	String res = null;
	int result;
	
	public DB() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniworddb", "root", "test123");
//			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			srs = stmt.executeQuery("select * from member");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// DB의 중복값 유무 확인
	public String memberSurf(String col, String value) throws SQLException {	// 메소드 호출 시 검색하고 싶은 column 과 중복여부를 확인하고 싶은 값을 매개 변수로 받는다.
		// [X]이 경우, 중복 값 없을 경우 반환 값이 없어 null 이 되는데, 이 null 은 아예 값이 없는 경우로 값 비교가 불가. 아래와 같이 해야함
//		srs = stmt.executeQuery("select " + col + " from member where " + col + " = '" + value + "'");	
		// [O] value가 DB에 없다면 null == 중복값 없다는 뜻 , 있다면 문자열 담김 == 중복값 있다는 뜻!
		srs = stmt.executeQuery("select max(" + col + ") " + col + " from member where " + col + " = '" + value + "'");
		while(srs.next()) {
			res = srs.getString(col);
		}
		return res;
		
	}
	
	
	// first 
	public void firstValue(JTextField [] firTxt, JPasswordField [] firPw) {
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
		try {
			stmt.executeUpdate("update member set " + col + " = '" + value + "' where " + col2 + " = '"+ value2 +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// test용 main 메소드
//	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		DB db = new DB();
//		String a = db.memberSurf("pw", "20221130Wed");
//		System.out.println(a);
//	}
}
