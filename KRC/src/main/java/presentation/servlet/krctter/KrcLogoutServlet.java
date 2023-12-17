package presentation.servlet.krctter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@link KrcLogoutServlet}
 */
@WebServlet("/logout")
public class KrcLogoutServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションを取得する
		HttpSession session = request.getSession(false);
		// セッションを破棄する
		session.invalidate();

		// ログイン画面にリダイレクトする
		response.sendRedirect(request.getContextPath() + "/login");
	}
}
