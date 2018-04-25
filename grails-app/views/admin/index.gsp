<%--
  Created by IntelliJ IDEA.
  User: ssaan
  Date: 04.04.2017
  Time: 10:55
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin</title>
    <asset:javascript src="application.js"/>
    <script type="text/javascript">
        function successRequestAdmin(data) {
            if(data.split(' ')[0] == 'error'){
                jQuery('#answer').html('Неверный логин или пароль');
            }
            if(data == 'success')
            {
                jQuery('#answer').html('Сейчас вы будете перенаправлены');
                window.location.replace("http://" + window.location.host + "/admin/panel");
            }
        }
    </script>
    <style>
        .input1{
            width: 200px;
            padding: 3px;
        }
    </style>
</head>

<body>
        <g:form action="resInput" style="text-align: center">
            <g:textField name="login" placeholder="Ник" class="input1"/>
            <p></p>
            <g:passwordField name="password" placeholder="Пароль" class="input1"/>
            <p></p>
            <g:submitToRemote onSuccess="successRequestAdmin(data);" url="[controller:'admin',action:'resInput']" name="input" value="input"/>
        </g:form>
        <div id="answer">qwertrty</div>
</body>
</html>