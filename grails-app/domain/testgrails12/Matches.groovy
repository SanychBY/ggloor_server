package testgrails12

import grails.rest.Resource

@Resource(uri = '/MatchesRest', readOnly = true)
class Matches {

    Teams team1
    Teams team2
    Date dateMatch
    Integer status
    Integer colGames
    String streamUrl
    Tournament tournament
    static mapping = {
        version false
        status defaultValue: 0
        colGames defaultValue: 1
        team1 lazy: false
        team2 lazy: false
    }
    static constraints = {
        team1 nullable: true
        team2 nullable: true
        dateMatch nullable: true
        status nullable: true
        colGames nullable: true
        streamUrl nullable: true
        tournament nullable: true
    }
}
