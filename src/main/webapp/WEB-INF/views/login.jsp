<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 로그인</title>
<script type="text/javascript">
	$(function() {
		//로그인버튼 실행
		$('#btnLogin').on('click', function() {
			var userid = $('#userid').val();
			var passwd = $('#passwd').val();
			if(userid==''){
				alert('아이디 입력하세요.');
				$('#userid').focus();
			}else if(passwd==''){
				alert('비밀번호를 입력하세요.');
				$('#passwd').focus();
			}else{
				$('#frmLogin').submit();
			}
		});
		
		if('${cookie.userid}'!=''){
			$('#remember').attr('checked',true);
		}
	});
</script>
</head>
<body>
	<%@ include file="./include/header.jsp" %>
	<div class="container wrapper">
		<div id="subTitle"><h2>로그인</h2></div>
		<form class="form-inline" action="" id="frmLogin" method="post">
			<table>
				<tr>
					<th>아이디</th>
				</tr>
				<tr>
					<td>
						<input type="text" class="form-control form-control-lg" id="userid" value="${cookie.userid.value}" placeholder="아이디" name="userid">
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
				</tr>
				<tr>
					<td>
						<input type="password" class="form-control form-control-lg" id="passwd" placeholder="비밀번호" name="passwd">
					</td>
				</tr>
				<tr>
					<th>
						<input type="checkbox" class="form-check-input" name="remember" id="remember"> 아이디 저장
					</th>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn btn-lg" value="네이버 간편로그인" onclick="location.href='${apiURL}'" style="width: 260px; margin-top: 10px; background-color: #03C74A; color: #FFFFFF;">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn btn-primary btn-lg" id="btnLogin" value="로그인" style="width: 260px; margin-top: 10px">
					</td>
				</tr>
			</table>
		</form>	
	</div>
	<%@ include file="./include/footer.jsp" %>
</body>
</html>