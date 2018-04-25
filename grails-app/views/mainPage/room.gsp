<%--
  Created by IntelliJ IDEA.
  User: ssaan
  Date: 16.05.2017
  Time: 9:58
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Кабинет</title>
    <style>
        #page_content{
            padding: 15px;
            text-align: center;
        }
        .oneTeamLike{
            width: 300px;
        }
        .oneTeamLikeName{
            color: whitesmoke;
        }
        .oneTeamLikeRightBlock{
            float: right;
            text-align: center;
            width: 155px;
            margin: 10px 0;
        }
        .oneTeamLikeButton{
            border: 0;
            background: #eab05d;
            font-size: 15px;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            margin: 10px;
        }
        .oneTeamLikeButton:hover{
            background: #d58512;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#exitRoomButton').click(function () {
                $.post('/mainPage/exit', {}, function (data) {
                    if(data == 'ok'){
                        $('#messageEv').show().html('Перенаправление').fadeOut(2000);
                        window.location.replace("http://" + window.location.host + "/mainPage");
                    }else {
                        $('#messageEv').show().html('Error').fadeOut(2000);
                    }
                });
            });
            $('.oneTeamLikeButton').click(function () {
                var obj = this;
                $.post('/mainPage/delLikeTeam', {id:$(this).attr('data-idTeam')}, function (data) {
                    if(data == 'ok'){
                        $("#oneTeamLike_" + $(obj).attr('data-idF')).hide();
                        $('#messageEv').show().html('Удалено').fadeOut(2000);
                    }else {
                        $('#messageEv').show().html('Error').fadeOut(2000);
                    }
                });
            })
        });
    </script>
</head>

<body>
<div id="messageEv">Добавлено</div>
<div id="header">
    %{--<asset:image src="apple-touch-icon.png" alt="logo"/>--}%
    <a href="/"><div id="logo">ggloor</div></a>
    <g:if test="${login != null}"><a id="roomHeaderLink" href="/mainPage/room">Кабинет</a></g:if>
    <g:else>
        <a href="/mainPage/input"><input class="buttonStyle2" id="inputButtonHeader" type="button" value="Вход"></a>
    </g:else>
</div>
<div id="MainDiv">
<div id="page_content">
    <g:if test="${login != null}">
        <g:each var="team" in="${listLikeTeam}">
            <div class="oneTeamLike" id="oneTeamLike_${team.id}">
                <img src="/static/images/teamcover/${team.team.cover}">
                <div class="oneTeamLikeRightBlock">
                    <div class="oneTeamLikeName">${team.team.name}</div>
                    <input type="button" value="Удалить" class="oneTeamLikeButton" data-idF="${team.id}" data-idTeam="${team.team.id}"/>
                </div>
            </div>
        </g:each>
        <input type="button" id="exitRoomButton" class="buttonStyle1" value="Выйти">
    </g:if>
    <g:else>
        <h2>Доступ на эту страницу разрешен только зарегистрированным пользователям</h2>
    </g:else>
</div>
</div>
</body>
</html>