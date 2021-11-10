package com.example.autochekapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.autochekapplication.R
import com.example.autochekapplication.dataclass.makelist.Make

class MakeAdapter : RecyclerView.Adapter<MakeAdapter.MyViewHolder>() {
    inner class MyViewHolder(var makeListItem : View) : RecyclerView.ViewHolder(makeListItem) {
        var brandImage : ImageView = makeListItem.findViewById(R.id.imageViewId)
        var brandName : TextView = makeListItem.findViewById(R.id.textViewId)
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Make>(){
        override fun areItemsTheSame(oldItem: Make, newItem: Make): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Make, newItem: Make): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var make : List<Make>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.make_list_list_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMake = make[position]

        holder.brandImage.load(currentMake.imageUrl){
            crossfade(true)
            crossfade(1000)
        }

        holder.brandName.text = currentMake.name
    }

    override fun getItemCount(): Int {
        return make.size
    }
}