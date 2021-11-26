package com.example.hedgehodtestapp.presentation.fragments.jokes.jokes_recycle_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hedgehodtestapp.data.data_source.Value

class JokesAdapter: RecyclerView.Adapter<JokesViewHolder>() {

    var jokesList: List<Value> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder =
        JokesViewHolder(parent = parent)


    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.bind(jokesList[position].joke)
    }

    override fun getItemCount(): Int = jokesList.size
}