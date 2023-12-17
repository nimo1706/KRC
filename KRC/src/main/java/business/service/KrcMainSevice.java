package business.service;

import java.util.List;

import business.dao.TweetDao;
import dto.DB_TWEET;
import dto.Tweet;
import dto.User;

public class KrcMainSevice {

	private TweetDao dao = new TweetDao();

	// タイムライン情報取得処理(全てのツイート情報を取得する処理)
	public List<Tweet> showTimelinePage() {

		// TODO Daoの全てのツイート情報を取得する処理を呼び出す
		return dao.findAllTweet();
	}

	// ツイート登録処理
	public int postTweet(User user, String text) {

		// TODO DB登録用のDTO作成
		DB_TWEET db_tweet = new DB_TWEET(0 ,text,user.getId());

		// TODO Daoツイートのレコード登録処理呼び出す
		return dao.insert(db_tweet);
	}

}
