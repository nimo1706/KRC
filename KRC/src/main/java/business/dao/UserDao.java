package business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.DB_USER;

public class UserDao extends DAOTemplate {

	// 引数で受け取ったDTO情報をツイートテーブルに登録する。
	public DB_USER insert(DB_USER db_users) {

		// TODO ①SQLを変数に格納

		String insertSql = "INSERT INTO USER (ID, NAME, PASSWORD) VALUES (?, ?, ?)";

		// TODO ②DBとのconnection確立

		// TODO ③prepareStatementの生成

		try (Connection con = createConnection(); PreparedStatement pstmt = con.prepareStatement(insertSql);) {

			// TODO ④getMaxIdを呼び出しIDを確定指せる

			db_users.setId(getMaxId() + 1);

			// TODO ⑤プレースホルダに値を設定

			pstmt.setInt(1, db_users.getId());
			pstmt.setString(2, db_users.getName());
			pstmt.setString(3, db_users.getPassword());

			// TODO ⑥SQLの実行
			pstmt.executeUpdate();

			// (戻り値:登録したDTO)
			return db_users;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// TODO USERテーブルのNAMEと引数のNAMEが一致するレコードの件数を返す
	public int CheckUserName(String name) {

		String sql = "SELECT COUNT(*) AS CNT FROM USER WHERE NAME = ?;";

		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			int result = 0;
			if (rs.next()) {
				result = rs.getInt("CNT");
			}

			return result;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// TODO USERテーブルのNAMEとPASSWORDが引数のnameとpasswordとが一致するレコードの情報を返す
	public DB_USER checkLogin(String name, String password) {

		String sql = "SELECT * FROM USER WHERE NAME = ? AND PASSWORD = ?;";

		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, name);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			DB_USER result = null;
			if (rs.next()) {
				result = new DB_USER(rs.getInt("ID"), rs.getString("NAME"), rs.getString("PASSWORD"));
			}

			return result;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// TWEETテーブルのIDの最大値を返す
	private int getMaxId() {

		String sql = "SELECT MAX(ID) AS MAX FROM USER";

		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// IDのMAXを取得
			ResultSet rs = pstmt.executeQuery();

			int maxId = 0;
			if (rs.next()) {
				maxId = rs.getInt("MAX");
			}

			return maxId;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
