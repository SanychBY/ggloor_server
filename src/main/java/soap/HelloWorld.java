package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by ssaan on 23.04.2017.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorld{

    @WebMethod
    String getHelloWorldAsString(String name);
}