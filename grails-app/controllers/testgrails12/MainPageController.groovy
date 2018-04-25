package testgrails12

import email.EmailHelper
import hash.MyHashSHA256
import randomstr.RandomString
import regEx.RegExHelper

class MainPageController {
    def authorizationService
    def cookieService
    def index() {
        def matchesList = Matches.findAll('from Matches order by dateMatch desc')
        def login = authorizationService.testAuth(cookieService.getCookie('keyAuth'))
        def admKey = KeyLoggerAdmin.findByKeyStr(cookieService.getCookie('AdminKey'))
        def adm = false
        if(admKey != null){
            adm = true
        }
        [title:'Расписание матчей', matchesList:matchesList, nowTime: new Date(), login:login, adm: adm]
    }
    def newReg(){

    }
    def reg(String nick, String email, String password, String passwordRep){
        nick = nick.trim()
        email = email.trim()
        if(password != passwordRep || password.length() < 5){
            render '1'//пароли не совпадают или слишком короткие
            return
        }
        RegExHelper reh = new RegExHelper()
        if(nick.length() > 2 && nick.length() < 10 && reh.Test(nick, "^[1-9a-zA-Z/-_]+")){
            def user = Users.findByNick(nick)
            if(user != null){
                render '2'//пользователь существует
                return
            }
        } else {
            render '3'//ошибка в длинне ника
            return
        }
        if(email.length()<30 && email.contains('@')){
            def user = Users.findByEmail(email)
            if(user != null) {
                render '4'//email существует
                return
            }
        }else {
            render '5'//ошибка в длинне эмейла
            return
        }
        def user = new Users(nick: nick, email: email, password: MyHashSHA256.getHashSHA256(password), status: 0)
        user.save()
        def str = RandomString.getRandomString(100)
        def keyLogger = new KeyLogger(user: user, dateStart: new Date(), keyStr: str)
        keyLogger.save()
        if(user == null || keyLogger == null) {
            render '6'
            return
        }
        EmailHelper emailHelper = new EmailHelper()
        emailHelper.sendEmail("Регистрация на GGLoor","Спасибо за регистрацию," + nick + "!\n\n" +
                "Перейдите по ссылке ниже для завершения регистраци.\n\n" +
                "http://" + request.getServerName() + "/mainPage/regEnd?h=" + str,email)
        render 'ok'
    }
    def regEnd(String h){
        def keyLogger = KeyLogger.findByKeyStr(h)
        if(keyLogger != null) {
            def user = Users.executeUpdate("update Users set status = 1 where id = ?", [keyLogger.user.id])
            KeyLogger.executeUpdate('delete KeyLogger where keyStr = ?', [h])
            return
        }
    }
    def input(){

    }
    def inputRes(String nick, String password){
        System.out.println('loginig 1111')
        render authorizationService.Auth(nick, password)
    }
    def room(){
        def login = authorizationService.testAuth(cookieService.getCookie('keyAuth'))
        def listLikeTeam = FavoriteTeams.findAll('from FavoriteTeams where user = ?',[login])
        [login: login, listLikeTeam: listLikeTeam]
    }
    def addLike(Integer id){
        Users login = authorizationService.testAuth(cookieService.getCookie('keyAuth'))
        if(login){
            def testFT = FavoriteTeams.find('from FavoriteTeams where user = ? and team = ?', [login, Teams.get(id)])
            if(testFT == null) {
                def ft = new FavoriteTeams(user: login, team: Teams.get(id))
                ft.save()
                render '1'
            }else{
                render '2'
            }
        } else         render '3'

    }
    def addLikeApp(Integer id, String key){
        Users login = authorizationService.testAuth(key)
        if(login){
            def testFT = FavoriteTeams.find('from FavoriteTeams where user = ? and team = ?', [login, Teams.get(id)])
            if(testFT == null) {
                def ft = new FavoriteTeams(user: login, team: Teams.get(id))
                ft.save()
                render '1'
            }else{
                render '2'
            }
        } else         render '3'

    }
    def exit(){
        KeyLogger.executeUpdate('delete KeyLogger where keyStr = ?', [cookieService.getCookie('keyAuth')])
        cookieService.deleteCookie('keyAuth')
        render 'ok'
    }
    def delLikeTeam(Integer id){
        def login = authorizationService.testAuth(cookieService.getCookie('keyAuth'))
        if(login != null){
            FavoriteTeams.executeUpdate('delete FavoriteTeams where user = ? and team = ?', [login, Teams.get(id)])
            render 'ok'
        }
    }
    def delLikeTeamApp(Integer id, String key){
        def login = authorizationService.testAuth(key)
        if(login != null){
            FavoriteTeams.executeUpdate('delete FavoriteTeams where user = ? and team = ?', [login, Teams.get(id)])
            System.out.println(id)
            render 'ok'
        }
    }
    def endStatusMatch(Long id){
        Matches.executeUpdate('update Matches set status = 1 where id = ?', [id])
        render 'ok'
    }
    def deleteMatch(Long id){
        Matches.executeUpdate('delete Matches where id = ?', [id])
        render 'ok'
    }
}
