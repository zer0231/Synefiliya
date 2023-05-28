package com.zero.synefiliya.fragments.detailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zero.synefiliya.databinding.FragmentDetailBinding
import com.zero.synefiliya.utils.ModalBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.descBtn.setOnClickListener {
            showBottomSheet()
        }

        return binding.root
    }

    private fun showBottomSheet() {
        val modalBottomSheet = ModalBottomSheet()
//        modalBottomSheet.setDescription("Nigga~~")
        modalBottomSheet.show(parentFragmentManager, modalBottomSheet.tag)

    }
}