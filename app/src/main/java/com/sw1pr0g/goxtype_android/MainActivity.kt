package com.sw1pr0g.goxtype_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.sw1pr0g.goxtype_android.databinding.ActivityLogInBinding
import com.sw1pr0g.goxtype_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var navBar: MeowBottomNavigation = findViewById(R.id.navBar)

        addFragment(MainFragment.newInstance())
        navBar.show(0)

        navBar.add(MeowBottomNavigation.Model(0, R.drawable.baseline_home_24))
        navBar.add(MeowBottomNavigation.Model(1, R.drawable.baseline_keyboard_24))

        binding.navBar.setOnClickMenuListener {

            when(it.id){

                0 -> {
                    replaceFragment(MainFragment.newInstance())
                }

                1 -> {
                    replaceFragment(TrainerFragment.newInstance())
                }

            }

        }

    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentTransition = supportFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.framePage, fragment).addToBackStack(Fragment::class.java.simpleName).commit()

    }

    private fun addFragment(fragment: Fragment) {

        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.framePage, fragment).addToBackStack(Fragment::class.java.simpleName).commit()

    }
}