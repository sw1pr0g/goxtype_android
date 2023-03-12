package com.sw1pr0g.goxtype_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val backMain: ImageButton = findViewById(R.id.backMain)

        backMain.setOnClickListener {

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }

        val logoutBtn: Button = findViewById(R.id.logoutBtn)

        logoutBtn.setOnClickListener {

            /*val i = Intent(this, LogInActivity::class.java)
            finishAffinity()
            startActivity(i)*/

        }

    }
}