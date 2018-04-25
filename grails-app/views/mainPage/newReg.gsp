<%--
  Created by IntelliJ IDEA.
  User: ssaan
  Date: 09.05.2017
  Time: 21:33
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Регистрация</title>
    <script type="text/javascript">
        function successReg(data) {
            if(data == 'ok'){
                $('#form_reg').html("<h3>Проверьте вашу электронную почту и подтвердите регистрацию</h3>");
                return;
            }
            if(data == '2'){
                $('#errorInfNick').html('Пользователь с таким ником уже существует');
                return;
            }
            if(data == '4'){
                $('#errorInfEmail').html('Такой email уже зарегистрирован');
                return;
            }
            alert('Неизвестная ошибка. Обратитесь к администратору.')
        }
        function b() {
            $('.errorStyle1').html('');
            $('.inputStyle1').css('border','0px');
            var nick = $('[name = "nick"]').val();
            var email = $('[name = "email"]').val();
            var password = $('[name = "password"]').val();
            var passwordRep = $('[name = "passwordRep"]').val();
            var flag = true;
            //alert(email.search(/@/));
            if(password.length < 5){
                $('#errorInfPassword').html('Пароль должен быть длиннее 5 символов');
                flag = false;
                $('[name = "password"]').css('border','2px solid #d58512');
                $('[name = "passwordRep"]').css('border','2px solid #d58512');
            }else{
                if(passwordRep != password){
                    $('#errorInfPassword').html('Пароли не совпадают');
                    $('[name = "password"]').css('border','2px solid #d58512');
                    $('[name = "passwordRep"]').css('border','2px solid #d58512');
                    flag = false;
                }
            }
            if(nick.length < 3 || nick.length > 10){
                $('#errorInfNick').html('Длинна ника больше 2, но меньеше 10');
                $('[name = "nick"]').css('border','2px solid #d58512');
                flag = false;
            }else{
                if(nick.search(/^[1-9a-zA-Z\-_]+$/i) != 0){
                    $('#errorInfNick').html('Только цифры, a-Z, _, -');
                    flag = false;
                }
            }
            if(email.search(/@/) == -1){
                $('#errorInfEmail').html('Email некоректный');
                flag = false;
                $('[name = "email"]').css('border','2px solid #d58512');
            }
            return flag;
        }
    </script>
    <style>
    #form_reg {
        padding: 30px 10px 0;
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
            <g:form action="reg">
                <div class="errorStyle1" id="errorInfNick"></div>
                <g:textField name="nick" placeholder="Ник" class="inputStyle1"/>
                <div class="errorStyle1" id="errorInfEmail"></div>
                <g:textField name="email" placeholder="@e-mail" class="inputStyle1"/>
                <div class="errorStyle1" id="errorInfPassword"></div>
                <g:passwordField name="password" placeholder="Пароль" class="inputStyle1"/>
                <g:passwordField name="passwordRep" placeholder="Повторите пароль" class="inputStyle1"/>
                <g:submitToRemote before="if (!b()) return false" onSuccess="successReg(data);" url="[controller:'MainPage',action:'reg']" class="buttonStyle1" name="input" value="Зарегистрироваться"/>
            </g:form>
        </div>
    </div>
</body>
</html>