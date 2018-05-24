package testgrails12.soap


import javax.xml.ws.Endpoint

/**
 * Created by ssaan on 25.04.2017.
 */
class Pub {
    static Endpoint ex()
    {
        return Endpoint.publish("http://192.168.100.4:9999/ws/mysoap", new AuthExImpl())
    }
}
