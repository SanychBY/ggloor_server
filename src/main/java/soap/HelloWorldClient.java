package soap;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWorldClient{

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:8081/ws/hello?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://soap.testgrails12/", "MatchesExImplService");

        Service service = Service.create(url, qname);
        QName port_name = new QName("http://soap.testgrails12/","MatchesExPort");
        HelloWorld hello = service.getPort(port_name,HelloWorld.class);

        System.out.println(hello.getHelloWorldAsString("alex"));

    }

}
