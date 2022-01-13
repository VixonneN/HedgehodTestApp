package com.example.hedgehodtestapp.presentation.fragments.jokes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hedgehodtestapp.databinding.FragmentJokesBinding
import com.example.hedgehodtestapp.domain.entity.RootEntity
import com.example.hedgehodtestapp.presentation.fragments.jokes.jokes_recycle_view.JokesAdapter
import kotlinx.android.synthetic.main.content_fragment_jokes.view.*

class JokesFragment : Fragment() {

    private val mViewModel: JokesFragmentViewModel by lazy {
        ViewModelProvider(this, JokesFragmentViewModelFactory())[JokesFragmentViewModel::class.java]
    }

    private var _binding: FragmentJokesBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: JokesAdapter

    private lateinit var mObserverList: Observer<RootEntity>

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
        exceptionMessage()
    }

    private fun exceptionMessage(){
        mViewModel.exception.observe(viewLifecycleOwner) { exceptionMessage ->
            Toast.makeText(context, exceptionMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initialization() {
        mAdapter = JokesAdapter()
        mRecyclerView = mBinding.root.jokeRecycler
        mObserverList = Observer {
            val list = it.value
            mAdapter.jokesList = list
        }
        mRecyclerView.adapter = mAdapter

        mBinding.root.btn_jokes.setOnClickListener {
            val number = mBinding.root.et_count.text.toString()
            mViewModel.jokesRequest(number)
            mViewModel.jokes.observe(viewLifecycleOwner, mObserverList)
        }
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


