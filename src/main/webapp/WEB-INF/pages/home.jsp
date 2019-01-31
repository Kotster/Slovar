<%--
  Created by IntelliJ IDEA.
  User: mderzhavin
  Date: 28.01.2019
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
    <body>
    <form:form action="/dict" modelAttribute="model" method="post">
        <form:select path="name" onchange="submit();">
            <form:option value=""></form:option>
            <form:options items="${ListDict}"/>
        </form:select>
    </form:form>
    <a href="/add/getadd">Add</a>
    <a href="/delete/getdelete">Delete</a>
    <a href="/update/getupdate">Update</a>
    </body>
</html>