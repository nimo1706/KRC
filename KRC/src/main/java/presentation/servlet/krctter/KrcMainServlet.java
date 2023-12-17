package presentation.servlet.krctter;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.service.KrcMainSevice;
import dto.Tweet;
import dto.User;

/**
 * {@link KrcMainServlet}
 */
@WebServlet("/main")
public class KrcMainServlet extends HttpServlet {

	private KrcMainSevice service = new KrcMainSevice();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		// TODO セッション情報がnullもしくはセッションからユーザー情報が取得できなければ
		if (session == null || session.getAttribute("user") == null) {

			// ログイン画面にリダイレクトする
			response.sendRedirect(request.getContextPath() + "/login");

		} else {
			// TODO サービスクラスのタイムライン情報取得処理を呼び出す。
			List<Tweet> list = service.showTimelinePage();

			// TODO リクエストスコープにタイムライン情報を設定
			request.setAttribute("tweetList", list);

			// TODO タイムライン表示画面にフォワードする。
			String view = "/WEB-INF/view/krctter/main.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
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
		// コンテキストパスを取得する
		String view = request.getContextPath();

		// TODO セッション情報がnullもしくはセッションからユーザー情報が取得できなければ
		if (session == null || session.getAttribute("user") == null) {

			// コンテキストパスにログイン画面のwebServletアノテーションを結合する
			view += "/login";

		} else {

			// TODO セッションからユーザー情報を取得
			User user = (User) session.getAttribute("user");
			// TODO 画面から入力されたtweet情報を取得
			String tweet = request.getParameter("tweet");

			// TODO サービスクラスのツイート登録処理を呼び出す
			service.postTweet(user, tweet);

			// コンテキストパスにタイムライン表示画面のwebServletアノテーションを結合する
			view += "/main";

		}

		// 設定した画面にリダイレクトする
		response.sendRedirect(view);

	}
}