package com.example.hedgehodtestapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hedgehodtestapp.R
import com.example.hedgehodtestapp.recycleView.JokesAdapter
import com.example.hedgehodtestapp.recycleView.JokesModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class JokesFragment : Fragment() {

    var recyclerViewData: ArrayList<JokesModel> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private val recyclerViewAdapter : JokesAdapter by lazy {
        JokesAdapter(recyclerViewData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jokes, container, false)
        recyclerView = view.findViewById(R.id.jokeRecycler)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = recyclerViewAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            JokesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}