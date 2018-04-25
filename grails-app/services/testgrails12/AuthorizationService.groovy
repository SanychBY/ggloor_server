package testgrails12

import hash.MyHashSHA256
import randomstr.RandomString


class AuthorizationService {
    def cookieService

    def Auth(String nick, String password) {
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
            cookieService.setCookie('keyAuth',key,2592000,'/')
            return key
        }else{
            return '0'
        }
    }
    Users testAuth(String key){
        if(key != null) {
            def kl = KeyLogger.findByKeyStr(key)
            if (kl != null) {
                def user = Users.get(kl.userId)
                if(user != null && user.status == 1){
                    return user
                }else{
                    return null
                }
            }
        }else {
            return null
        }
    }
}
