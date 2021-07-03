package com.example.hedgehodtestapp.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hedgehodtestapp.R

class JokesAdapter internal constructor(private val dataClassJokes: ArrayList<JokesModel>) :
    RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesAdapter.JokesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_jokes, parent, false)
        return JokesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JokesAdapter.JokesViewHolder, position: Int) {
        val model : JokesModel = dataClassJokes[position]
        holder.joke.text = model.jokes
    }

    override fun getItemCount(): Int {
        return dataClassJokes.size
    }

    public fun replaceAll(newItems : List<JokesModel>){
        this.dataClassJokes.clear()
        this.dataClassJokes.addAll(newItems)
        notifyDataSetChanged()
    }


    class JokesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val joke: TextView = itemView.findViewById(R.id.text_jokes)
    }
}