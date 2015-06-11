<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'index.jsp' starting page</title>

<script type="text/javascript" src="<c:url value='/js/jquery-1.7.1.min.js'/>"></script>
<script type="text/javascript">
	function getData() {
		$("#list").html("");
		alert("xxx");
        $.getJSON(
            "http://localhost:8080/shopping/res.upload",//产生JSON数据的服务端页面
            {pic_size_id:$("#pic_size_id").val(), table_name:$("#table_name").val()},//向服务器发出的查询字符串（此参数可选）
             //对返回的JSON数据进行处理，本例以列表的形式呈现
            function(json){
                //循环取json中的数据,并呈现在列表中
                $.each(json,function(i){
                    $("#list").append("<li>name:"+json[i].companyId+"&nbsp; Age:"+json[i].showType+"</li>");
                })
                
                
            }
        );
	}
</script>
</head>

<body>
	<form id="resForm" method="post" action="res.upload" enctype="multipart/form-data">
		<p>
		 <label>pic_size_id:</label><input name="pic_size_id" type="text" value="3"/>
		</p>
		
		<p>
		 <label>table_name:</label><input name="table_name" type="text" value="us_user_info"/>
		</p>
		<p>
		 <label>record_id:</label><input name="record_id" type="text" value="111"/>
		</p>
		<p>
		 <label>res_id:</label><input name="res_id" type="text" value="0"/>
		</p>
		<p>
		 <label>pic_file:</label><input name="pic_file" type="file"/>
		</p>
		<button id="Button1" value="确定" type="submit"></button>
	</form>
	<ul id="list"></ul>
</body>
</html>
