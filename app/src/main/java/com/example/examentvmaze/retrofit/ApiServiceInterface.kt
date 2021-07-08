package com.example.examentvmaze.retrofit

import com.example.examentvmaze.constant.AppConstant.PATH_ID
import com.example.examentvmaze.constant.AppConstant.QUERY_COUNTRY
import com.example.examentvmaze.constant.AppConstant.QUERY_DATE
import com.example.examentvmaze.constant.AppConstant.QUERY_Q
import com.example.examentvmaze.constant.AppConstant.ROUTE_DETAIL
import com.example.examentvmaze.constant.AppConstant.ROUTE_DETAIL_CAST
import com.example.examentvmaze.constant.AppConstant.ROUTE_SCHEDULE
import com.example.examentvmaze.constant.AppConstant.ROUTE_SEARCH
import com.example.examentvmaze.retrofit.model.TVCast
import com.example.examentvmaze.retrofit.model.TVMovie
import com.example.examentvmaze.retrofit.model.TVShow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET(ROUTE_SCHEDULE)
    suspend fun getMovies(
        @Query(QUERY_COUNTRY) country: String,
        @Query(QUERY_DATE) dateNow: String?
    ): Response<ArrayList<TVMovie>>

    @GET(ROUTE_SEARCH)
    suspend fun getSearchMovies(
        @Query(QUERY_Q) textSearch: String
    ): Response<ArrayList<TVMovie>>

    @GET(ROUTE_DETAIL)
    suspend fun getDetailMovie(
        @Path(PATH_ID) id: Int
    ): Response<TVShow>

    @GET(ROUTE_DETAIL_CAST)
    suspend fun getDetailMovieCast(
        @Path(PATH_ID) id: Int
    ): Response<ArrayList<TVCast>>

}
