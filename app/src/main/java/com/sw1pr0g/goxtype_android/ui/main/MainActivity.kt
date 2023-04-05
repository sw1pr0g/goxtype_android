package com.sw1pr0g.goxtype_android.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.databinding.ActivityMainBinding
import com.sw1pr0g.goxtype_android.ui.Component

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private val component = Component(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //component.showFragment(MainHomeFragment())
        //binding.mainBottomNavigationView.selectedItemId = 0

        /*binding.mainBottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.home -> component.showFragment(MainHomeFragment(), false)
                R.id.trainer -> component.showFragment(MainTrainerFragment(), false)
                R.id.notifications -> component.showFragment(MainNotificationsFragment(), false)
                R.id.profile -> component.showFragment(MainProfileFragment(), false)
            }
            true
        }*/

    }

}


