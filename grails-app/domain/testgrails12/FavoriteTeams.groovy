package testgrails12


class FavoriteTeams {
    Users user
    Teams team
    static mapping = {
        team lazy: false
        version false
    }
    static constraints = {
    }
}
