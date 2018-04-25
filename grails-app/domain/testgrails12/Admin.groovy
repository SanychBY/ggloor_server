package testgrails12

class Admin {
    String login
    String password
    static mapping = {
        id false
        version false
        password column: 'password', sqlType: 'VARCHAR(500)'
    }
    static constraints = {

    }
}
