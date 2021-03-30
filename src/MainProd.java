import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MainProd {
	public static void main(String[] args) {
		addProd("cola", "콜라", "(주)코카콜라", 1500, "음료");
	}
	
	public static void addProd(String prod_code, String prod_name, String brand, int price, String category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO product (prod_code, prod_name, brand, price, category)" 
				+ " VALUE (?, ?, ?, ?, ?)";
		try {
			conn = MyConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, prod_code);
			pstmt.setString(2, prod_name);
			pstmt.setString(3, brand);
			pstmt.setInt(4, price);
			pstmt.setString(5, category);
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

