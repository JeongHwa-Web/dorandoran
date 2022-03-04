<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 리뷰작성</title>
<script type="text/javascript">
	function reviewCheck() {
		var content = frmReviewAdd.content;
		var grade = frmReviewAdd.grade;
		if(content.value==''){
			alert('리뷰내용을 입력하세요.');
			content.focus();
		}else if(grade.value==''){
			alert('리뷰별점을 선택하세요.');
			grade.focus();
		}else{
			frmReviewAdd.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div class="container wrapper">
		<form action="${path}/review/reviewAdd" name="frmReviewAdd" method="post">
			<table>
				<tr>
					<td>
						<input type="hidden" name="boardnum" value="${boardnum}">
					</td>
				</tr>
				<tr>
					<td>
						<strong>리뷰내용</strong> <br>
						<textarea rows="3" id="content" cols="50" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<strong>리뷰별점</strong> <br>
						<select name="grade" id="grade" class="form-control">
							<option value="">(없음)</option>
							<option value="1">★</option>
							<option value="2">★★</option>
							<option value="3">★★★</option>
							<option value="4">★★★★</option>
							<option value="5">★★★★★</option>
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