package com.example.hedgehodtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.bind
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hedgehodtestapp.fragments.BrowserFragment
import com.example.hedgehodtestapp.fragments.JokesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation()
    }

    private fun bottomNavigation () {
        val bottomNavigationView : BottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigationJoke -> {
                    Toast.makeText(this, "joke", Toast.LENGTH_SHORT).show()
                    loadFragments(JokesFragment.newInstance())
                    true
                }
                R.id.navigationBrowser -> {
                    Toast.makeText(this, "browser", Toast.LENGTH_SHORT).show()
                    loadFragments(BrowserFragment.newInstance())
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.navigationJoke -> {
                    Toast.makeText(this, "joke", Toast.LENGTH_SHORT).show()
                    loadFragments(JokesFragment.newInstance())

                }
                R.id.navigationBrowser -> {
                    Toast.makeText(this, "browser", Toast.LENGTH_SHORT).show()
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
}