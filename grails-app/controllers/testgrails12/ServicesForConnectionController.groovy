package testgrails12

import grails.converters.JSON
import testgrails12.soap.Pub

import javax.xml.ws.Endpoint


class ServicesForConnectionController {
   // def PubService
    Endpoint ep
    def index() {
        render '<h2>Rest, soap контроллер</h2>'
        List<Matches> matches = Matches.findAll('from Matches order by dateMatch desc',[max:10, offset:0*10])
            System.out.println(matches[0].team1 as JSON)
            JSON.use('deep')
            render matches as JSON
            return matches as JSON
    }
    def soap(){
        ep = Pub.ex()
    }
    def stop(){
        ep.stop()
    }
}
