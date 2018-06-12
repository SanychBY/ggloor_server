<%--
  Created by IntelliJ IDEA.
  User: ssaan
  Date: 12.05.2017
  Time: 10:57
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <asset:javascript src="bootstrap.js"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="com.bitloor.js"/>
    <asset:stylesheet href="ggloor.css" />
    <title>Вход</title>
    <script type="text/javascript">
        function successReg(data) {
            if(data != '1' && data != '2'){
                $('#errorInfInput').css('color','#008000').html('Сейчас Вы будете перенаправлены');
                window.location.replace("http://" + window.location.host + "/");
            }else {
                if(data == '1'){
                    $('#errorInfInput').html('Неверный ник или пароль')
                }
                if(data == '2'){
                    $('#errorInfInput').html('Ваш аккаунт не активирован. Проверьте свою почту')
                }
            }
        }
        function b() {
            return true;
        }
    </script>
    <style>
    #form_reg {
        padding: 30px 10px 30px;
        text-align: center;
    }
    #page_content{
        display: table;
        margin: 70px auto;
        background: #4d4e52;
    }
    .buttonStyle1{
        display: table;
        margin: 0 auto;
    }
    .errorStyle1{
        color: #d58512;
        margin: 0 0 5px 20px;
    }
    </style>
</head>

<body>
<div id="header">
    %{--<asset:image src="apple-touch-icon.png" alt="logo"/>--}%
    <a href="/"><div id="logo">ggloor</div></a>
</div>
<div id="page_content">
    <div id="form_reg">
        <g:form action="inputRes">
            <div class="errorStyle1" id="errorInfInput"></div>
            <g:textField name="nick" placeholder="Ник или email" class="inputStyle1"/>
            <g:passwordField name="password" placeholder="Пароль" class="inputStyle1"/>
            <g:submitToRemote before="if (!b()) return false" onSuccess="successReg(data);" url="[controller:'MainPage',action:'inputRes']" class="buttonStyle1" name="input" value="Войти"/>
        </g:form>
        <a style="color: #E7AE5C" href="/mainPage/newReg">Регистрация</a>
    </div>
</div>
</body>
</html>