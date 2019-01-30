<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mderzhavin
  Date: 30.01.2019
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form:form modelAttribute="modelDictionary" action="/update" method="post">
        <form:input  path="key"></form:input>
        <form:input path="value"></form:input>
        <form:button>check</form:button>
    </form:form>
    <a href="dictionary">back to dictionary</a>
</body>
</html>
