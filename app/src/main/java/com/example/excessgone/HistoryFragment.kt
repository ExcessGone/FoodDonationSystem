package com.example.excessgone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.excessgone.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHistoryBinding.inflate(layoutInflater)
        val view = binding.root


        //  Log.i("XXXXXXX", "XOXOXOXOXOXOXO")
        binding.addForm.setOnClickListener {
            val i = Intent(activity, FormActivity::class.java)
            activity?.startActivity(i)
        }


        // Inflate the layout for this fragment
        return view
    }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}