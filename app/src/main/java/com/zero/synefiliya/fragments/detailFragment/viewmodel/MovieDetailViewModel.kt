package com.zero.synefiliya.fragments.detailFragment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zero.synefiliya.fragments.detailFragment.models.DetailResponse
import com.zero.synefiliya.fragments.detailFragment.models.MovieDetailAdditional
import com.zero.synefiliya.fragments.detailFragment.repository.MovieDetailRepository
import com.zero.synefiliya.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieDetailRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _movieDetail: MutableLiveData<NetworkResult<DetailResponse>> =
        MutableLiveData()
    val movieDetail: LiveData<NetworkResult<DetailResponse>> = _movieDetail

    private val _bookmark: MutableLiveData<Boolean> =
        MutableLiveData()
    val bookmark: LiveData<Boolean> = _bookmark


    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieDetail.postValue(NetworkResult.Loading())
            repository.getMovieDetail(movieId).collect {
                _movieDetail.postValue(it)
            }
        }
    }

    fun addToBookMark(movie: MovieDetailAdditional) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToBookMark(movie)
        }
    }

    fun removeFromBookMark(movie: MovieDetailAdditional) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeToBookMark(movie)
        }
    }

    fun isBookMarked(movieId: Int) {
        viewModelScope.launch {
            _bookmark.postValue(repository.isBookMarked(movieId))
        }
    }

}