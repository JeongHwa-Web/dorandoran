<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 글쓰기</title>
<script type="text/javascript">
	$(function() {
		$('#btnAdd').on('click', function() {
			var title = $('#title').val();
			var content = $('#content').val();
			if(title == ''){
				alert('제목을 입력하세요.');
				$('#title').focus();
			}else if(content == ''){
				alert('내용을 입력하세요.');
				$('#content').focus();
			}else{
				$('#frmAdd').submit();
			}
			
		});
	});
</script>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div class="container wrapper">
		<div id="subTitle"><h2>글쓰기</h2></div>
		<form action="${path}/board/add" method="post" id="frmAdd" enctype="multipart/form-data">
			<table>
				<tr>
					<th>제목</th>
					<td> <input class="form-control form-control-lg" type="text" name="title" id="title"> </td>
				</tr>
				<tr>
					<th>내용</th>
					<td> <textarea class="form-control" rows="30" cols="100" name="content" id="content"></textarea> </td>
				</tr>
				<tr>
					<th>사진</th>
					<td> 
						<div class="custom-file">
							<input class="custom-file-input" multiple="multiple" type="file" name="files" id="files"> 
							<label class="custom-file-label" for="customFile">Choose file</label>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"> <button class="btn btn-primary" type="button" id="btnAdd" style="margin-top: 20px;">등록하기</button> </td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>