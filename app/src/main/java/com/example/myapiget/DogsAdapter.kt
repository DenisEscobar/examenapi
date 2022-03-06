package com.cursokotlin.retrofitkotlinexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapiget.R

class DogsAdapter (val images: List<String>
) : RecyclerView.Adapter<DogsAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item,holder.IvDog)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }
    override fun getItemCount(): Int {
        return images.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val IvDog: ImageView = view.findViewById(R.id.ivDog)
        fun bind(image: String, ivDog:ImageView) {
            ivDog.fromUrl(image)
            //itemView.ivDog.fromUrl(image)
        }
    }
}