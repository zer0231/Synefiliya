package com.zero.synefiliya.fragments.detailFragment.repository

import com.zero.synefiliya.fragments.detailFragment.models.DetailResponse
import com.zero.synefiliya.fragments.detailFragment.models.MovieDetailAdditional
import com.zero.synefiliya.utils.APIResponse
import com.zero.synefiliya.utils.NetworkResult
import com.zero.synefiliya.utils.RemoteDataSource
import com.zero.synefiliya.utils.roomdb.MovieDao
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class MovieDetailRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val movieDao: MovieDao
) :
    APIResponse() {

    suspend fun getMovieDetail(movieId: Int): Flow<NetworkResult<DetailResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getMovieDetail(movieId) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addToBookMark(movie: MovieDetailAdditional) {
        movieDao.insertMovie(movie)
    }

    suspend fun removeToBookMark(movie: MovieDetailAdditional) {
        movieDao.deleteMovie(movie)
    }
    suspend fun isBookMarked(movieId: Int): List<MovieDetailAdditional> {
      return movieDao.isBookmarked(movieId)
    }

}