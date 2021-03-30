import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestBatch {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = MyConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO accounts (balance)"
					+ " VALUE (?)");
			pstmt.setInt(1,  10000);
			pstmt.addBatch();
			pstmt.setInt(1,  50000);
			pstmt.addBatch();
			pstmt.setInt(1,  30000);
			pstmt.addBatch();
			
			pstmt.executeBatch(); 
			// 1만, 3만, 5만 대기중이던 3개의 query들을 batch한다
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnectionProvider.closeStmt(pstmt);
			MyConnectionProvider.closeConnection(conn);
		}
	}
}
