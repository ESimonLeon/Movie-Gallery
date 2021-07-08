package com.example.examentvmaze.retrofit.model

data class TVCast(
    var person: TVPerson,
    var character: TVCaracter,
    var self: Boolean,
    var voice: Boolean
)

data class TVPerson(
    var id: Int,
    var url: String,
    var name: String,
    var country: TVCountry,
    var birthday: String,
    var deathday: String,
    var gender: String,
    var image: TVImage,
    var _links: TVLink
)

data class TVCaracter(
    var id: Int,
    var url: String,
    var name: String,
    var image: TVImage,
    var _links: TVLink
)