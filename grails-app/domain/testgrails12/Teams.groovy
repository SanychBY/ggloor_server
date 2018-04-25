package testgrails12

import grails.rest.Resource

@Resource(uri = '/TeamsRest')
class Teams {
    String name
    String cover
    static mapping = {
        table 'teams'
        version false
    }
    static constraints = {
        name nullable: true
        name unique: true
        cover nullable: true
    }
}
