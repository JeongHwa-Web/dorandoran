<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<meta charset="UTF-8">
<title>도란도란</title>
<script type="text/javascript">
	$(function() {
		$('.alist').on('click', function(e) {
			e.preventDefault();
			var curpage = $(this).attr('href');
			var findkey = $('#findkey').val();
			var findvalue = $('#findvalue').val();
			location.href = '${path}/board/?curpage='+curpage+'&findkey='+findkey+'&findvalue='+findvalue;
		});
	});
</script>
</head>
<body>
	<%@ include file="./include/header.jsp" %>
	<div class="container wrapper">      
		<form action="${path}/board/">
			<div class="input-group mb-3" style="margin-top: 100px;">
				<select name="findkey" id="findkey" class="custom-select">
					<option value="ticon" <c:out value="${page.findkey=='ticon'?'selected':''}"/>>제목+내용</option>
					<option value="nickname" <c:out value="${page.findkey=='nickname'?'selected':''}"/>>닉네임</option>
					<option value="title" <c:out value="${page.findkey=='title'?'selected':''}"/>>제목</option>
					<option value="content" <c:out value="${page.findkey=='content'?'selected':''}"/>>내용</option>
				</select>
				<input type="text" name="findvalue" class="form-control" id="findvalue" value="${page.findvalue}" style="width: 500">
				<button class="btn btn-success">조회</button>
			</div>
		</form>
		<table class="table table-hover" style="margin-top: 10px">
			<thead>
				<tr>
				    <th>No.</th>
				    <th>사진</th>
				    <th>제목</th>
				    <th>닉네임</th>
				    <th>등록날짜</th>
				    <th>판매여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${blist}">
					<tr>
						<td>${board.boardnum}</td>
						<td>
							<c:if test="${board.filename==null}"></c:if>
							<c:if test="${board.filename!=null}">
								<img alt="${board.filename}" src="${path}/savedir/${board.filename}" style="width: 200px; height: 100px;">
							</c:if>
						</td>
						<td><a href="${path}/board/detail?boardnum=${board.boardnum}">${board.title}</a></td>
						<td>${board.nickname}</td>
						<td> <fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/> </td>
						<td>
							<c:if test="${board.truserid!=null}">판매완료</c:if>
							<c:if test="${board.truserid==null}">판매중</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pagination" style="justify-content: center;">
			<c:if test="${page.startpage != 1}">
				<a href="${page.startpage-1}" class="alist page-link">이전</a>
			</c:if>
			<c:forEach var="i" begin="${page.startpage}" end="${page.endpage}">
				<c:if test="${i==page.curpage}">
					<li class="page-item active"> <a href="${i}" class="alist page-link">${i}</a> </li>
				</c:if>
				<c:if test="${i!=page.curpage}">
					<li class="page-item"> <a href="${i}" class="alist page-link">${i}</a> </li>
				</c:if>
			</c:forEach>
			<c:if test="${page.endpage != page.totpage}">
				<a href="${page.endpage+1}" class="alist page-link">다음</a>
			</c:if>
		</ul>
	</div>
	<%@ include file="./include/footer.jsp" %>
</body>
</html>