package testgrails12

class Tournament {
    String name
    String info
    String img
    static mapping = {
        version false
    }
    static constraints = {
        info nullable: true
        img nullable: true
    }
}
