package com.zero.synefiliya.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import com.zero.synefiliya.databinding.ActivityStarterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarterActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityStarterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityStarterBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}