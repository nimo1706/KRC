package business.service;

import business.dao.UserDao;
import dto.DB_USER;
import dto.User;

public class KrcLoginService {

	private UserDao dao = new UserDao();

	// ログイン処理
	public User login(String name, String password) {

		User user = null;

		// TODO Daoのユーザー情報確認処理呼び出し

		DB_USER db_user = dao.checkLogin(name, password);

		// TODO ユーザー情報確認処理の戻り値がnullと等しくなければ
		if (db_user != null) {
			// TODO DB_USERからUSERに入れ替える
			user = new User(db_user.getId(), db_user.getName());
		}

		return user;
	}

}