package com.example.piedrasnaturales.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.piedrasnaturales.R
import com.example.piedrasnaturales.model.PiedrasItem
import com.example.piedrasnaturales.ui.FirstFragment
import kotlinx.android.synthetic.main.joyas.view.*

class JoyasAdapter(var mPassJoyas: FirstFragment): RecyclerView.Adapter<JoyasAdapter.TaskViewHolder>() {

    private var dataList = emptyList<PiedrasItem>()

    fun updateListJoyas(mDataList: List<PiedrasItem>){

        dataList = mDataList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val mimagen = itemView.image
        val mproducto = itemView.nombre
        val mprecio = itemView.precio
        val itemview = itemView.setOnClickListener(this)

        override fun onClick(v: View?){
            mPassJoyas.passJoyas(dataList[adapterPosition])
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoyasAdapter.TaskViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.joyas, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JoyasAdapter.TaskViewHolder, position: Int) {

        val mJoya = dataList[position]
        holder.mproducto.text = mJoya.nombreProducto
        holder.mprecio.text = mJoya.precio
        Glide.with(holder.itemView.context)
            .load(mJoya.image)
            .into(holder.mimagen)
    }

    override fun getItemCount() = dataList.size

    interface Joyas{
        fun passJoyas(mJoyas: PiedrasItem)
    }
}



