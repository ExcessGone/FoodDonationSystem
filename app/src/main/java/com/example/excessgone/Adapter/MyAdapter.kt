package com.example.excessgone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.excessgone.Models.Forms
import com.example.excessgone.R

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private val formList = ArrayList<Forms>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val itemView = LayoutInflater.from(parent.context).inflate(
          R.layout.form_item,
          parent, false
      )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = formList[position]
        holder.shelterName.text=currentItem.shelterName
        holder.phoneNumber.text=currentItem.phoneNumber
        holder.address.text=currentItem.address
        holder.foodType.text=currentItem.foodType
    }

    override fun getItemCount(): Int {
        return formList.size
    }

    fun updateFormList(formList : List<Forms>){
        this.formList.clear()
        this.formList.addAll(formList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val shelterName : TextView = itemView.findViewById(R.id.NameField)
        val phoneNumber : TextView = itemView.findViewById(R.id.phoneField)
        val address : TextView = itemView.findViewById(R.id.addressField)
        val foodType : TextView = itemView.findViewById(R.id.foodField)

    }
}