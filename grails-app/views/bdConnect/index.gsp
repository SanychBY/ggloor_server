<%--
  Created by IntelliJ IDEA.
  User: ssaan
  Date: 03.04.2017
  Time: 10:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>BdConnect</title>
</head>

<body>
    <g:each var="Team" in="${TeamsList}">
        <p>ID: ${Team.id}</p>
        <p>Name: ${Team.name}</p>
    </g:each>
</body>
</html>