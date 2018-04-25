package testgrails12

import grails.rest.Resource

@Resource(uri = '/GameRest', readOnly = true)
class Games {
    Teams teamWin
    Integer scor1
    Integer scor2
    Double gameTime
    Matches match
    Integer status
    String recordingLink
    static mapping = {
        version false
        status defaultValue: 0
    }
    static constraints = {
        teamWin nullable: true
        scor1 nullable: true
        scor2 nullable: true
        gameTime nullable: true
        match nullable: true
        recordingLink nullable: true
    }
}
