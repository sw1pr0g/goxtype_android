package com.sw1pr0g.goxtype_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
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
                         statusBarColor: Int,
                         statusBarDarkText: Boolean,
                         firstShowing: Boolean)
    }

    private var callbacks: Callbacks? = null

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

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

        emailEditText = view.findViewById(R.id.email_edit_text)
        passwordEditText = view.findViewById(R.id.password_edit_text)

        dialogAuthLoading = DialogAuthLoading(requireActivity())

        logInButton.setOnClickListener {

            dialogAuthLoading.startLoadingDialog()

            Thread(
                Runnable {
                    logIn(emailEditText.text.toString(), passwordEditText.text.toString())
                }
            ).start()
        }

        goSignUpButton.setOnClickListener { callbacks?.showFragment(AuthSignUpFragment(),
            getColor(requireActivity(), R.color.colorPrimary), false,
            firstShowing = false) }

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
                } else
                    Toast.makeText(activity, "ERROR! LogIn Non Success", Toast.LENGTH_SHORT).show()

            }

        })
        Thread.sleep(1500)
        dialogAuthLoading.dismissDialog()
    }

}