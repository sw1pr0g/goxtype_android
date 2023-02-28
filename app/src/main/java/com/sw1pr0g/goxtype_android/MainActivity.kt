package com.sw1pr0g.goxtype_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {
    val ID_HOME = 0
    val ID_TRAINER = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var navBar: MeowBottomNavigation = findViewById(R.id.navBar)

        navBar.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.baseline_home_24))
        navBar.add(MeowBottomNavigation.Model(ID_TRAINER, R.drawable.baseline_keyboard_24))

        navBar.show(ID_HOME)
    }
}