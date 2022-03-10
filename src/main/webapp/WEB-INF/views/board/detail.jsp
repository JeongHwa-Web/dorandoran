<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도란도란 상세조회</title>
<script type="text/javascript">
	var userid = '${sessionScope.userid}';
	var wruserid = '${result.board.wruserid}';
	$(function() {
		if(userid != wruserid){
			$('#btnModify').hide();
		}
		$('#btnAddReply').on('click', function () {
			var boardnum = ${result.board.boardnum}
			var content = $('#replyContent').val();
			if(userid==''){
				alert('로그인 후 작성 가능합니다.');
				return;
			}
			if(content == ''){
				alert('댓글 내용을 입력해주세요.');
				$('#replyContent').focus();
				return;
			}
			$.ajax({
				url : '${path}/reply/',
				type : 'post',
				data : JSON.stringify({boardnum,content}),
				contentType : 'application/json',
				dataType : 'text',
				success : function (data) {
					$('#replyContent').val('');
					replyList();
				},
				error : function (e) {
					console.log(e);
					alert('실패');
				}
			});
		});
	});
	
	function btnTrade(truserid) {
		if(userid != wruserid){
			alert('권한이 없습니다.');
			return;
		}
		if(confirm('거래완료 하시겠습니까?')){
			var boardnum = ${result.board.boardnum};
			location.href='${path}/board/tradeSuccess?truserid='+truserid+'&boardnum='+boardnum;
		}
	}
	
	function btnReplyRemove(replynum, ruserid) {
		if(userid != ruserid){
			alert('권한이 없습니다.');
			return;
		}
		if(!confirm('삭제하시겠습니까?')){
			return;
		}
		$.ajax({
			url : '${path}/reply/'+replynum,
			type : 'delete',
			dataType : 'text',
			success : function(data) {				
				replyList();
			},
			error : function (e) {
				console.log(e);
				alert('실패');
			}
		});
	}
	
	function btnReplyModify(replynum) {
		var content = $('#replyEditContent').val();
		$.ajax({
			url: '${path}/reply/',
			type : 'put',
			data : JSON.stringify({replynum, content}),
			contentType : 'application/json',
			dataType : 'text',
			success: function(data) {
				replyList();
			},
			error : function (e) {
				console.log(e);
				alert('실패');
			}
		});
	}
	
	function btnModifyView(replynum, nickname, regdate, content, ruserid) {
		if(userid != ruserid){
			alert('권한이 없습니다.');
			return;
		}
		var replyModify = '';
		replyModify += '<div class="card mb-2" id="divReply'+replynum+'">';
		replyModify +=     '<div class="card-header bg-light">';
		replyModify +=		  '<strong>닉네임: </strong>'+nickname+'&nbsp;&nbsp;&nbsp;&nbsp;';
		replyModify +=		  '<strong>등록날짜: </strong>'+regdate;
		replyModify +=     '</div>';
		replyModify += 	   '<div class="card-body">';
		replyModify += 		   '<ul class="list-group list-group-flush">';
		replyModify += 		       '<li class="list-group-item">';
		replyModify += 				   '<textarea class="form-control" id="replyEditContent" rows="3">'+content+'</textarea>';
		replyModify += 				   '<button type="button" class="btn btn-dark mt-3" onclick="btnReplyModify('+replynum+')" value="'+replynum+'">댓글작성</button>';
		replyModify += 				   '<button type="button" class="btn btn-dark mt-3" onclick="replyList()">취소</button>';
		replyModify += 			   '</li>';
		replyModify += 		   '</ul>';
		replyModify +=     '</div>';
		replyModify += '</div>';
		$('#divReply'+replynum).replaceWith(replyModify);
		$('#divReply'+replynum+'#replyContent').focus();
	}
	
	function replyList() {
		var boardnum = ${result.board.boardnum};
		$.ajax({
			url : '${path}/reply/list/'+boardnum,
			type : 'get',
			dataType : 'json',
			success : function (data) {
				var replyList = '';	
				if(data.lenth < 1){
					replyList = "등록된 댓글이 없습니다.";
				}else{
					$(data).each(function() {
						replyList += '<hr>';
						replyList += '<div class="card mb-2" id="divReply'+this.replynum+'">';
						replyList += 	'<div class="card-header bg-light">';
						replyList +=		'<strong>닉네임: </strong>'+this.nickname+'&nbsp;&nbsp;&nbsp;&nbsp;';
						replyList +=		'<strong>등록날짜: </strong>'+this.regdate;
						replyList += 	'</div>';
						replyList += 	'<div class="card-body">';
						replyList += 		'<ul class="list-group list-group-flush">';
						replyList += 		    '<li class="list-group-item">'
						replyList += 				'<textarea class="form-control" rows="3" readonly>'+this.content+'</textarea>';
						replyList += 				'<button type="button" class="btn btn-dark mt-3" onclick="btnModifyView('+this.replynum+',\''+this.nickname+'\',\''+this.regdate+'\',\''+this.content+'\',\''+this.userid+'\')">수정</button>';
						replyList += 				'<button type="button" class="btn btn-dark mt-3" onclick="btnReplyRemove('+this.replynum+',\''+this.userid+'\')">삭제</button>';
						replyList += 				'<button type="button" class="btn btn-dark mt-3" onclick="btnTrade(\''+this.userid+'\')" style="float: right;">거래완료</button>';
						replyList += 			'</li>';
						replyList += 		'</ul>';
						replyList += 	'</div>';
						replyList += '</div>';
					});
				}
				$('#divReplyList').html(replyList);
			},
			error : function (e) {
				console.log(e);
				alert('실패')
			}
		});
	}
	replyList()
</script>
</head>
<body>
	<%@ include file="../include/header.jsp" %>
	<div class="container wrapper">
		<div class="border border-secondary rounded" style="margin-top: 100px; padding-bottom: 50px; padding-top: 50px;">
			<div class="row" style="justify-content: center;">
				<div class="col-sm-1">
					<strong>No.</strong>
					${result.board.boardnum}
				</div>
				<div class="col-sm-3">
					<strong>닉네임</strong>
					${result.board.nickname}
				</div>	
				<div class="col-sm-2">
					<strong>등록날짜</strong>
					<fmt:formatDate value="${result.board.regdate}" pattern="yyyy-MM-dd"/>
				</div>	
				<div class="col-sm-2">
					<strong>평점</strong>
					${result.gradeAvg}
				</div>	
			</div>
			<div class="row" style="justify-content: center; margin-top: 10px;">	
				<div class="col-sm-4">
					<strong>제목</strong>
					${result.board.title}
				</div>
				<div class="col-sm-2">
					<strong>판매여부</strong>
					<c:if test="${result.board.truserid!=null}">판매완료</c:if>
					<c:if test="${result.board.truserid==null}">판매중</c:if>
				</div>
				<div class="col-sm-2">
					<strong>조회수</strong>
					${result.board.readcnt}
				</div>
			</div>
		</div>
		<div>
			<textarea class="form-control" rows="30" readonly="readonly" style="margin-top: 10px; margin-bottom: 20px;">${result.board.content}</textarea>
			<c:forEach var="file" items="${result.files}">
				<img alt="${file.filename}" src="${path}/savedir/${file.filename}" style="width: 500px;">
			</c:forEach>
		</div>
		
		<div class="row" style="justify-content: center; margin-top: 20px;">
			<button type="button" class="btn btn-info" id="btnModify" onclick="location.href='${path}/board/modify?boardnum=${result.board.boardnum}'">수정</button>
		</div>
		
		<div id="reply" style="margin-top: 20px;">
			<div class="card mb-2" id="divReplyAdd">
				<div class="card-body">
					<strong>댓글입력창</strong>
					<textarea class="form-control" rows="5" name="replyContent" id="replyContent"></textarea>
					<button type="button" class="btn btn-dark mt-3" id="btnAddReply" name="btnAddReply">댓글입력</button>
				</div>
			</div>
			<div id="divReplyList">
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>