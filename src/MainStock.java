import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MainStock {
	public static void main(String[] args) {
		addProd("cola", 100);
	}
	
	public static void addProd(String prod_code, int stock) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO stock (prod_code, stock)" 
				+ " VALUE (?, ?)";
		try {
			conn = MyConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, prod_code);
			pstmt.setInt(2, stock);

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



