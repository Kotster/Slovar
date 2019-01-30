<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mderzhavin
  Date: 30.01.2019
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form:form modelAttribute="model" action="select/selectDict" method="post">
        <form:select path="str" onchange="submit();">
            <form:option value=""></form:option>
            <form:options items="${list}"/>
        </form:select>
    </form:form>
    <<a href="/">back to select dictionary</a>
</body>
</html>
