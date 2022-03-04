<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 게시글 수정</title>
<script type="text/javascript">
	$(function() {
		$('#btnModify').on('click', function (e) {
			e.preventDefault();
			var title = $('#title').val();
			var content = $('#content').val();
			if(title==''){
				alert('제목을 입력하세요.');
				$('#title').focus();
			}else if(content==''){
				alert('내용을 입력하세요.');
				$('#content').focus();
			}else{
				$('#frmModify').submit();
			}
		});
		$('#btnRemove').on('click', function() {
			if(confirm('삭제하시겠습니까?')){
				location.href = "${path}/board/remove?boardnum="+${result.board.boardnum};
			}
		});
	});
</script>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div class="container wrapper">
		<div id="subTitle"><h2>글쓰기</h2></div>
		<form action="${path}/board/modify" method="post" id="frmModify" enctype="multipart/form-data">
			<table>
				<tr>
					<td> <input class="form-control form-control-lg" type="hidden" name="boardnum" id="boardnum" value="${result.board.boardnum}"> </td>
				</tr>
				<tr>
					<th>제목</th>
					<td> <input class="form-control form-control-lg" type="text" name="title" id="title" value="${result.board.title}"> </td>
				</tr>
				<tr>
					<th>내용</th>
					<td> <textarea class="form-control" rows="30" cols="100" name="content" id="content">${result.board.content}</textarea> </td>
				</tr>
				<tr>
					<th>사진</th>
					<td>
						<div class="custom-file">
							<input class="custom-file-input" multiple="multiple" type="file" name="files" id="files"> 
							<label class="custom-file-label" for="customFile">Choose file</label>
						</div>
						<c:forEach var="file" items="${result.files}">
							<img alt="${file.filename}" src="${path}/savedir/${file.filename}" style="width: 500px;">
							삭제 <input type="checkbox" name="removeFile" value="${file.photonum}"> <br>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button class="btn btn-primary" type="button" id="btnModify" style="margin-top: 20px;">등록하기</button>
						<button type="button" class="btn btn-info" id="btnRemove" style="margin-top: 20px;">삭제</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>