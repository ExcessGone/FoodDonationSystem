// Attached: FormAdapter.kt
// =============================================================================
//
// Programmer: Nour Siwar
// Class: Android Development
// Instructor: CodePath
//
// =============================================================================
// Program: FormAdapter class
// =============================================================================
// Description:
// Adapter is part of the recyclerview process. This class is responsible for
// retrieving the items, and loading up on the UI of the History Fragment.
// =============================================================================

package com.example.excessgone

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.excessgone.Form_Models.Forms

class FormAdapter(private val formList:ArrayList<Forms>) : RecyclerView.Adapter<FormAdapter.MyViewHolder>(){

    // declare variables
    private lateinit var mListener: onItemClickListener

    // this interface is for transitioning to detailed part of the form
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.form_item,
            parent, false
        )
        return MyViewHolder(itemView, mListener)
    }

    // this function takes in the ViewHolder class and the index position
    // of the arrayList

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // currentItem signifies the value of the current index in the arrayList
        val currentItem = formList[position]

        // this is the data being transferred each time the function gets called
        holder.shelterNameTextView.text=currentItem.shelterName
    }

    // returning the size of the arrayList
    override fun getItemCount(): Int {
        return formList.size
    }

    // updating the List for new information
    fun updateFormList(formList : List<Forms>){
        this.formList.clear()
        this.formList.addAll(formList)
        notifyDataSetChanged()
    }

    //this function holds the data that will be transferred to the recylcerview.
    // in this case, the data used here is just the shelter name.
    class MyViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){


         val shelterNameTextView: TextView = itemView.findViewById(R.id.nameField)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(absoluteAdapterPosition)
            }
        }

    }
}