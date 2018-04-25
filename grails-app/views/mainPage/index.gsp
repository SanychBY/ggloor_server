<%--
  Created by IntelliJ IDEA.
  User: ssaan
  Date: 03.04.2017
  Time: 11:08
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${title}</title>
    <script type="text/javascript">
        function addLikeTeam(id) {
            $.post('/mainPage/addLike', {id: id}, function (data) {
                if(data == '1' || data == '2'){
                    $('#messageEv').show().html('Добавлено').fadeOut(2000);
                }else {
                    $('#messageEv').show().html('Error').fadeOut(2000);
                }
            });
        }
        $(document).ready(function () {
            $('.team1div').click(function () {
                addLikeTeam($(this).attr('data-idTeam'));
            });
            $('.team2div').click(function () {
                addLikeTeam($(this).attr('data-idTeam'));
            });
            <g:if test="adm">
                $('.buttonsStatus1').click(function () {
                    var id = $(this).attr('data-idMatch');
                    $.post('/mainPage/endStatusMatch', {id: id}, function (data) {
                        if(data == 'ok'){
                            $('#matchStatus_' + id).html("<p>Завершен</p>");
                        }
                    });
                });
                $('.buttonsDel1').click(function () {
                    var id = $(this).attr('data-idMatch');
                    $.post('/mainPage/deleteMatch', {id: id}, function (data) {
                        if(data == 'ok'){
                            $('#listOfMatchItem_' + id).hide();
                        }
                    });
                });
            </g:if>
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
        <div id="listOfMatch">
            <g:each var="match" in="${matchesList}">
                <div class="listOfMatchItems" id="listOfMatchItem_${match.id}">
                    <div class="team1div" data-idTeam="${match.team1.id}">
                        <img src="/static/images/teamcover/${match.team1.cover}">
                        <div>${match.team1.name}</div>
                    </div>
                    <div class="matchData">
                        %{--<div>${match.dateMatch.hours}:${match.dateMatch.minutes}
                        ${match.dateMatch.day}.${match.dateMatch.month + 1}.${match.dateMatch.year + 1900}</div>--}%
                        <div>${match.dateMatch.hours}:${match.dateMatch.minutes} ${match.dateMatch.dateString}</div>
                        <p><a style="color: inherit" href="Match?id=${match.id}">BO ${match.colGames}</a></p>
                        <div id="matchStatus_${match.id}">
                        <g:if test="${match.dateMatch.getTime() < nowTime.getTime() && match.status == null}">
                            <p style="color: #ff2021">LIVE</p>
                        </g:if>
                        <g:if test="${match.status == 1}">
                            Завершен
                        </g:if>
                        </div>

                    </div>
                    <div class="team2div" data-idTeam="${match.team2.id}">
                        <img src="/static/images/teamcover/${match.team2.cover}">
                        <div>${match.team2.name}</div>
                    </div>
                    <g:if test="${adm}">
                        <div>
                            <g:if test="${match.dateMatch.getTime() < nowTime.getTime() && match.status == null}">
                                <button data-idMatch="${match.id}" class="buttonsStatus1">Завершить</button>
                            </g:if>
                            <button data-idMatch="${match.id}" class="buttonsDel1">Удалить</button>
                        </div>
                    </g:if>
                </div>
            </g:each>
        </div>
    </div>
<footer id="footer">
</footer>
</body>
</html>