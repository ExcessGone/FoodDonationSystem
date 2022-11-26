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

class FormAdapter : RecyclerView.Adapter<FormAdapter.MyViewHolder>(){

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
        holder.shelterNameTextView.text=currentItem.shelterName
    }

    override fun getItemCount(): Int {
        return formList.size
    }

    fun updateFormList(formList : List<Forms>){
        this.formList.clear()
        this.formList.addAll(formList)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

         val shelterNameTextView: TextView = itemView.findViewById(R.id.nameField)

    }
}


/*package com.example.excessgone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.excessgone.R
import kotlinx.android.synthetic.main.form_item.view.*

class MyAdapter(
    private val urls: List<String>
) : RecyclerView.Adapter<MyAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.form_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = urls[position]
        Glide.with(holder.itemView).load(url).into(holder.itemView.foodImage)
    }
}
*/
