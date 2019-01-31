<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mderzhavin
  Date: 31.01.2019
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}webapp/WEB-INF/js/jquery.js"></script>
</head>
<body>
<script type="text/javascript">
    function doAjax() {
        // var key = $("#inp").val();
        var key = document.getElementById("inp").value;
        alert(key);
        $.ajax({
            url: 'http://localhost:8081/search/postsearch',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data:({
                key:key
            }),
            success: function (data) {
                //$("#id").text(data.id);
                document.getElementById("id").innerHTML=data.id;
                $("#key").text(data.key);
                $("#value").text(data.value);
                $("#name").text(data.name);
                console.log(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("ERROR: " + textStatus + " - " + errorThrown);
            }
        });
        alert(document.getElementById("id").value);
    }
    alert("End");
</script>
<input type="text" id="inp" value="123"/>
    <p id="id"></p>
    <p id="key"></p>
    <p id="value"></p>
    <p id="name"></p>
    <button type="button" onclick="doAjax();return false;">OK</button>
<a href="/">home</a>

</body>
</html>
