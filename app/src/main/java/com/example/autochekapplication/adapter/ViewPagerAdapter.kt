package com.example.autochekapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.autochekapplication.R
import com.example.autochekapplication.dataclass.carmedia.CarMedia

class ViewPagerAdapter(private val carMediaImages:List<CarMedia>) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {
    inner class MyViewHolder(var carMediaView : View) : RecyclerView.ViewHolder(carMediaView) {
        var imageView : ImageView = carMediaView.findViewById(R.id.viewPagerImageViewId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_layout, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentImage = carMediaImages[position]

        holder.imageView.load(currentImage.url)
    }

    override fun getItemCount(): Int {
        return carMediaImages.size
    }
}