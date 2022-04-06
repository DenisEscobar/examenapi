package com.example.myapiget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val reviewList: MutableList<ResponseData>) : RecyclerView.Adapter<Adapter.MyViewHolder>(){

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

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val Nom: TextView = itemView.findViewById(R.id.txtNom2)
        fun bindView(postModel: ResponseData){
            Nom.text = postModel.nom
        }


    }
}