package testgrails12.soap

import testgrails12.AppSettings

import javax.xml.ws.Endpoint

/**
 * Created by ssaan on 25.04.2017.
 */
class Pub {
    static Endpoint ex()
    {
        def link = AppSettings.findByKey("ip_soap")
        return Endpoint.publish(link.value, new AuthExImpl())
    }
}
