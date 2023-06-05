package com.zero.synefiliya.utils
import com.google.gson.JsonObject
import com.zero.synefiliya.fragments.dashboardFragment.models.ListResponse
import com.zero.synefiliya.fragments.detailFragment.models.DetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface APIService {


    @GET
    suspend fun getMoviesList(@Url url: String): Response<ListResponse>

    @GET
    suspend fun getMovieDetail(@Url url: String): Response<DetailResponse>

    @GET
    suspend fun getSimilarSuggestion(@Url url:String):Response<JsonObject>

}