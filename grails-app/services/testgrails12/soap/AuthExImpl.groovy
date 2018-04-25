package testgrails12.soap

import email.EmailHelper
import grails.converters.JSON
import hash.MyHashSHA256
import randomstr.RandomString
import regEx.RegExHelper
import testgrails12.AuthorizationService
import testgrails12.KeyLogger
import testgrails12.Matches
import testgrails12.Users

import javax.jws.WebService

/**
 * Created by ssaan on 25.04.2017.
 */

@WebService(endpointInterface = "testgrails12.soap.AuthEx", name = "AuthEx")
class AuthExImpl implements AuthEx{
    //def authorizationService = new AuthorizationService()
    @Override
    String Auth(String nick, String password) {
        System.out.println('Soap req')
        Users.withNewSession {
            def user = Users.find('from Users where (nick = :nick or email = :email) ' +
                    'and password = :password',[nick:nick, email:nick, password: MyHashSHA256.getHashSHA256(password)])
            if(user != null){
                if(user.status == 0){
                    return '1'
                }
                def key = RandomString.getRandomString(50)
                def date = new Date()
                def calendar = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay(), date.getHours(),date.getMinutes())
                calendar.add(Calendar.MONTH,1)
                def kl = new KeyLogger(keyStr: key, dateStart: new Date(), dateEnd: calendar.getTime(), user: user)
                kl.save()
                return key
            }else{
                return '0'
            }
        }

    }

    @Override
    String AuthTest(String key) {
        Users.withNewSession {
            def auth = new AuthorizationService()
            if(auth.testAuth(key) == null){
                return '0'
            }else {
                return '1'
            }
        }
    }

    @Override
    String Reg(String nick, String email, String password, String passwordRep) {
        Users.withNewSession {
            nick = nick.trim()
            email = email.trim()
            if (password != passwordRep || password.length() < 5) {
                return '1'//пароли не совпадают или слишком короткие
            }
            RegExHelper reh = new RegExHelper()
            if (nick.length() > 2 && nick.length() < 10 && reh.Test(nick, "^[1-9a-zA-Z_-]+")) {
                def user = Users.findByNick(nick)
                if (user != null) {
                    return '2'//пользователь существует
                }
            } else {
                return '3'//ошибка в длинне ника

            }
            if (email.length() < 30 && email.contains('@')) {
                def user = Users.findByEmail(email)
                if (user != null) {
                    return '4'//email существует
                }
            } else {
                return '5'//ошибка в длинне эмейла
            }
            def user = new Users(nick: nick, email: email, password: MyHashSHA256.getHashSHA256(password), status: 0)
            user.save()
            def str = RandomString.getRandomString(100)
            def keyLogger = new KeyLogger(user: user, dateStart: new Date(), keyStr: str)
            keyLogger.save()
            if (user == null || keyLogger == null) {
                return '6'
            }
            EmailHelper emailHelper = new EmailHelper()
            emailHelper.sendEmail("Регистрация на GGLoor", "Спасибо за регистрацию," + nick + "!\n\n" +
                    "Перейдите по ссылке ниже для завершения регистраци.\n\n" +
                    "http://site.com/mainPage/regEnd?h=" + str, email)
            return 'ok'
        }
    }
}
