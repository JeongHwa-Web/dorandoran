<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 상세리뷰</title>
<script type="text/javascript">
	$(function() {		
		if('${review.truserid}'!='${sessionScope.userid}'){
			$('#reviewModify').hide();
		}
		var grade="";
		for(i=0;i<${review.grade};i++){
			grade += '<i class="fa-solid fa-star"></i>';
		}
		$('#grade').html(grade);
	});
</script>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div class="container wrapper">
		<table>
			<tr>
				<td>
					<input type="hidden" name="boardnum" value="${review.boardnum}">
				</td>
			</tr>
			<tr>
				<td align="center">
					<strong>리뷰내용</strong> <br>
					<textarea id="content" class="form-control" rows="3" cols="50" name="content" readonly="readonly">${review.content}</textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<strong>리뷰별점</strong> <br>
					<div id="grade"></div>
				</td>
			</tr>
			<tr>
				<td align="center"> 
					<button type="button" class="btn btn-info" id="reviewModify" 
					onclick="location.href='${path}/review/reviewModify?boardnum=${review.boardnum}'" style="margin-top: 20px;">리뷰수정</button> 
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>