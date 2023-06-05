package com.zero.synefiliya.fragments.dashboardFragment.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.zero.synefiliya.fragments.dashboardFragment.models.ListResponse
import com.zero.synefiliya.fragments.dashboardFragment.models.MovieDetail
import com.zero.synefiliya.fragments.dashboardFragment.repository.DashboardRepository
import com.zero.synefiliya.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _movieList: MutableLiveData<NetworkResult<ListResponse>> =
        MutableLiveData()
    val movieList: LiveData<NetworkResult<ListResponse>> get()  = _movieList

    private val _movieListQuery: MutableLiveData<NetworkResult<ListResponse>> =
        MutableLiveData()
    val movieListQuery: LiveData<NetworkResult<ListResponse>> get()  = _movieListQuery

    fun fetchMovieList(pageNumber: Int) {
        viewModelScope.launch{
           if(movieList.value == null){
               _movieList.postValue(NetworkResult.Loading())
               repository.getMovieList(pageNumber).collect {result ->
                   _movieList.postValue(result)
               }
           }
        }
    }

    fun fetchMovieListQuery(pageNumber: Int,query:String){
        viewModelScope.launch {

                _movieList.postValue(NetworkResult.Loading())
                repository.getMovieListQuery(pageNumber,query).collect {result ->
                    _movieList.postValue(result)
                }

        }
    }
}