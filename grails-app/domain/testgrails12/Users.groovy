package testgrails12

class Users {
    String nick
    String password
    String email
    Integer status
    Integer frequency
    String vk_access_token
    Boolean vk_notify
    Integer vk_id
    static mapping = {
        version false
        password column: 'password', sqlType: 'VARCHAR(500)'
        frequency defaultValue: 60000000
        vk_notify defaultValue: true
        vk_access_token nullable: true
        vk_id nullable: true
    }
    static constraints = {
        nick unique: true
        email unique: true
    }
}
