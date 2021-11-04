package com.example.hedgehodtestapp.fragments.jokes.jokes_recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import com.example.hedgehodtestapp.R
import com.example.hedgehodtestapp.data.Root
import com.example.hedgehodtestapp.data.Value
import kotlinx.android.synthetic.main.item_jokes.view.*

class JokesViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_jokes, parent, false)) {

    fun bind(joke: String){
        (itemView.text_jokes).text = joke
    }
}