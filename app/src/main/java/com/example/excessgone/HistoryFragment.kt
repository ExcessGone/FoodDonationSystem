package com.example.excessgone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.excessgone.Adapter.MyAdapter
import com.example.excessgone.Models.FormViewModel
import com.example.excessgone.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {

    private lateinit var viewModel:FormViewModel
    private lateinit var formRV : RecyclerView
    lateinit var adapter: MyAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        formRV=view.findViewById(R.id.rvForms)
        formRV.layoutManager=LinearLayoutManager(context)
        formRV.setHasFixedSize(true)
        adapter = MyAdapter()
        formRV.adapter=adapter

        viewModel=ViewModelProvider(this).get(FormViewModel::class.java)

        // this will be called when there is update to the list
        viewModel.allForms.observe(viewLifecycleOwner, Observer {
            adapter.updateFormList(it)
        })

    }
}