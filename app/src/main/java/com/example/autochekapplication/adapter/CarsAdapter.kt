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
import com.example.autochekapplication.dataclass.cars.Result

class CarsAdapter : RecyclerView.Adapter<CarsAdapter.MyViewHolder>() {
    inner class MyViewHolder(var carsListItem : View) : RecyclerView.ViewHolder(carsListItem) {
        var title : TextView = carsListItem.findViewById(R.id.titleTv)
        var condition : TextView = carsListItem.findViewById(R.id.sellingConditionTv)
        var price : TextView = carsListItem.findViewById(R.id.priceTv)
        var imageView : ImageView = carsListItem.findViewById(R.id.imageTv)
        var rating : TextView = carsListItem.findViewById(R.id.ratingTV)
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var cars : List<Result>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cars_list_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentCar = cars[position]

        holder.imageView.load(currentCar.imageUrl){
            crossfade(true)
            crossfade(1000)
            placeholder(R.drawable.placeholderimage)
        }

        holder.title.text = currentCar.title
        holder.condition.text = currentCar.sellingCondition
        holder.price.text = currentCar.marketplacePrice.toString()
        holder.rating.text = currentCar.gradeScore?.toInt().toString()

        holder.itemView.setOnClickListener {
            listItemClickListener?.let {
                it(currentCar)
            }
        }
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    //Implement onclick listeners
    private var listItemClickListener : ((Result) -> Unit)? = null

    fun setListItemClickListener(listener: (Result) -> Unit){
        listItemClickListener = listener
    }
}