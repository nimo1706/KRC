package presentation.servlet.krctter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.service.KrcRegisterUsersService;
import dto.User;

/**
 * {@link KrcUserRegisterServlet}
 */
@WebServlet("/register_form")
public class KrcUserRegisterServlet extends HttpServlet {

	private KrcRegisterUsersService service = new KrcRegisterUsersService();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO ユーザー情報登録用画面にフォワードする
		String view = "/WEB-INF/view/krctter/registerForm.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 日本語パラメータの対応
		request.setCharacterEncoding("UTF-8");

		// TODO 画面から入力されたユーザー名とパスワードを取得する
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// TODO サービスクラスのユーザー情報登録処理を呼び出す
		User user = service.resister(username, password);

		// TODO ユーザー登録処理の戻り値がnullでなければ
		if (user != null) {
			// セッションの新規生成する
			HttpSession session = request.getSession();

			// TODO セッションにユーザー情報を登録する
			session.setAttribute("user", user);

			// タイムライン画面にリダイレクトする
			response.sendRedirect(request.getContextPath() + "/main");

		} else {

			// TODO リクエストスコープにエラーメッセージを表示する
			request.setAttribute("loginFailure", "名前が被っているよ!!");

			// TODO 新規会員登録画面にフォワードする
			String view = "/WEB-INF/view/krctter/registerForm.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			
		}

	}

}
