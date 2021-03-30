import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = MyConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			pstmt1 = conn.prepareStatement("UPDATE accounts"
					+ " SET balance = balance - 5000"
					+ " WHERE id = 1");
			pstmt2 = conn.prepareStatement("UPDATE accounts"
					+ " SET balance = balance + 5000"
					+ " WHERE id = 2");
			pstmt1.executeUpdate();
			pstmt2.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback(); // commit에서 예외가 생기면 rollback이 실행되면서 위에 conn.setAutoCommit(false);으로 올라가서 실행된다
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			MyConnectionProvider.closeStmt(pstmt1);
			MyConnectionProvider.closeStmt(pstmt2);
			MyConnectionProvider.closeConnection(conn);
		}
	}
}
