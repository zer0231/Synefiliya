package com.zero.synefiliya.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zero.synefiliya.R
import com.zero.synefiliya.databinding.ModalBottomSheetContentBinding

class ModalBottomSheet : BottomSheetDialogFragment() {
    private var description:String? = null
    private var _binding: ModalBottomSheetContentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ModalBottomSheetContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       if(description!= null){
           binding.descTv.text = description
       }
    }
    fun setDescription(desc: String) {
        description = desc
    }
}