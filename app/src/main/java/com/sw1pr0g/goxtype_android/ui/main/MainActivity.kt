package com.sw1pr0g.goxtype_android.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.auth0.android.jwt.JWT
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.databinding.ActivityMainBinding
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback
import com.sw1pr0g.goxtype_android.ui.main.profile.MainProfileFragment
import com.sw1pr0g.goxtype_android.utils.SessionManager

class MainActivity : AppCompatActivity(), ShowFragmentCallback {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(MainHomeFragment(), true)
        binding.mainBottomNavigationView.selectedItemId = 0

        binding.mainBottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.home -> showFragment(MainHomeFragment(), false)
                R.id.trainer -> showFragment(MainTrainerFragment(), false)
                R.id.notifications -> showFragment(MainNotificationsFragment(), false)
                R.id.profile -> showFragment(MainProfileFragment(), false)
            }
            true
        }

    }

    override fun showFragment(fragment: Fragment, firstShowing: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)

        if (!firstShowing) fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }

}


