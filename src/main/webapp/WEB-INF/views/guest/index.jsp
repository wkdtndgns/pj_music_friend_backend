<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:url var="R" value="/" />

<!DOCTYPE html>
<html>
<head>
    <title>음악 친구 찾기 </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${R}res/main.css"/>
    <link type="text/css" rel="stylesheet" href="${R}res/heart.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="frame">
<!-- 1번 프레임 시작  -->
	<div class="menu" style="textalr">            
    	<div class="row">
            <div class="col-md-2" style="padding-left: 50px; padding-top:30px;  ">
            	<a href="${R}guest/index"><img src="/pj_music_friend_backend/resources/img/title.PNG" /></a>
            </div>
            <div class="col-md-6">
                <ul class="text-center" style="padding-top: 50px;">
                      <li><a href="${R}guest/index">Home</a></li>
                      <li><a href="${R}music/list">My ❤ </a></li>
                      <li><a href="${R}music/friendList">친구 관리</a></li>
                      <li><a href="${R}guest/login">Login</a></li>
                 </ul>
			</div>
			<div class="col-md-4"></div>
         </div>
     </div>
     <!-- 1번 프레임 끝  -->
	 
     <!-- 2번 프레임 시작  -->
     <div class="header" style="padding-top: 10px;">
     	<div class="title text-center"> <strong>실시간 차트</strong></div>
     	<hr/>
     </div>
     <!-- 2번 프레임 끝  -->

     <!-- 3번 프레임 시작  -->
     <div class="container text-center" style="height: 600px" >
     <!--4번 프레임 시작-->
     	<div class="content">
            <table class="table table-responsive">
            <thead>
            	<tr>
                	<td>순위</td>
                	<td>변화</td>
                    <td>제목 </td>
                    <td>가수 </td>
                    <td>앨범</td>
                </tr>
            </thead>
            <tbody>
		        <c:forEach var="music" items="${ list }">
		            <tr>
		                <td>${ music.rank }</td>
		                <td>${ music.rate }</td>
		                <td>${ music.title }</td>
		                <td>${ music.singer }</td>
		                <td>${ music.album }</td>
		            </tr>
		        </c:forEach>
	        </tbody>
			</table>
		</div>
     <!--4번 프레임 끝-->
     </div>
     <hr/>
     <!-- 3번 프레임 끝  -->
</div>
</body>
</html>
