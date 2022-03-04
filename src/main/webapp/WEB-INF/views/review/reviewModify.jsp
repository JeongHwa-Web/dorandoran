<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 리뷰수정</title>
<script type="text/javascript">
	function reviewCheck() {
		var content = frmReviewModify.content;
		var grade = frmReviewModify.grade;
		if(content.value==''){
			alert('리뷰내용을 입력하세요.');
			content.focus();
		}else if(grade.value==''){
			alert('리뷰별점을 선택하세요.');
			grade.focus();
		}else{
			frmReviewModify.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div class="container wrapper">
		<form action="${path}/review/reviewModify" name="frmReviewModify" method="post">
			<table>
				<tr>
					<td>
						<input type="hidden" name="boardnum" value="${review.boardnum}">
					</td>
				</tr>
				<tr>
					<td align="center">
						<strong>리뷰내용</strong> <br>
						<textarea rows="3" id="content" class="form-control" cols="50" name="content">${review.content}</textarea>
					</td>
				</tr>
				<tr>
					<td align="center">
						<strong>리뷰별점</strong> <br>
						<select name="grade" id="grade" class="form-control">
							<option value="1" <c:out value="${review.grade==1 ? 'selected':''}"/>>★</option>
							<option value="2" <c:out value="${review.grade==2 ? 'selected':''}"/>>★★</option>
							<option value="3" <c:out value="${review.grade==3 ? 'selected':''}"/>>★★★</option>
							<option value="4" <c:out value="${review.grade==4 ? 'selected':''}"/>>★★★★</option>
							<option value="5" <c:out value="${review.grade==5 ? 'selected':''}"/>>★★★★★</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"> <button type="button" class="btn btn-primary" onclick="reviewCheck()" style="margin-top: 20px;">리뷰등록</button> </td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>