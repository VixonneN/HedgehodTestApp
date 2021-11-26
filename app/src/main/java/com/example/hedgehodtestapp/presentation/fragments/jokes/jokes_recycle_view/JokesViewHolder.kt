package com.example.hedgehodtestapp.presentation.fragments.jokes.jokes_recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hedgehodtestapp.R
import kotlinx.android.synthetic.main.item_jokes.view.*

class JokesViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_jokes, parent, false)) {

    fun bind(joke: String){
        (itemView.text_jokes).text = joke
    }
}