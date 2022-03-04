<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 정보수정</title>
<script type="text/javascript">
	$(function() {
		var nickCheck = 'n';
		$('#btnModify').on('click', function() {
			var userid = $('#userid').val();
			var passwd = $('#passwd').val();
			var passwdCheck = $('#passwdCheck').val();
			var nickname = $('#nickname').val();
			var name = $('#name').val();
			var email = $('#email').val();
			var callnum = $('#callnum').val();
			var newPasswd = $('#newPasswd').val();
			if('${sessionScope.nickname}'==nickname){
				nickCheck = 'y';
			}
			if(userid == ''){
				alert('아이디를 입력하세요.');
				$('#userid').focus();
			}else if(nickname == ''){
				alert('닉네임을 입력하세요.');
				$('#nickname').focus();
			}else if(name == ''){
				alert('이름을 입력하세요.');
				$('#name').focus();
			}else if(email == ''){
				alert('이메일을 입력하세요.');
				$('#email').focus();
			}else if(callnum == ''){
				alert('전화번호를 입력하세요.');
				$('#callnum').focus();
			}else if(nickCheck == 'n'){
				alert('닉네임 중복 확인하세요.');
				$('#nickname').focus();
			}else if(passwd == ''){
				alert('비밀번호를 입력하세요.');
				$('#passwd').focus();
			}else if(newPasswd != ''){				
				if(newPasswd != passwdCheck){
					alert('비밀번호를 확인하세요.');
					$('#passwdCheck').focus();
				}else{	
					$('#frmModify').submit();
				}
			}else{
				$('#frmModify').submit();
			}
		});
		$('#btnRemove').on('click', function() {
			if(confirm('삭제하시겠습니까?')){				
				var passwd = $('#passwd').val();
				if(passwd==''){
					alert('비밀번호를 입력하세요.');
					$('#passwd').focus();
					return;
				}
				location.href="${path}/member/remove?userid=${member.userid}";
			}
		});
			
		$('#nickCheck').on('click', function() {
			var nickname = $('#nickname').val();
			if(nickname == ''){
				alert('닉네임을 입력하세요.');
				$('#nickname').focus();
				return;
			}
			$.ajax({
				url:'${path}/member/nickCheck',
				dataType: 'text',
				type:'post',
				data: {nickname},
				success: function(cnt) {
					if(cnt != 1){
						$('.nick_ok').css('display','inline-block');
						$('.nick_already').css('display','none');
						nickCheck = 'y';
					}else{
						$('.nick_already').css('display','inline-block');
						$('.nick_ok').css('display','none');
						nickCheck = 'n';
					}
				},
				error: function(e) {
					alert("실패");
				}
			});
		});
	});
</script>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div class="container wrapper">
		<div id="subTitle"><h2>내정보 수정</h2></div>
		<form class="form-inline" action="${path}/member/modify" id="frmModify" enctype="multipart/form-data" method="post">
			<table style="margin-left: 425;">
				<tr>
					<th>아이디</th>
				</tr>
				<tr>	
					<td>
						<input type="text" class="form-control form-control-lg" id="userid" name="userid" value="${member.userid}" readonly="readonly">
					</td>			
				</tr>	
				<tr>
					<th>현재 비밀번호</th>
				</tr>
				<tr>
					<td>
						<input type="password" class="form-control form-control-lg" id="passwd" placeholder="현재 비밀번호" name="passwd">
					</td>
				</tr>		
				<tr>
					<th>변경 비밀번호</th>
				</tr>	
				<tr>
					<td>
						<input type="password" class="form-control form-control-lg" id="newPasswd" placeholder="변경 비밀번호" name="newPasswd">
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
				</tr>	
				<tr>
					<td>
						<input type="password" class="form-control form-control-lg" id="passwdCheck" placeholder="비밀번호 확인" name="passwdCheck">
					</td>
				</tr>
				<tr>
					<th>닉네임</th>
				</tr>	
				<tr>
					<td>
						<input type="text" class="form-control form-control-lg" id="nickname" placeholder="닉네임" name="nickname"  value="${member.nickname}">
						<button id="nickCheck" type="button" class="btn btn-secondary btn-sm">중복체크</button>
						<span class="nick_ok">사용 가능한 닉네임입니다.</span>
						<span class="nick_already">중복된 닉네임입니다.</span>
					</td>
				</tr>
				<tr>
					<th>이름</th>
				</tr>
				<tr>
					<td>
						<input type="text" class="form-control form-control-lg" id="name" placeholder="이름" name="name" value="${member.name}">
					</td>
				</tr>
				<tr>
					<th>이메일</th>
				</tr>
				<tr>
					<td>
						<input type="email" class="form-control form-control-lg" id="email" placeholder="이메일" name="email" value="${member.email}">
					</td>
				</tr>
				<tr>
					<th>휴대전화</th>
				</tr>
				<tr>
					<td>
						<input type="tel" class="form-control form-control-lg" id="callnum" placeholder="휴대전화" name="callnum" value="${member.callnum}">
					</td>
				</tr>
				<tr>
					<th>프로필사진</th>
				</tr>
				<tr>
					<td>
						<div>
							<img alt="" src="${path}/savedir/${member.filename}" style="width: 200px;">
						</div>
					</td>
				</tr>	
				<tr>
					<td>
						<div class="custom-file">
							<input type="file" class="custom-file-input" id="file" placeholder="프로필사진" name="file">
							<label class="custom-file-label" for="customFile" style="width: 260px;">Choose file</label>
						</div>
					</td>
				</tr>	
				<tr>
					<td>
						<input type="button" class="btn btn-primary btn-lg" id="btnModify" value="수정" style="width: 128px">
						<input type="button" class="btn btn-primary btn-lg" id="btnRemove" value="삭제" style="width: 128px">
					</td>
				</tr>
			</table>
		</form>	
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>