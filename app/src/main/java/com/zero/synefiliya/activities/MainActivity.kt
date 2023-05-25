package com.zero.synefiliya.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zero.synefiliya.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}