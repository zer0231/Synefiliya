package com.zero.synefiliya.fragments.detailFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.zero.synefiliya.databinding.FragmentDetailBinding
import com.zero.synefiliya.fragments.detailFragment.adapters.CarouselAdapter
import com.zero.synefiliya.fragments.detailFragment.models.MovieDetailAdditional
import com.zero.synefiliya.fragments.detailFragment.viewmodel.MovieDetailViewModel
import com.zero.synefiliya.utils.ModalBottomSheet
import com.zero.synefiliya.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailVM: MovieDetailViewModel by viewModels()
    private val TAG = "DetailFragment"
    private val args: DetailFragmentArgs by navArgs()
    private var movieDetail: MutableLiveData<MovieDetailAdditional> = MutableLiveData()
    private var carouselUrls = ArrayList<String>()
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        Log.d(TAG, args.movieId.toString())

        detailVM.fetchMovieDetail(args.movieId)
        detailVM.movieDetail.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    Log.d(TAG, "LOADING")
                }

                is NetworkResult.Success -> {
                    movieDetail.value = result.data?.data?.movie!!
                    carouselUrls.add(movieDetail.value!!.mediumScreenshotImage1.toString())
                    carouselUrls.add(movieDetail.value!!.mediumScreenshotImage2.toString())
                    carouselUrls.add(movieDetail.value!!.mediumScreenshotImage3.toString())

                    carouselAdapter = CarouselAdapter(requireContext(), carouselUrls)
                    binding.backgroundVp2.adapter = carouselAdapter
                    binding.backgroundVp2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                    binding.titleTv.text = movieDetail.value!!.title
                    binding.ratingTv.text = movieDetail.value!!.rating.toString()
                    var genreString = ""
                    for(i in movieDetail.value!!.genres.indices ){
                        genreString += "${movieDetail.value!!.genres[i]},"
                    }
                    binding.genreTv.text = genreString.dropLast(1)
                    Log.d(TAG, result.data.toString())
                }

                is NetworkResult.Error -> {
                    Log.d(TAG, result.message.toString())
                }
            }
        }

        binding.descIb.setOnClickListener {
            showBottomSheet()
        }

        return binding.root
    }


    private fun showBottomSheet() {
        val modalBottomSheet = ModalBottomSheet()
        movieDetail.observe(viewLifecycleOwner) {
            if (it.descriptionFull != null) {
                modalBottomSheet.setDescription(it.descriptionFull!!)
            } else {
                modalBottomSheet.setDescription("Not found")
            }

        }
        modalBottomSheet.show(parentFragmentManager, modalBottomSheet.tag)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}