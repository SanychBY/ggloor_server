package testgrails12

import hash.MyHashSHA256
import randomstr.RandomString

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AdminService {

    def cookieService
    def goOut(){
        def key = cookieService.getCookie('AdminKey')
        if(key != null) {
            KeyLoggerAdmin.executeUpdate("delete KeyLoggerAdmin where key_str = ?", [key])
            cookieService.deleteCookie('AdminKey')
        }
    }
    def loginingTest(HttpServletRequest request){
        def key = request.cookies.find{'AdminKey' == it.name}
        if(key != null) key = key.value
        def input = false
        if(key != null){
            def keyDB = KeyLoggerAdmin.findByKeyStr(key)
            if(keyDB != null){
                input = true
            }
        }
        return input
    }
    def resInput(HttpServletResponse response, String login, String password){
        def answer = 'error'
        if(login != null && password != null){
            def adminRes = Admin.find(new Admin(login: login,password: MyHashSHA256.getHashSHA256(password)))
            if(adminRes != null){
                String randStr = RandomString.getRandomString(50)
                def keyLogger = new KeyLoggerAdmin(admin: adminRes, dateStart: new Date(), keyStr: randStr)
                keyLogger.save()
                cookieService.setCookie('AdminKey', randStr, 2592000,'/')
                /*def cookie = new Cookie("AdminKey", randStr)
                cookie.path = '/'
                cookie.maxAge = 24 * 60 * 60
                response.addCookie(cookie)*/
                answer = 'success'
            } else {
                answer = 'error 2'
            }
        } else{
            answer = 'error 1'
        }
        return answer
    }
}
