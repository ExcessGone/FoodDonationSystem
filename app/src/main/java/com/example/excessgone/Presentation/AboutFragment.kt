package com.example.excessgone.Presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.excessgone.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAboutBinding.inflate(layoutInflater)
        val view = binding.root

        // Line 31 - 44 include process of implicit intent,
        // aka, transitioning from fragment to activity.
        binding.productionBtn.setOnClickListener {
            val i = Intent(activity, ProductionActivity::class.java)
            activity?.startActivity(i)
        }

        binding.appearanceBtn.setOnClickListener {
            val i = Intent(activity, AppearanceActivity::class.java)
            activity?.startActivity(i)
        }

        binding.techStackBtn.setOnClickListener {
            val i = Intent(activity, TechStackActivity::class.java)
            activity?.startActivity(i)
        }

        // Inflate the layout for this fragment
        return view

    }


}