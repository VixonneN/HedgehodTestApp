package com.example.hedgehodtestapp.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hedgehodtestapp.R

class JokesAdapter internal constructor(private var dataClassJokes: ArrayList<String>) :
    RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_jokes, parent, false)
        return JokesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val model : String = dataClassJokes[position]
        holder.joke.text = model
    }

    override fun getItemCount(): Int {
        return dataClassJokes.size
    }

    fun replaceAll(newItems : List<String>){
        val currentSize = dataClassJokes.size
        dataClassJokes.clear()
        dataClassJokes.addAll(newItems)
        notifyItemRangeRemoved(0, currentSize)
        notifyItemRangeInserted(0, newItems.size)
//        notifyDataSetChanged()
    }


    class JokesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val joke: TextView = itemView.findViewById(R.id.text_jokes)
    }
}