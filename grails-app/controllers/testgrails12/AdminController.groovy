package testgrails12

import randomstr.RandomString


class AdminController {
    def adminService
    def index() {
        /*def cookie = new Cookie("my",'1111')
        cookie.path = '/'
        response.addCookie cookie*/

    }
    def panel() {
        def input = adminService.loginingTest(request)
        if(input) {
            def teams = Teams.findAll('from Teams')
            def link = AppSettings.findByKey("vk_aut_link")
            [teamsArr: teams, input: input, vklink: link.value]
        }
        else{
            [input:input]
        }
    }
    def resInput(String login, String password){
        render adminService.resInput(response, login, password)
    }
    def goOut(){
        adminService.goOut()
        render 'ok'
    }
    def newTeam(String nameTeam){
        try {
            if (nameTeam != null && nameTeam != '') {
                def team = new Teams(name: nameTeam)
                def uploadedFile = request.getFile("file0")
                def newFileName = null
                if (uploadedFile != null) {
                    String[] expansion = uploadedFile.originalFilename.tokenize('.')
                    def webrootDir = servletContext.getRealPath("/")
                    newFileName = RandomString.getRandomString(20) + '.' + expansion[expansion.length - 1]
                    team.cover = newFileName
                    team.save()
                    if(team.id != null){
                        File file = new File(webrootDir + 'images/teamcover/' + newFileName)
                        file.bytes = uploadedFile.getBytes()
                        render 'success'
                    }else {
                        render 'not save'
                    }
                }else{
                    team.save()
                    render 'success'
                }

            } else {
                render 'error'
            }
        }catch (Exception e){
            render 'Error:'  + e.message
        }
    }
    def newMatch(Integer team1, Integer team2, Integer year, Integer month,Integer day, Integer h, Integer min, Integer colGames){
        def match = new Matches(team1: Teams.findById(team1), team2: Teams.findById(team2), colGames: colGames, dateMatch:
                new Date(year - 1900, month - 1, day, h, min))
        match.save()
    }
}
