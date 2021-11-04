package com.example.hedgehodtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hedgehodtestapp.databinding.ActivityMainBinding
import com.example.hedgehodtestapp.fragments.browser.BrowserFragment
import com.example.hedgehodtestapp.fragments.jokes.JokesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding?= null
    private val mBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragment_container, JokesFragment.newInstance(), null)
            .commit()

        bottomNavigation()

    }

    private fun bottomNavigation () {
        val bottomNavigationView : BottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigationJoke -> {
                    loadFragments(JokesFragment.newInstance())
                    true
                }
                R.id.navigationBrowser -> {
                    loadFragments(BrowserFragment.newInstance())
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.navigationJoke -> {
                    loadFragments(JokesFragment.newInstance())

                }
                R.id.navigationBrowser -> {
                    loadFragments(BrowserFragment.newInstance())
                }
            }
        }
    }

    private fun loadFragments(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, null)
            .setReorderingAllowed(true)
            .addToBackStack("")
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        BrowserFragment.newInstance().goBackWebView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}