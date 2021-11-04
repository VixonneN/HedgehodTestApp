package com.example.hedgehodtestapp.fragments.jokes

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hedgehodtestapp.data.Root
import com.example.hedgehodtestapp.databinding.FragmentJokesBinding
import com.example.hedgehodtestapp.fragments.jokes.jokes_recycle_view.JokesAdapter
import kotlinx.android.synthetic.main.content_fragment_jokes.view.*
import kotlin.collections.ArrayList

class JokesFragment : Fragment() {

    private lateinit var mViewModel: JokesFragmentViewModel

    private var _binding: FragmentJokesBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: JokesAdapter

    private lateinit var mObserverList: Observer<Root>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mAdapter = JokesAdapter()
        mRecyclerView = mBinding.root.jokeRecycler
        mObserverList = Observer {
            val list  = it.value
            Log.d(TAG, "initialization: $list")
            mAdapter.jokesList = list
        }
        mRecyclerView.adapter = mAdapter

        mBinding.root.btn_jokes.setOnClickListener {
            val number = mBinding.root.et_count.text.toString()
            if (number.isEmpty()){
                Toast.makeText(context, "Write numbers of jokes", Toast.LENGTH_SHORT).show()
            } else {
                mViewModel.networkModule(number.toInt())
                mViewModel.jokes.observe(this, mObserverList)

            }
        }
        mViewModel = ViewModelProvider(this).get(JokesFragmentViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mViewModel.jokes.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }

    companion object {
        fun newInstance() = JokesFragment()
    }
}


