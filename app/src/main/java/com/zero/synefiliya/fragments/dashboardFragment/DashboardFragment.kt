package com.zero.synefiliya.fragments.dashboardFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.zero.synefiliya.R
import com.zero.synefiliya.databinding.FragmentDashboardBinding
import com.zero.synefiliya.fragments.dashboardFragment.adapters.MoviesAdapter
import com.zero.synefiliya.fragments.dashboardFragment.models.MovieDetail
import com.zero.synefiliya.fragments.dashboardFragment.viewModels.DashboardViewModel
import com.zero.synefiliya.utils.LoadingHandler
import com.zero.synefiliya.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val TAG = "DashboardFragment"
    private val dashboardVm by viewModels<DashboardViewModel>()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
//    private lateinit var adapter: MoviesAdapter

    private var movieList: MutableList<MovieDetail> = mutableListOf()
    private lateinit var loading: LoadingHandler
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.searchView.setupWithSearchBar(binding.searchbarSb)
        loading = LoadingHandler(requireContext(), binding.parentConstraint)
        getMoviesList(null)
        binding.searchView.editText.setOnEditorActionListener { _, _, _ -> //v, actionId, event
            binding.searchView.hide()
            binding.searchbarSb.text = binding.searchView.text
            getMoviesList(binding.searchView.text.toString())
            Log.d(TAG, binding.searchView.text.toString())
            false
        }
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }


    private fun getMoviesList(filterQuery: String?) {
        if (filterQuery == null) {

            dashboardVm.fetchMovieList(1)
            dashboardVm.movieList.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        movieList = result.data?.data!!.movies
                        val adapter = MoviesAdapter(movieList, requireContext())
                        binding.movieListRv.adapter = adapter
                        binding.movieListRv.layoutManager = GridLayoutManager(requireContext(), 2)
                        loading.stopAnimation()
                        Log.d(TAG, movieList.size.toString())
                    }

                    is NetworkResult.Error -> {
                        loading.stopAnimation()
                        findNavController().navigate(R.id.errorFragment)
                    }

                    is NetworkResult.Loading -> {
                        Log.d(TAG, "Loading")
                        loading.startAnimation()

                    }
                }
            }
        } else {
//            filterQuery = "star"
            dashboardVm.fetchMovieListQuery(1, filterQuery)
            dashboardVm.movieListQuery.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        movieList = result.data?.data!!.movies
                        val adapter = MoviesAdapter(movieList, requireContext())
                        binding.movieListRv.adapter = adapter
                        loading.stopAnimation()
                        Log.d(TAG, movieList.size.toString())
                    }

                    is NetworkResult.Error -> {
                        loading.stopAnimation()
                        findNavController().navigate(R.id.errorFragment)
                    }

                    is NetworkResult.Loading -> {
                        Log.d(TAG, "Loading from search")
                        loading.startAnimation()

                    }
                }
            }

        }

    }
}