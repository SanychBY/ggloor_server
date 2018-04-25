package testgrails12

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'MainPage')
        "500"(view:'/error')
        "404"(view:'/notFound')
        get "/MatchesRestPage/$page"(controller:'Rest',action:"GetMatches")
        get "/LikesRestPage/$key"(controller:'rest', action:"GetLikes")
        get "/TeamssRest/$page"(controller:'Rest',action:"index")

    }
}
