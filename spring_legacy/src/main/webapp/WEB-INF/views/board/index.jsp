<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "/WEB-INF/views/layout/header.jsp" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<title>board</title>


<script type="text/javascript">

$(document).on('click','#btnWriteForm',function(e){
	e.preventDefault();
	location.href="${pageContext.request.contextPath}/board/boardForm";
	})
	
	function fn_contentView(bid){
		var url="${pageContext.request.contextPath}/board/getBoardContent";
		url = url  + "?bid="+bid;
		location.href = url;
		}
	// 이전 버튼 이벤트 <c:url var="getBoardListURL" value="/board/getBoardList"></c:url> 방식으로 하면 url 부분에 중복을 줄일 수 있다.
	function fn_prev(page,range,rangSize){
		var page = ((range - 2) * rangeSize) + 1;
		var range = range - 1;
		
		var url = "${pageContext.request.contextPath}/board/getBoardList";
		url = url + "?page=" + page;
		url = url + "&range=" + range;

		location.href = url;
		}
	// 페이지 번호 클릭
	function fn_pagination(page,range,rangSize){
		//var url = "${getBoardList}";
		var url = "${pageContext.request.contextPath}/board/getBoardList";
		var url = url + "?page=" + page;
		var url = url + "&range=" + range;
		location.href = url; 
		}
	// 다음 버튼 이벤트
	function fn_next(page,range,rangSize){
		var page = paraseInt((range * rangeSize)) + 1;
		var range = paraseInt(range) + 1;
		//var url = "${getBoardList}";
		var url = "${pageContext.request.contextPath}/board/getBoardList";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;
		}
	// 검색 기능
	$(document).on('click','#btnSearch',function(e){
		e.preventDefault();
		//var url = "${getBoardList}";
		var url = "${pageContext.request.contextPath}/board/getBoardList";
		url = url + "?searchType=" + $('#searchType').val();
		url = url + "&keyword=" + $('#keyword').val();
		location.href = url;
		console.log(url);
		});
</script>

</head>

<body>
<h2>board list</h2>
<article>
	<div class="container">
		<table class="table-responsive">
		<colgroup>
			<col style="width:5%;" />
			<col style="width:auto;" />
			<col style="width:15%;" />
			<col style="width:10%;" />
			<col style="width:10%;" />
		</colgroup>
		<thead>
			<tr>
				<th>NO</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty boardList }" >
					<tr><td colspan="5" align="center">데이터가 없습니다.</td></tr>
				</c:when> 
				<c:when test="${!empty boardList}">
					<c:forEach var="list" items="${boardList}">
						<tr>
							<td><c:out value="${list.bid}"/></td>
							<td>
								<a href="#" onclick="fn_contentView('<c:out value='${list.bid}' />')">
									<c:out value="${list.title}"/>
								</a>
							</td>
							<td><c:out value="${list.reg_id}"/></td>
							<td><c:out value="${list.view_cnt}"/></td>
							<td><c:out value="${list.reg_dt}"/></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
	
	<div>
		<button type="button" class="btn btn-sm btn-primary" id="btnWriteForm">글쓰기</button>
	</div>
	
	<!-- pagination(s) -->
	<div id="paginationBox">
		<ul class="pagination">
			<!-- previous -->
			<c:if test="${pagination.prev}">
				<li class="page-item">
					<a class="page-link" href="#" onclick="fn_prev('${pagination.page}','${pagination.range }','${pagination.rangSize }')">Previous</a>
				</li>
			</c:if>
			<!-- number -->
			<c:forEach begin="${pagination.startPage }" end="${pagination.endPage }" var="idx">
				<li class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> ">
					<a class="page-link" href="#" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')"> ${idx} </a>
				</li>

			</c:forEach>
			
			<!-- NEXT -->
			<c:if test="${pagination.next }">
				<li class="page-item">
					<a class="page-link" href="#" onclick="fn_next('${pagination.page}','${pagination.range }','${pagination.rangSize }')">Next</a>
				</li>
			</c:if>
		</ul>
	</div>
	
	<!-- search -->
	<div class="form-group row justify-content-center">
		<div class="w100" style="padding-right:10px">
			<select class="form-control form-control-sm" name="searchType" id="searchType">
				<option value="title">제목</option>
				<option value="Content">본문</option>
				<option value="reg_id">작성자</option>
			</select>		
		</div>
		<div class="w300" style="padding-right:10px">
			<input type="text" class="form-control form-control-sm" name="keyword" id="keyword" />
		</div>
		<div>
			<button class="btn btn-sm btn-primary" name="btnSearch" id="btnSearch">검색</button>
		</div>
	</div>
	
	
	</div>

</article>


	
</body>
</html>

