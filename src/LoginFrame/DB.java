package LoginFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// memberDB
public class DB {
	
	Connection conn;
	Statement stmt;
	ResultSet srs;
	String res = null;
	String result;
	
	public DB() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniworddb", "root", "test123");
//			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			stmt.executeQuery("select * from member");
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
	
	// 목적: 타 클래스에서 DB 클래스 객체 생성 후 아래 메소드를 호출하여 DB의 중복값이 있는지 확인 받기 위함.
	public String memberSurf(String col, String value) throws SQLException {	// 메소드 호출 시 검색하고 싶은 column 과 중복여부를 확인하고 싶은 값을 매개 변수로 받는다.
//		srs = stmt.executeQuery("select " + col + " from member where " + col + " = '" + value + "'");	// 이 경우, 중복 값 없을 경우 반환 값이 없어 null 이 되는데 이 null 은 아예 값이 없는 경우로 값 비교가 불가. 아래와 같이 해야함
		srs = stmt.executeQuery("select max(" + col + ") " + col + " from member where " + col + " = '" + value + "'");   // value가 DB에 없다면 null == 중복값 없다는 뜻 , 있다면 문자열 담김 == 중복값 있다는 뜻!
		while(srs.next()) {
			res = srs.getString(col);
//			if(res == null) {
//				result = "중복 없음";
//			} else {
//				result = "중복 없음";
//			}
		}
		return res;
		
	}
	
	// 목적 : 해당 메소드 호출 시 DB에 데이터 값 입력.
	public void insertValue(String col, String value) {
		try {
			stmt.executeUpdate("insert into member (" + col + ") values (" + value + ");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// test용 main 메소드
	public static void main(String[] args) throws SQLException {
		DB db = new DB();
		String a = db.memberSurf("name", "펭귄");
		System.out.println(a);
		
	}
}
