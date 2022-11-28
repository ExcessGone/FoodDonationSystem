package com.example.excessgone

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.excessgone.Form_Models.FormViewModel
import com.example.excessgone.Form_Models.Forms
import com.example.excessgone.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {


// declare variables
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel:FormViewModel
    lateinit var adapter: FormAdapter
    private var formList = arrayListOf<Forms>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHistoryBinding.inflate(layoutInflater)
        val view = binding.root

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


        binding.rvForms.layoutManager=LinearLayoutManager(context)
        binding.rvForms.setHasFixedSize(true)
        adapter = FormAdapter(formList)
        binding.rvForms.adapter=adapter

        adapter.setOnItemClickListener(object : FormAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
               val intent = Intent(activity, DetailActivity::class.java)

                // put extras
                intent.putExtra("name", formList[position].shelterName)
                intent.putExtra("food", formList[position].foodType)
                intent.putExtra("phone", formList[position].phoneNumber)
                intent.putExtra("address", formList[position].address)
                startActivity(intent)
            }

        })

        viewModel=ViewModelProvider(this).get(FormViewModel::class.java)

        // this will be called when there is update to the list
        viewModel.allForms.observe(viewLifecycleOwner, Observer {
            adapter.updateFormList(it)
        })

    }
}