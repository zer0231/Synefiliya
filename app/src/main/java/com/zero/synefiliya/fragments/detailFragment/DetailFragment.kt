package com.zero.synefiliya.fragments.detailFragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.zero.synefiliya.R
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
    private lateinit var bookmarkAdd: Drawable
    private lateinit var bookmarkRemove: Drawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        Log.d(TAG, args.movieId.toString())
        bookmarkAdd = ResourcesCompat.getDrawable(resources, R.drawable.ic_bookmark_add_24, null)!!
        bookmarkRemove =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_bookmark_remove_24, null)!!
        detailVM.fetchMovieDetail(args.movieId)
        detailVM.movieDetail.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    Log.d(TAG, "LOADING")
                }

                is NetworkResult.Success -> {
                    movieDetail.value = result.data?.data?.movie!!
                    detailVM.isBookMarked(movieDetail.value?.id!!)
                    detailVM.bookmark.observe(viewLifecycleOwner){result->
                        if(result==false){
                            binding.bookmarkIb.setImageDrawable(bookmarkAdd)
                        }else{
                            binding.bookmarkIb.setImageDrawable(bookmarkRemove)
                        }
                    }

                    carouselUrls.add(movieDetail.value!!.mediumScreenshotImage1.toString())
                    carouselUrls.add(movieDetail.value!!.mediumScreenshotImage2.toString())
                    carouselUrls.add(movieDetail.value!!.mediumScreenshotImage3.toString())

                    carouselAdapter = CarouselAdapter(requireContext(), carouselUrls)
                    binding.backgroundVp2.adapter = carouselAdapter
                    binding.backgroundVp2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                    binding.titleTv.text = movieDetail.value!!.title
                    binding.ratingTv.text = movieDetail.value!!.rating.toString()
                    var genreString = ""
                    for (i in movieDetail.value!!.genres.indices) {
                        genreString += "${movieDetail.value!!.genres[i]},"
                    }
                    binding.genreTv.text = genreString.dropLast(1)
                    Log.d(TAG, result.data.toString())
                }

                is NetworkResult.Error -> {
                    findNavController().navigate(R.id.errorFragment)
                }

            }
        }

        binding.bookmarkIb.setOnClickListener {
            if (binding.bookmarkIb.drawable == bookmarkRemove) {
                detailVM.removeFromBookMark(movieDetail.value!!)
                binding.bookmarkIb.setImageDrawable(bookmarkAdd)
                val snack =
                    Snackbar.make(it, "Removed from bookmark", Snackbar.LENGTH_SHORT)
                snack.setAction("Undo") {
                    detailVM.addToBookMark(movieDetail.value!!)
                }
                snack.show()
            } else {
                binding.bookmarkIb.setImageDrawable(bookmarkRemove)
                detailVM.addToBookMark(movieDetail.value!!)
                val snack =
                    Snackbar.make(it, "Added to bookmark", Snackbar.LENGTH_SHORT)
                snack.setAction("Undo") {
                    detailVM.removeFromBookMark(movieDetail.value!!)
                }
                snack.show()
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