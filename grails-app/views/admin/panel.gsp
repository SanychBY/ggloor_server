<%--
  Created by IntelliJ IDEA.
  User: ssaan
  Date: 05.04.2017
  Time: 9:10
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <asset:javascript src="bootstrap.js"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="com.bitloor.js"/>
    <asset:stylesheet href="ggloor.css" />
    <title>Панель управления</title>
    <script type="text/javascript">

        function successAddMatch(data) {
            alert("add ok");
        }

        function successExit(data) {
            window.location.replace("http://" + window.location.host + "/admin/panel");
        }
        $(document).ready(function () {
            $('#uploadTeamData').click(function () {
                jBL('buttonLoadCover').goFiles({type: 'POST',url:'/admin/newTeam',file:'file'},
                    {nameTeam:$('#nameTeam').val()},
                function () {}, function () {},
                    function (data) {
                        alert(data);
                    },
                    function (e) {
                        alert("Error! " + e);
                    });
            });
            $("#showTeam").click(function () {
                $(".formsAD").hide();
                $("#teamForm").show();
            });
            $("#showMatch").click(function () {
                $(".formsAD").hide();
                $("#matchForm").show();
            });
        });
    </script>
</head>

<body>
    <g:if test="${input}">
        <div style="text-align: center">
            <div>
                <button id="showTeam">Создать команду</button>
                <button id="showMatch">Создать матч</button>
                <g:remoteLink action="goOut" onSuccess="successExit(data)">
                    <button>Выйти</button>
                </g:remoteLink>
            </div>
            <div id="teamForm" class="formsAD" style="display: none">
                <g:form enctype="multipart/form-data" onsubmit="return false">
                    <g:textField name="nameTeam" placeholder="Название"/>
                    <p><input type="file" id="buttonLoadCover"></p>
                    <button id="uploadTeamData">Создать</button>
                    %{--<g:submitToRemote url="[controller:'admin',action:'newTeam']" name="newTeam" value="Создать"/>--}%
                </g:form>
            </div>
            <div id="matchForm" class="formsAD" style="display: none">
                <g:form action="newMatch" name="newMatchForm">
                    <p><g:select from="${teamsArr}" optionValue="name" optionKey="id" name="team1"/></p>
                    <p><g:select from="${teamsArr}" optionValue="name" optionKey="id" name="team2"/></p>
                    <p><g:select from="${2017..2018}" name="year"/>-
                    <g:select from="${1..12}" name="month"/>-
                    <g:select name="day" from="${1..31}"/> <g:select from="${0..23}" name="h"/>:<g:field value="0" type="number" min="0" max="59" name="min"/></p>
                    <p><g:field type="number" value="1" min="1" max="5" name="colGames"/></p>
                    <g:submitToRemote onSuccess="successAddMatch(data)" url="[controller:'admin',action:'newMatch']" name="newMatch" value="Создать"/>
                </g:form>
            </div>
            <div>
                <a href="${vklink}">vk</a>
            </div>
        </div>
    </g:if>
    <g:else>
        <div>
            <h3>Страница для закрытого доступа</h3>
        </div>
    </g:else>
</body>
</html>