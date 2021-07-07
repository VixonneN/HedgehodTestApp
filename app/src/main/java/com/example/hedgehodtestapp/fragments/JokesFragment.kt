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
import com.example.hedgehodtestapp.network.Root
import com.example.hedgehodtestapp.recycleView.JokesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class JokesFragment : Fragment() {

    private var jokesModelData: ArrayList<String> = ArrayList()
    private var recyclerView: RecyclerView? = null

    private var editText: EditText? = null
    private var buttonGetRequest: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            jokesModelData = savedInstanceState.getStringArrayList("jokesRecycleView")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_jokes, container, false)
        recyclerView = view.findViewById(R.id.jokeRecycler)
        editText = view.findViewById(R.id.et_count)
        buttonGetRequest = view.findViewById(R.id.btn_jokes)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = JokesAdapter(jokesModelData)
        buttonGetRequest?.setOnClickListener {
            jokesModelData.clear()

            val numberText: String = editText?.text.toString()

            if (numberText.equals("") || numberText == null){
                Toast.makeText(context, "write smth", Toast.LENGTH_SHORT).show()
            } else {
                val number: Int = Integer.parseInt(numberText)
                networkModule(number)
            }
        }
    }

    private fun networkModule(number: Int) {
        val networkModule = NetworkModule()
        networkModule.api().getSomeJokes(number).enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                val body: Root? = response.body()
                Log.d(TAG, body.toString())
                var i = 0
                while (i < number) {
                    val data: String = body!!.value[i].joke
                    jokesModelData.add(data)
                    i++
                }
                recyclerView!!.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                Toast.makeText(context, "Jokes can't find Chuck Norris", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("jokesRecycleView", jokesModelData)
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