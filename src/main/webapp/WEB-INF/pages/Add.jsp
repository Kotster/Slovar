<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mderzhavin
  Date: 30.01.2019
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form:form modelAttribute="model" action="/add/postadd">
        <form:input  path="key"></form:input>
        <form:input  path="value"></form:input>
        <form:select path="name">
            <form:options items="${ListDict}"></form:options>
        </form:select>
        <form:button>check</form:button>
    </form:form>
    <a href="/">home</a>
</body>
</html>
