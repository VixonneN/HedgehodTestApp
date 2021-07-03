package com.example.hedgehodtestapp.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hedgehodtestapp.R
import com.example.hedgehodtestapp.network.NetworkModule
import com.example.hedgehodtestapp.network.Value
import com.example.hedgehodtestapp.recycleView.JokesAdapter
import com.example.hedgehodtestapp.recycleView.JokesModel
import kotlinx.serialization.serializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.Collections.replaceAll
import kotlin.collections.ArrayList

class JokesFragment : Fragment() {

    var recyclerViewData: ArrayList<JokesModel> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private val recyclerViewAdapter: JokesAdapter by lazy {
        JokesAdapter(recyclerViewData)
    }
    private var editText: EditText? = null
    private var button: Button? = null

    private var data: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_jokes, container, false)
        recyclerView = view.findViewById(R.id.jokeRecycler)
        editText = view.findViewById(R.id.et_count)
        button = view.findViewById(R.id.btn_jokes)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = recyclerViewAdapter

        buttonClick()
    }


    private fun searchJokes(number: Int) {
        val networkModule = NetworkModule()
        networkModule.api().getSomeJokes(number).enqueue(object : Callback<Value> {
            override fun onResponse(call: Call<Value>, response: Response<Value>) {
                var body: Value? = response.body()
                Toast.makeText(context, body.toString(), Toast.LENGTH_SHORT).show()
                Log.d(TAG, body.toString())
                Log.d(TAG, "onResponse: here we go")
                //body is null
//                if (body != null){
//
//                        data = body.joke[0].toString()
//                        recyclerViewData.add(createModel(data!!))
//
//
//                    recyclerViewAdapter.replaceAll(recyclerViewData)
//
//                }
            }

            override fun onFailure(call: Call<Value>, t: Throwable) {
                t.message?.let { Log.d(TAG, it) }
            }

        } )

    }

    private fun createModel(jokes: String): JokesModel {
        val jokesModel = JokesModel(jokes)
        setOf(jokes)
        return jokesModel
    }

    private fun buttonClick() {
        button?.setOnClickListener {
            val number: Int = Integer.parseInt(editText?.text.toString())
            searchJokes(number)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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

