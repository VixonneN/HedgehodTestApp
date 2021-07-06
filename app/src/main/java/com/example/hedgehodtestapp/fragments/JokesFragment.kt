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

//TODO Update RecycleView
class JokesFragment : Fragment() {

    private var jokesModelData: ArrayList<String> = ArrayList()
    private var dataArrayList: ArrayList<String> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private val recyclerViewAdapter: JokesAdapter by lazy {
        JokesAdapter(jokesModelData)
    }

    private var editText: EditText? = null
    private var buttonGetRequest: Button? = null
    private var buttonUpdade: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addArray()
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
        buttonUpdade = view.findViewById(R.id.btn_update)
        return view
    }

    private fun updateRecycler(){
        buttonUpdade?.setOnClickListener {
            var i = 0
            while (i < dataArrayList.size){
                jokesModelData.add(dataArrayList[i])
                i++
            }
            recyclerViewAdapter.replaceAll(jokesModelData)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateRecycler()

        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = JokesAdapter(jokesModelData)
        //TODO принимать в один лист, записывать в другой
        buttonGetRequest?.setOnClickListener {
            val number: Int = Integer.parseInt(editText?.text.toString())

            val networkModule = NetworkModule()
            networkModule.api().getSomeJokes(number).enqueue(object : Callback<Root> {
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    val body: Root? = response.body()
                    Log.d(TAG, body.toString())
                    var i = 0
                    while (i < number) {
                        val data: String = body!!.value[i].joke
                        dataArrayList.add(data)
                        i++
                    }
                    Log.d(TAG, "onResponse: $dataArrayList")
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    Toast.makeText(context, "Jokes can't find Chuck Norris", Toast.LENGTH_SHORT)
                        .show()
                }
            })
//            recyclerViewAdapter.replaceAll(jokesModelData)
        }
    }

    private fun addArray(){
        val networkModule = NetworkModule()
        networkModule.api().getSomeJokes(4).enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                val body: Root? = response.body()
                Log.d(TAG, body.toString())
                var i = 0
                while (i < 4) {
                    val data: String = body!!.value[i].joke
                    jokesModelData.add(data)
                    i++
                }
                Log.d(TAG, "onResponse: $jokesModelData")
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                Toast.makeText(context, "Jokes can't find Chuck Norris", Toast.LENGTH_SHORT)
                    .show()
            }
        })
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