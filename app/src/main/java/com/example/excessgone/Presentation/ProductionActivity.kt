package com.example.excessgone.Presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.excessgone.databinding.ActivityProductionBinding

class ProductionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}