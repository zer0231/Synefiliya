package com.zero.synefiliya.utils

import com.zero.synefiliya.utils.Constants.Companion.DETAIL
import com.zero.synefiliya.utils.Constants.Companion.LIST
import com.zero.synefiliya.utils.Constants.Companion.SUGGESTIONS
import javax.inject.Inject

class RemoteDataSource @Inject constructor(

    private val userSharedPreference: SharedPreferenceUtil,
    private val apiService: APIService
) {
    suspend fun getMoviesList() = apiService.getMoviesList(LIST)
    suspend fun getMovieDetail(movieId: Int) = apiService.getMovieDetail(DETAIL + movieId)
    suspend fun getSimilarSuggestion(movieId: Int) =
        apiService.getSimilarSuggestion(SUGGESTIONS + movieId)

}
