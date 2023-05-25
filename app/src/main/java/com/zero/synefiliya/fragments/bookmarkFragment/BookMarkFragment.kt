package com.zero.synefiliya.fragments.bookmarkFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.zero.synefiliya.fragments.dashboardFragment.models.MovieDetail
import com.zero.synefiliya.utils.roomdb.MovieDatabase

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BookMarkFragment : Fragment() {

    @Inject
    lateinit var movieDB: MovieDatabase
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycleScope.launch(Dispatchers.IO) {
            //TODO: CREATE ADAPTER AND ASSIGN TO RecyclerView FROM THIS COROUTINE
            getBookMarkedList()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private suspend fun getBookMarkedList(): List<MovieDetail> {
        return movieDB.movieDao().getAllMovies()
    }
}