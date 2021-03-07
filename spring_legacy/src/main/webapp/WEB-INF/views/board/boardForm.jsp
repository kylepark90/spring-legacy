<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %> 
<%@ include file = "/WEB-INF/views/layout/header.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>board</title>

<script type="text/javascript">
$(document).on('click','#btnSave', function(e){
	e.preventDefault();
	$("#form").submit();
	})
	
$(document).on('click','#btnList', function(e){
	e.preventDefault();
	location.href = "${pageContext.request.contextPath}/board/getBoardList";
	})
// 수정작업을 위한 데이터 바인딩	
$(document).ready(function(){
	var mode = '<c:out value="${mode}" />';
	if(mode == 'edit'){
		//입력 폼 세팅
		$('#reg_id').prop('readonly',true);	//prop은 속성값을 가져오거나 추가함.
		$("input:hidden[name='bid']").val('<c:out value="${boardContent.bid}" />');
		$("input:hidden[name='mode']").val('<c:out value="${mode}" />');
		$("#reg_id").val('<c:out value="${boardContent.reg_id}" />');	
		$("#title").val('<c:out value="${boardContent.title}"/>');
		$("#content").val('<c:out value="${boardContent.content}"/>');
		$("#tag").val('<c:out value="${boardContent.tag}"/>');		
		}
	})


	
</script>


</head>
<body>
	<article>
		<div class="container" role="main">
			<h2>board Form</h2>
			<!-- 
				form:from은 spring2.0 부터 지원 -> 데이터 바인딩과 관련된 여러가지 태그를 사용 할 수 있다.
				name 대신에 path라는 속성을 사용한다.
				boardVO에는 mode라는 프로퍼티를 가지고 있지 않기때문에 mode는 일반적인 태그를 사용. form:form은 VO에 등록된 것만 사용할 수 있음.
			 -->
			<form:form name="form" id="form" role="form" modelAttribute="boardVO" method="post" action="${pageContext.request.contextPath}/board/saveBoard">
				<form:hidden path="bid"/>
				<input type="hidden" name="mode" />
				<div class="mb-3">
					<label for="title">제목</label>
					<form:input path="title"  class="form-control" id="title" placeholder="제목을 입력해 주세요" />
				</div>
				<div class="mb-3">
					<label for="reg_id">작성자</label>
					<form:input path="reg_id" class="form-control" id="reg_id" placeholder="이름을 입력해 주세요" />
				</div>
				<div class="mb-3">
					<label for="content">내용</label>
					<form:textarea class="form-control" rows="5" path="content" id="content" placeholder="내용을 입력해 주세요" />
				</div>
				<div class="mb-3">
					<label for="tag">TAG</label>
					<form:input class="form-control" path="tag" id="tag" placeholder="태그를 입력해 주세요" />
				</div>
				<input type="hidden" class="form-control" name="cate_cd" id="cate_cd" value="cate_cd">
			</form:form>
			<div >
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
			</div>
		</div>
	</article>

</body>
</html>