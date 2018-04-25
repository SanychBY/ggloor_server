package testgrails12

class Comments {
    String text
    Date date
    Users user
    Matches match
    static mapping = {
        version false
    }
    static constraints = {
    }
}
