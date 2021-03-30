import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;


public class MainOrder {
	public static void main(String[] args) {
		addProd("order1", 2021-01-01, "p01", "cola", 4);
	}
	
	public static void addProd(String order_num, Date date, String orderer, String prod_code, int amount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO order_t (order_num, date, orderer, prod_code, amount)" 
				+ " VALUE (?, ?, ?, ?, ?)";
		
		try {
			conn = MyConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, order_num);
			pstmt.setDate(2, date);
			pstmt.setString(3, orderer);
			pstmt.setString(4, prod_code);
			pstmt.setInt(5, amount);
			
			int result = pstmt.executeUpdate();
			System.out.println("행 추가: " +  result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnectionProvider.closeStmt(pstmt);
			MyConnectionProvider.closeConnection(conn);
		}
	}
}






//public class Main {
//	public static void main(String[] args) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = MyConnectionProvider.getConnection();
//			pstmt = conn.prepareStatement("INSERT INTO product (prod_code, prod_name, brand, price, category)" 
//					+ " VALUE (?, ?, ?, ?, ?)");
//			pstmt.setString(1, "cola");
//			pstmt.setString(2, "콜라");
//			pstmt.setString(3, "(주)코카콜라");
//			pstmt.setInt(4, 1500);
//			pstmt.setString(5, "음료");
//			pstmt.addBatch();
//
//			
//			pstmt.executeBatch(); 
//			int result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			MyConnectionProvider.closeStmt(pstmt);
//			MyConnectionProvider.closeConnection(conn);
//		}
//	}
//}

