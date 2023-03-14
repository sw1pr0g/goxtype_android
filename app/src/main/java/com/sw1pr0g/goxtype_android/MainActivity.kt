package com.sw1pr0g.goxtype_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.sw1pr0g.goxtype_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainHomeFragment.Callbacks, MainProfileFragment.Callbacks {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navBar: MeowBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navBar = findViewById(R.id.navBar)

        showFragment(MainHomeFragment(), true)
        navBar.show(0)

        navBar.add(MeowBottomNavigation.Model(0, R.drawable.baseline_home_24))
        navBar.add(MeowBottomNavigation.Model(1, R.drawable.baseline_keyboard_24))

        binding.navBar.setOnClickMenuListener {

            when(it.id){

                0 -> {
                    showFragment(MainHomeFragment(), false)
                }

                1 -> {
                    showFragment(MainTrainerFragment(), false)
                }

            }

        }
    }

    override fun showFragment(fragment: Fragment, firstShowing: Boolean) {

        val supportFragmentManager = supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)

        if (!firstShowing) supportFragmentManager.addToBackStack(null)

        supportFragmentManager.commit()

    }
}