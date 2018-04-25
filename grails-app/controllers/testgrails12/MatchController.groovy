package testgrails12

class MatchController {

    def index(Integer id) {
        def match = Matches.findById(id)
        def games = null
        def title = "0"
        if(match != null) {
            title = match.team1.name + " vs " + match.team2.name + " BO " + match.colGames
            games = Games.findAll("from Games where match_id = ?", [id])
        }else {
            title = 404
        }
        [title:title, match:match, nowTime: new Date(), games: games]
    }
    def addMatch(Integer matchId, Integer score1, Integer score2, Integer min, Integer sec, String link){
        def time = null
        if(min != null & sec !=null){
            time = min * 60 + sec
        }
        def game = new Games(scor1: score1, scor2: score2, gameTime: time, match: Matches.get(matchId), status: 0, recordingLink: link)
        game.save()
        System.out.println(matchId)
        render 'ok'
    }
}
