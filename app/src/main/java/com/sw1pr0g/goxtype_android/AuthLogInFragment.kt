package com.sw1pr0g.goxtype_android

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.sw1pr0g.goxtype_android.api.ApiInterface
import com.sw1pr0g.goxtype_android.api.LogInBody
import com.sw1pr0g.goxtype_android.api.RetrofitInstance
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthLogInFragment: Fragment() {

    interface Callbacks {
        fun showFragment(fragment: Fragment,
                         firstShowing: Boolean)
    }

    private var callbacks: Callbacks? = null

    private lateinit var logInEmailTextLayout: TextInputLayout
    private lateinit var logInEmailEditText: EditText

    private lateinit var logInPasswordTextLayout: TextInputLayout
    private lateinit var logInPasswordEditText: EditText

    private lateinit var logInButton: Button
    private lateinit var goSignUpButton: Button

    private lateinit var dialogAuthLoading: DialogAuthLoading

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_log_in, container, false)

        logInButton = view.findViewById(R.id.log_in_button)
        goSignUpButton = view.findViewById(R.id.go_sign_up_button)

        logInEmailTextLayout = view.findViewById(R.id.log_in_email_text_layout)
        logInEmailEditText = view.findViewById(R.id.log_in_email_edit_text)

        logInPasswordTextLayout = view.findViewById(R.id.log_in_password_text_layout)
        logInPasswordEditText = view.findViewById(R.id.log_in_password_edit_text)

        dialogAuthLoading = DialogAuthLoading(requireActivity())

        logInButton.setOnClickListener {

            if (logInEmailEditText.text.isNotEmpty() && logInPasswordEditText.text.isNotEmpty()) {
                dialogAuthLoading.startLoadingDialog()

                Thread(
                    Runnable {
                        logIn(
                            logInEmailEditText.text.toString(),
                            logInPasswordEditText.text.toString()
                        )
                    }
                ).start()
            }
            validateLogInData()

        }

        logInEmailEditText.addTextChangedListener { checkOffMistakes() }
        logInPasswordEditText.addTextChangedListener { checkOffMistakes() }

        goSignUpButton.setOnClickListener { callbacks?.showFragment(AuthSignUpFragment(),false) }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun logIn(email: String, password: String) {

        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val logInInfo = LogInBody(email, password)

        retIn.logIn(logInInfo).enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.isSuccessful) {
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    logInEmailTextLayout.isErrorEnabled = true
                    logInEmailTextLayout.error = "Incorrect email"
                    logInPasswordTextLayout.isErrorEnabled = true
                    logInPasswordTextLayout.error = "Incorrect password"
                }
            }

        })
        Thread.sleep(1500)
        dialogAuthLoading.dismissDialog()
    }

    private fun validateLogInData() {
        if (logInEmailEditText.text.isNotEmpty()) {
            logInEmailTextLayout.isErrorEnabled = false
        }
        if (logInPasswordEditText.text.isNotEmpty()) {
            logInPasswordTextLayout.isErrorEnabled = false
        }
        if (logInEmailEditText.text.isNotEmpty() && logInPasswordEditText.text.isNotEmpty()) {
            logInEmailTextLayout.isErrorEnabled = false
            logInPasswordTextLayout.isErrorEnabled = false
        }
        if (logInEmailEditText.text.isEmpty() && logInPasswordEditText.text.isEmpty()) {
            logInEmailTextLayout.isErrorEnabled = true
            logInEmailTextLayout.error = "Email is required"
            logInPasswordTextLayout.isErrorEnabled = true
            logInPasswordTextLayout.error = "Password is required"
        } else if (logInEmailEditText.text.isEmpty()) {
            logInEmailTextLayout.isErrorEnabled = true
            logInEmailTextLayout.error = "Email is required"
        } else if (logInPasswordEditText.text.isEmpty()) {
            logInPasswordTextLayout.isErrorEnabled = true
            logInPasswordTextLayout.error = "Password is required"
        } else {
            logInEmailTextLayout.isErrorEnabled = false
            logInPasswordTextLayout.isErrorEnabled = false
        }
    }

    private fun checkOffMistakes() {
        if (logInEmailEditText.text.isNotEmpty()) {
            logInEmailTextLayout.isErrorEnabled = false
        }
        if (logInPasswordEditText.text.isNotEmpty()) {
            logInPasswordTextLayout.isErrorEnabled = false
        }
    }
}