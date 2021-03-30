import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainCustomer {
	public static void main(String[] args) {
		addCustomer("p01", "010-1234-5678", "부산시 어쩌구 저쩌구동");
	}

	private static void addCustomer(String customer_code, String phone, String adderess) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO customer (customer_code, phone, address)"
				+ " VALUE (?, ?, ?)";
		
		try {
			conn = MyConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customer_code);
			pstmt.setString(2, phone);
			pstmt.setString(3, adderess);
			
			int result = pstmt.executeUpdate();
			System.out.println("행 추가: " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnectionProvider.closeStmt(pstmt);
			MyConnectionProvider.closeConnection(conn);
		}
	}
	
}
