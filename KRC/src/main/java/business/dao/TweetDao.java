package business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DB_TWEET;
import dto.Tweet;

public class TweetDao extends DAOTemplate {

	// 引数で受け取ったDTO情報をツイートテーブルに登録する。
	public int insert(DB_TWEET db_tweets) {

		// TODO ①SQLを変数に格納

		String sql = "INSERT INTO TWEET VALUES (?,?,?)";
		// TODO ②DBとのconnection確立
		// TODO ③prepareStatementの生成
		try (Connection con = createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// TODO ④getMaxIdを呼び出しIDを確定指せる
			db_tweets.setId(getMaxId() + 1);

			// TODO ⑤プレースホルダに値を設定
			pstmt.setInt(1, db_tweets.getId());
			pstmt.setString(2, db_tweets.getText());
			pstmt.setInt(3, db_tweets.getUser_Id());

			// TODO ⑥SQLの実行(戻り値:レコードの更新結果)
			// (戻り値:レコードの更新結果)
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// ツイートテーブルに登録されているレコードを全て取得する。
	public List<Tweet> findAllTweet() {

		// TODO ①SQLを変数に格納
		String sql = "SELECT NAME,TEXT FROM  tweet, USER WHERE tweet.USER_ID = USER.ID ORDER BY TWEET.ID DESC";

		// TODO ②DBとのconnection確立
		try (Connection con = createConnection();

				// TODO ③prepareStatementの生成
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// TODO ⑤SQLの実行
			ResultSet rs = pstmt.executeQuery();

			// TODO ⑥SQLの実行結果をDTO(Tweet)に格納する
			// TODO ⑦ ⑥の処理を繰り返す
			List<Tweet> list = new ArrayList<>();
			while (rs.next()) {

				String name = rs.getString("NAME");
				String text = rs.getString("TEXT");

				list.add(new Tweet(name, text));

			}

			// (戻り値:DTOのリストを返す)
			return list;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// TWEETテーブルのIDの最大値を返す
	private int getMaxId() {

		String sql = "SELECT MAX(ID) AS MAX FROM TWEET";

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