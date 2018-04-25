<%--
  Created by IntelliJ IDEA.
  User: ssaan
  Date: 08.04.2018
  Time: 14:04
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${title}</title>
    <asset:stylesheet href="ggloor.css"/>
    <asset:javascript src="jquery-2.2.0.min.js"/>
</head>

<body>

    <div id="listOfMatch">
        <g:if test="${match != null}">
            <div class="listOfMatchItems" id="listOfMatchItem_${match.id}">
                <div class="team1div" data-idTeam="${match.team1.id}">
                    <img src="/static/images/teamcover/${match.team1.cover}">
                    <div>${match.team1.name}</div>
                </div>
                <div class="matchData">
                    %{--<div>${match.dateMatch.hours}:${match.dateMatch.minutes}
                    ${match.dateMatch.day}.${match.dateMatch.month + 1}.${match.dateMatch.year + 1900}</div>--}%
                    <div>${match.dateMatch.hours}:${match.dateMatch.minutes} ${match.dateMatch.dateString}</div>
                    <p>BO ${match.colGames}</p>
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

            </div>
        </g:if>
        <g:else>
            <div>Такого матча нет =(</div>
        </g:else>
        <div id="games_list">
            <g:each status="i" var="game" in="${games}">
                <div class="one_game_item">
                    <div>Game ${i + 1}</div>

                </div>
            </g:each>
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#game_create').click(function () {
                    var id = $(this).attr('data-id');
                    var game_score1 = $('#game_score_1').val();
                    var game_score2 = $('#game_score_2').val();
                    var game_min = $('#game_min').val();
                    var game_sec = $('#game_sec').val();
                    var game_link = $('#game_stream_link').val();
                    $.post('/Match/addMatch', {matchId: id, score1: game_score1, score2: game_score2, min: game_min,  sec: game_sec, link: game_link}, function (data) {
                        if(data == 'ok'){
                           //location.reload();
                        }
                    });
                });
            });
        </script>
        <div id="add_game_block">
            <input type="number" id="game_score_1" placeholder="счет 1">
            <br>
            <input type="number" id="game_score_2" placeholder="счет 2">
            <br>
            <input type="number" id="game_min" placeholder="min" min="0">
            <br>
            <input type="number" id="game_sec" placeholder="sec" min="0" max="59">
            <br>
            <input type="text" id="game_stream_link" placeholder="ссылка на запись">
            <br>
            <input data-id="${match.id}" type="button" id="game_create" value="добавить игру">
        </div>
    </div>
</body>
</html>