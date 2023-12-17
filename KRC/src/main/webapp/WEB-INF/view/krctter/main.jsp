<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ page import="dto.*"%>
<%@ page import="java.util.List"%>

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
<link rel="stylesheet" href="<c:url value="/assets/krctter.css" />">
</head>

<body>

	<%@ include file="../_headerNavbar.jsp"%>
	<%
	User user = (User) session.getAttribute("user");
	List<Tweet> tweetList = (List<Tweet>) request.getAttribute("tweetList");
	%>

	<main>
		<div class="container workspace">

			<h1>KRCtterにようこそ</h1>

			<div class="main-wrapper">
				<div class="user-info-section">
					<div class="user-info-container">
						<img src="./upload/nigaoe_alfred_adler.png">
						<div class="user-info-username"><%=user.getName()%>さん
						</div>
					</div>
					<form action="logout" method="get">
						<input type="submit" name="logout" value="ログアウト">
					</form>
				</div>

				<div class="tweet-section">
					<form action="main" method="post">
						<div class="tweet-form">
							<input type="text" name="tweet" placeholder="今どうしてる？" maxlength="145" required>
							<input type="submit" value="ツイート">
						</div>
					</form>

					<div class="tweet-wrapper">
						<%
						if (null != tweetList) {
							for (Tweet tweet : tweetList) {
						%>

						<div class="tweet-item-container">
							<img src="./upload/nigaoe_alfred_adler.png">
							<div class="tweet">
								<div class="tweet-username"><%=tweet.getUserName()%></div>
								<div class="tweet-text"><%=tweet.getText()%></div>
							</div>
						</div>
						<%
							}
						}
						%>
					</div>
				</div>
			</div>
		</div>
	</main>

	<footer class="footer mt-auto fixed-bottom py-3 bg-secondary"></footer>

	<script src="<c:url value="/assets/bootstrap.bundle.min.js" />"></script>
	<script src="<c:url value="/assets/script.js" />"></script>
</body>

</html>
