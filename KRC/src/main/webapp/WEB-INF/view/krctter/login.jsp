<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!doctype html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>KRCtter</title>
<link rel="shortcut icon" href="<c:url value="/assets/favicon.ico" />">
<link rel="stylesheet" href="<c:url value="/assets/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/assets/bootstrap-icons.css" />">
<link rel="stylesheet" href="<c:url value="/assets/styles.css" />">
</head>

<body>

	<%@ include file="../_headerNavbar.jsp"%>

	<main>
		<div class="container workspace">

			<h1>KRCtter</h1>

			<!-- エラーメッセージが存在するときだけ表示する -->
			<%String failureMessage = (String)request.getAttribute("loginFailure"); %>
			<% if (failureMessage != null) {%>
			<%=failureMessage %>
			<%} %>

			<form action="login" method="post">
				<label for="username">ユーザー名：</label>
				<input id="username" type="text" name="username" maxlength="45" required><br>
				<label for="password">パスワード：</label>
				<input id="password" type="password" name="password" maxlength="45" required><br>
				
				<input type="submit" name="login" value="ログイン">
			</form>
			<form action="register_form" method="get">
				<input type="submit" value="新規登録">
			</form>

		</div>
	</main>

	<footer class="footer mt-auto fixed-bottom py-3 bg-secondary"></footer>

	<script src="<c:url value="/assets/bootstrap.bundle.min.js" />"></script>
	<script src="<c:url value="/assets/script.js" />"></script>
</body>

</html>