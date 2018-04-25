package soap;

import javax.xml.ws.Endpoint;

/**
 * Created by ssaan on 23.04.2017.
 */
public class Publ  {
    public static void main(String[] args) {
        Endpoint.publish("http://192.168.100.8:9999/ws/hello", new HelloWorldImpl());
    }
    public static Endpoint ex()
    {
       return Endpoint.publish("http://192.168.43.48:9999/ws/hello", new HelloWorldImpl());
    }
}
