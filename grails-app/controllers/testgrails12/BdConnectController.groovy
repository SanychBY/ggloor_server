package testgrails12

class BdConnectController {

    def index() {
       /* def team = Teams.get(4)
        team.name = "123"
        team.save()
        render team.name*/
        def team = Teams.get(1)
        //team.name = '3424231'
        Teams.executeUpdate("update Teams set name = '00000' where id = 1")
        def all = Teams.findAll("from Teams")
        [TeamsList:all]
    }
}
