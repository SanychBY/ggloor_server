package testgrails12

class Users {
    String nick
    String password
    String email
    Integer status
    static mapping = {
        version false
        password column: 'password', sqlType: 'VARCHAR(500)'
    }
    static constraints = {
        nick unique: true
        email unique: true
    }
}
