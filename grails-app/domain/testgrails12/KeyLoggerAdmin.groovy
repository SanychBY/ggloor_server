package testgrails12

class KeyLoggerAdmin {
    Admin admin
    String keyStr
    Date dateStart
    Date dateEnd
    static mapping = {
        version false
        keyStr column: 'key_str', sqlType: 'VARCHAR(500)'
    }
    static constraints = {
        dateStart nullable: true
        dateEnd nullable: true
    }
}
