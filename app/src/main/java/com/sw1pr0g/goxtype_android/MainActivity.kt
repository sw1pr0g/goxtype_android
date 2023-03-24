package com.sw1pr0g.goxtype_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sw1pr0g.goxtype_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainBottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainBottomNavigationView = findViewById(R.id.main_bottom_navigation_view)

        //showFragment(MainHomeFragment(), true)
        mainBottomNavigationView.selectedItemId = 0

        binding.mainBottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {




            }

        }

    }
}

    /*fun showFragment(fragment: Fragment, firstShowing: Boolean) {

        val supportFragmentManager = supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)

        if (!firstShowing) supportFragmentManager.addToBackStack(null)

        supportFragmentManager.commit()

    }*/
