package testgrails12.soap

import javax.jws.WebMethod
import javax.jws.WebService
import javax.jws.soap.SOAPBinding

/**
 * Created by ssaan on 23.04.2017.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
interface AuthEx {
    @WebMethod
    String Auth(String nick, String password)
    @WebMethod
    String AuthTest(String key)
    @WebMethod
    String Reg(String nick, String email, String password, String passwordRep)
}