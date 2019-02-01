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
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function doAjax() {

        var key = document.getElementById("inp").value;
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

                $("#id").text("id:"+data.id);
                $("#key").text("key:"+data.key);
                $("#value").text("value:"+data.value);
                $("#name").text("table:"+data.name);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("ERROR: " + textStatus + " - " + errorThrown);
            }
        });
    }
</script>
<input type="text" id="inp"/>
    <p id="id"></p>
    <p id="key"></p>
    <p id="value"></p>
    <p id="name"></p>
    <button type="button" onclick="doAjax();return false;">OK</button>
<a href="/">home</a>

</body>
</html>
