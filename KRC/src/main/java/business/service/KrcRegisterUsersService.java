package business.service;

import business.dao.UserDao;
import dto.DB_USER;
import dto.User;

public class KrcRegisterUsersService {

	private UserDao dao = new UserDao();

	// ユーザー名の登録件数取得処理
	private int checkUserName(String name) {

		// TODO Daoのユーザー名の登録件数取得処理呼び出し
		return dao.CheckUserName(name);
	}

	// ユーザー情報登処理
	public User resister(String name, String password) {


		User user = null;

		// TODO ユーザー名の登録件数取得が0と等しければ
		if (checkUserName(name) == 0) {

			// TODO DB登録用のDTO作成
			DB_USER db_user = new DB_USER(0, name, password);

			// TODO Daoのユーザー情報登録処理を呼び出す
			db_user = dao.insert(db_user);

			// TODO Daoのユーザー情報登録処理の戻り値からUserDTOに詰め替えを行う
			user = new User(db_user.getId(),db_user.getName());
		}

		return user;
	}

}
