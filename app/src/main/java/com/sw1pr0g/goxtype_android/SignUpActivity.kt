package com.sw1pr0g.goxtype_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.log

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val onLogInTextClick = findViewById<TextView>(R.id.logInText)
        onLogInTextClick.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
        }
        val onLogInArrowClick = findViewById<ImageView>(R.id.logInArrow)
        onLogInArrowClick.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
        }
    }
}