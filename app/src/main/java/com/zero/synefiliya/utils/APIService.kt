package com.zero.synefiliya.utils

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface APIService {


    @GET
    suspend fun getMoviesList(@Url url: String): Response<JsonObject>

    @GET
    suspend fun getMovieDetail(@Url url: String): Response<JsonObject>

    @GET
    suspend fun getSimilarSuggestion(@Url url:String):Response<JsonObject>

}