// Attached: HistoryFragment.kt
// =============================================================================
//
// Programmer: Nour Siwar
// Class: Android Development
// Instructor: CodePath
//
// =============================================================================
// Program: HistoryFragment class
// =============================================================================
// Description:
// This class is the fragment of main activity that takes care of past food donated
// to past shelters.
// =============================================================================
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

    // declaring variables
    private lateinit var viewModel:FormViewModel
    lateinit var adapter: FormAdapter
    private lateinit var binding: FragmentHistoryBinding
    private var formList : ArrayList<Forms> = arrayListOf<Forms>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHistoryBinding.inflate(layoutInflater)
        val view = binding.root

        // line 39 - 42 allows the History fragment to move to
        // the form by pressing the add button.
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


        // this is responsible for positioning the items and
        binding.rvForms.layoutManager=LinearLayoutManager(context)

        // because this is true, the adapter's height and width wont
        // change constantly and unnecessarily.
        // This is also better than using requestLayout
        binding.rvForms.setHasFixedSize(true)

        // defining the variable adapter and assigning the layout
        // to the adapter (backend with frontend)
        adapter = FormAdapter(formList)
        binding.rvForms.adapter=adapter

        // this is for the DetailedActivity transition from history
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

        // creating object of ViewModel
        viewModel= ViewModelProvider(this)[FormViewModel::class.java]

        // this will be used when there is update to the list
        viewModel.allForms.observe(viewLifecycleOwner, Observer {
            // callback method for any change in the data.
            adapter.updateFormList(it)
        })

    }
}