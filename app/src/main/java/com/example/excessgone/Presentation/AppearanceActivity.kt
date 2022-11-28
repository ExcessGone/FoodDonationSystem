package com.example.excessgone.Presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.excessgone.databinding.ActivityAppearanceBinding

class AppearanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppearanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppearanceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}