package com.zero.synefiliya.fragments.dashboardFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
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
    private val dashboardVm: DashboardViewModel by viewModels()
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var adapter: MoviesAdapter
    private val binding get() = _binding!!
    private lateinit var loading: LoadingHandler
    private lateinit var movieItemList: MutableLiveData<ArrayList<MovieDetail>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        movieItemList = MutableLiveData()
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.searchView.setupWithSearchBar(binding.searchbarSb)
        binding.searchView.editText.setOnEditorActionListener { v, actionId, event ->
            binding.searchbarSb.text = binding.searchView.text
            false
        }

        getMoviesList(null)
        loading = LoadingHandler(requireContext(), binding.parentConstraint)

        return binding.root
    }

    private fun getMoviesList(filterQuery: String?) {
        dashboardVm.fetchMovieList(1)
        dashboardVm.movieList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    movieItemList.value = result.data?.data?.movies
                    binding.movieListRv.layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = MoviesAdapter(movieItemList.value!!, requireContext())
                    binding.movieListRv.adapter = adapter
                    if (!binding.movieListRv.isComputingLayout || !binding.movieListRv.isAnimating) {
                        loading.stopAnimation()
                    }
                }

                is NetworkResult.Error -> {
                    loading.stopAnimation()
                }

                is NetworkResult.Loading -> {
                    loading.startAnimation()

                }
            }
        }
    }


}