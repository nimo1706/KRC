package presentation.servlet.krctter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.service.KrcLoginService;
import dto.User;

/**
 * {@link KrcLoginServlet}
 */
@WebServlet("/login")
public class KrcLoginServlet extends HttpServlet {

	private KrcLoginService service = new KrcLoginService();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO ログイン画面にフォワードする
		String view = "/WEB-INF/view/krctter/login.jsp";
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

		// セッションを取得する
		HttpSession session = request.getSession(false);

		// TODO 既にセッションがnullと等しくない
		if (session != null) {
			// セッションの破棄
			session.invalidate();
		}

		// TODO 画面から入力されたユーザー名とパスワードを取得する
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// TODO サービスクラスのログイン処理を呼び出す
		User user = service.login(username, password);

		// TODO サービスクラスのログイン処理の戻り値がnullでなければ
		if (user != null) {

			// セッションを新規で作成する
			session = request.getSession(true);

			// TODO セッションにユーザー情報を設定する
			session.setAttribute("user", user);

			// タイムライン画面にリダイレクトする
			response.sendRedirect(request.getContextPath() + "/main");
		} else {

			// TODO リクエストスコープにエラーメッセージを表示する
			request .setAttribute("loginFailure", "残念！もう一度");

			// TODO ログイン画面にフォワードする
			String view = "/WEB-INF/view/krctter/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

}
