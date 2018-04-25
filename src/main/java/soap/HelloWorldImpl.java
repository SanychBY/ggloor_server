package soap;


import javax.jws.WebService;

@WebService(endpointInterface = "soap.HelloWorld", name = "AuthEx")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name + " ";
    }
}
