package com.example.excessgone.Repository

import androidx.lifecycle.MutableLiveData
import com.example.excessgone.Form_Models.Forms
import com.google.firebase.database.*
//This class is part of the process of retrieving firebase data into recylcerview
class FormRepository {

    // Forms is the parent node
    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Forms")

    // getting single instance for the whole application at given point
    @Volatile private var INSTANCE : FormRepository ?= null

    // this function makes sure the instance is null,
    // then it will return a new instance. Otherwise,
    // if instance not null, then it will return the
    // same instance.
    fun getInstance() : FormRepository{
        return INSTANCE ?: synchronized(this){
            val instance = FormRepository()
            INSTANCE = instance
            instance
        }
    }

    // this function will contain list of forms
    fun loadForms(formList : MutableLiveData<List<Forms>>){

        // addValueEventListener method is called
        // whenever there is a change in the data,
        // whether it is adding or deleting data.
        databaseReference.addValueEventListener(object : ValueEventListener{

            // this method is called whenever there is change
            // in data
            // Any time you read data from the Database, you receive the data as a DataSnapshot .
            override fun onDataChange(snapshot: DataSnapshot) {
                try{

                    val form_list : List<Forms> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Forms::class.java)!!
                    }

                    formList.postValue(form_list)
                }catch(e:Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}