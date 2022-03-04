<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 판매내역</title>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div class="container wrapper">
		<div id="subTitle"><h2>판매내역</h2></div>
		<table class="table table-hover">
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>구매자 닉네임</th>
				<th></th>
			</tr>
			<c:forEach var="board" items="${sellList}">
				<tr>
					<td>${board.boardnum}</td>
					<td><a href="${path}/board/detail?boardnum=${board.boardnum}">${board.title}</a></td>
					<td>${board.nickname}</td>
					<td> 
						<c:if test="${board.review == 0 and board.truserid != null}">
							(리뷰가 작성되기 전입니다.)
						</c:if>
						<c:if test="${board.review == 1}">
							<button type="button" class="btn btn-success btn-sm" 
							onclick="location.href='${path}/review/reviewDetail?boardnum=${board.boardnum}'">받은리뷰 보기</button> 
						</c:if>
						<c:if test="${board.truserid == null}">
							(판매중)
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>