<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		var userid = '${sessionScope.userid}';
		if(userid==''){
			$('#loginInfo').hide();
			$('#logout').hide();
		}else{
			$('#login').hide();
			$('#join').hide();
		}
		if(userid==''){
			$('.loginCheck').on('click', function() {
				alert('로그인 후 이용 가능합니다.');
				$('.loginCheck').prop('href',"${path}/login");
			});
		}
	});
	if('${msg}'!=''){		
		alert('${msg}');
	}
</script>
</head>
<body>
	<div class="jumbotron text-center" style="margin-bottom:0">
		<div style="text-align: right;">
			<span style="margin-right: 20px" id="loginInfo">
				<a href="${path}/member/detail?userid=${sessionScope.userid}">${sessionScope.nickname}</a> 님 반갑습니다.
			</span>
			<span style="margin-right: 20px" id="login"><a href="${path}/login">로그인</a></span>
			<span style="margin-right: 20px" id="logout"><a href="${path}/logout">로그아웃</a></span>
			<span style="margin-right: 20px" id="join"><a href="${path}/member/join">회원가입</a></span>
		</div>
		<h1>도란도란</h1>
	</div>	
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	
	<a class="navbar-brand" href="${path}/board/" style="margin-left: 20%;">메인</a>

	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="nav-link loginCheck" href="${path}/board/add">글쓰기</a>
		</li>
		<li class="nav-item">
			<a class="nav-link loginCheck" href="${path}/review/buyList">구매내역</a>
		</li>
		<li class="nav-item">
			<a class="nav-link loginCheck" href="${path}/review/sellList">판매내역</a>
		</li>
  	</ul>
	</nav>
</body>
</html>