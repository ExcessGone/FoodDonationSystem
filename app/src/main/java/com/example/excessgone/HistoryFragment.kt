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
import androidx.recyclerview.widget.RecyclerView
import com.example.excessgone.Form_Models.FormViewModel
import com.example.excessgone.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {

    private lateinit var viewModel:FormViewModel
    private lateinit var formRV : RecyclerView
    lateinit var adapter: FormAdapter
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
        adapter = FormAdapter()
        formRV.adapter=adapter

        viewModel=ViewModelProvider(this).get(FormViewModel::class.java)

        // this will be called when there is update to the list
        viewModel.allForms.observe(viewLifecycleOwner, Observer {
            adapter.updateFormList(it)
        })

    }
}









/*
package com.example.excessgone

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.excessgone.MyAdapter
import com.example.excessgone.Form_Models.Forms
import com.example.excessgone.databinding.FragmentHistoryBinding
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.Normalizer


class HistoryFragment : Fragment() {

     val imageRef = Firebase.storage.reference

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

        binding.addForm.setOnClickListener {
            val i = Intent(activity, FormActivity::class.java)
            activity?.startActivity(i)
        }

        listFiles()




        return view
    }
  /*  private fun getFormData() {

        DBref = FirebaseDatabase.getInstance().getReference("Forms")

        DBref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (formSnapshot in snapshot.children){


                        val form = formSnapshot.getValue(Normalizer.Form::class.java)
                        formArrayList.add(form!! as Forms)

                    }

                    binding.rvForms.adapter = MyAdapter(formArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }

        private fun listFiles() = CoroutineScope(Dispatchers.IO).launch {
            try{
                val images = imageRef.child("forms").listAll().await()
                val imageUrls = mutableListOf<String>()
                for(image in images.items){
                    val url = image.downloadUrl.await()
                    imageUrls.add(url.toString())
                }
                    withContext(Dispatchers.Main)
                    {
                        val MyAdapter = MyAdapter(imageUrls)
                        rvForms.apply {
                            adapter = MyAdapter
                            layoutManager = LinearLayoutManager(activity)
                        }
                    }
            }
            catch(e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }


}*/