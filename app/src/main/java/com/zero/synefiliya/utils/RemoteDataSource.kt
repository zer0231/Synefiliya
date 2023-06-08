package com.zero.synefiliya.utils

import com.zero.synefiliya.utils.Constants.Companion.DETAIL
import com.zero.synefiliya.utils.Constants.Companion.LIST
import com.zero.synefiliya.utils.Constants.Companion.SUGGESTIONS
import javax.inject.Inject

class RemoteDataSource @Inject constructor(

    private val userSharedPreference: SharedPreferenceUtil,
    private val apiService: APIService
) {
    suspend fun getMoviesList(pageNumber: Int) =  apiService.getMoviesList("$LIST$pageNumber")
    suspend fun getMoviesListQuery(pageNumber: Int,searchQuery:String) = apiService.getMoviesList("$LIST$pageNumber&query_term=$searchQuery")
    suspend fun getMovieDetail(movieId: Int) = apiService.getMovieDetail(DETAIL + movieId)
    suspend fun getSimilarSuggestion(movieId: Int) =
        apiService.getSimilarSuggestion(SUGGESTIONS + movieId)

}
