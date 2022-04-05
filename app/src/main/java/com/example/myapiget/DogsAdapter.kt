package com.cursokotlin.retrofitkotlinexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapiget.R
import okhttp3.ResponseBody

class DogsAdapter(
    private val reviewList: MutableList<DogsResponse>
) : RecyclerView.Adapter<DogsAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv,parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bindView(reviewList[position])
    }

    override fun getItemCount(): Int {

        return reviewList.size
    }

    public class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val Nom: TextView = itemView.findViewById(R.id.txtNom2)
        val id: TextView = itemView.findViewById(R.id.txtID)
        fun bindView(postModel: DogsResponse){
            Nom.text = postModel.nombre
            id.text = postModel.id.toString()
        }


    }
}




/*(val images: List<String>
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
}*/