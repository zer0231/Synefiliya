package com.zero.synefiliya.fragments.errorFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zero.synefiliya.databinding.FragmentErrorBinding

class ErrorFragment : Fragment() {

    private val TAG = "ErrorFragment"
    private var _binding: FragmentErrorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorBinding.inflate(inflater, container, false)
        binding.errorTv.text = "Sorry an error has occurred, Please try again later"
        binding.tryAgainBtn.setOnClickListener {
            Log.d(TAG,"REtry pressed")
            findNavController().navigateUp()
        }
        return binding.root
    }
}