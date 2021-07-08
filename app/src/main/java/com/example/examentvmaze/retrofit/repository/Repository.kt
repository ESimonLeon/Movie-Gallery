package com.example.examentvmaze.retrofit.repository

import com.example.examentvmaze.constant.AppConstant.COUNTRY
import com.example.examentvmaze.retrofit.ApiServiceInterface

class Repository(private val apiServiceInterface: ApiServiceInterface) {
    suspend fun getMovies(dateNow: String?) = apiServiceInterface.getMovies(COUNTRY, dateNow)
    suspend fun getSearchMovies(textSearch: String?) = apiServiceInterface.getSearchMovies(textSearch!!)
    suspend fun getDetailMovie(id: Int) = apiServiceInterface.getDetailMovie(id)
    suspend fun getDetailCastMovie(id: Int) = apiServiceInterface.getDetailMovieCast(id)
}
