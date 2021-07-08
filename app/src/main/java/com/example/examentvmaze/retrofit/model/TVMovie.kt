package com.example.examentvmaze.retrofit.model

class TVMovie(
    var id: Int,
    var url: String,
    var name: String,
    var season: Int,
    var number: Int,
    var type: String,
    var airdate: String,
    var airtime: String,
    var airstamp: String,
    var runtime: Int,
    var image: TVImage,
    var summary: String,
    var show: TVShow,
    var _links: TVLink
)

class TVImage(var medium: String, var original: String)
class TVShow(
    var id: Int,
    var url: String,
    var name: String,
    var type: String,
    var language: String,
    var genres: ArrayList<String>,
    var status: String,
    var runtime: Int,
    var averageRuntime: Int,
    var premiered: String,
    var officialSite: String,
    var schedule: TVShedule,
    var rating: TVRating,
    var weight: Int,
    var network: TVNetwork,
    var dvdCountry: String,
    var externals: TVExternals,
    var image: TVShowImage,
    var summary: String
)

data class TVRating(var average: Double)
class TVShowImage(var medium: String, var original: String)
class TVExternals(var tvrage: Int, var thetvdb: Int, var imdb: String)
class TVNetwork(var id: Int, var name: String, var country: TVCountry)
class TVCountry(var name: String, var code: String, var timezone: String)
class TVShedule(var time: String, var days: ArrayList<String>)
class TVLink(var self: TVSelf)
class TVSelf(var href: String)