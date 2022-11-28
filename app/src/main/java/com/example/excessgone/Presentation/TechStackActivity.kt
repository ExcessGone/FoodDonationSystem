package com.example.excessgone.Presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.excessgone.databinding.ActivityTechstackBinding

class TechStackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTechstackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTechstackBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}