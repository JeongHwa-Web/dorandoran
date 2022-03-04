<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 구매내역</title>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div class="container wrapper">
		<div id="subTitle"><h2>구매내역</h2></div>
		<table class="table table-hover">
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>판매자 닉네임</th>
				<th></th>
			</tr>
			<c:forEach var="board" items="${buyList}">
				<tr>
					<td>${board.boardnum}</td>
					<td><a href="${path}/board/detail?boardnum=${board.boardnum}">${board.title}</a></td>
					<td>${board.nickname}</td>
					<td> 
						<c:if test="${board.review == 0}">
							<button type="button" class="btn btn-primary btn-sm" 
							onclick="location.href='${path}/review/reviewAdd?boardnum=${board.boardnum}'">리뷰작성</button> 
						</c:if>
						<c:if test="${board.review == 1}">
							<button type="button" class="btn btn-success btn-sm" 
							onclick="location.href='${path}/review/reviewDetail?boardnum=${board.boardnum}'">보낸리뷰 보기</button> 
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>