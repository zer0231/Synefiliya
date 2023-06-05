package com.zero.synefiliya.fragments.dashboardFragment.repository


import com.zero.synefiliya.fragments.dashboardFragment.models.ListResponse
import com.zero.synefiliya.utils.APIResponse
import com.zero.synefiliya.utils.NetworkResult
import com.zero.synefiliya.utils.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class DashboardRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    APIResponse() {
    suspend fun getMovieListQuery(pageNumber: Int,searchQuery:String): Flow<NetworkResult<ListResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getMoviesListQuery(pageNumber,searchQuery) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieList(pageNumber: Int): Flow<NetworkResult<ListResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getMoviesList(pageNumber) })
        }.flowOn(Dispatchers.IO)
    }
}