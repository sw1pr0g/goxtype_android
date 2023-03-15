package com.sw1pr0g.goxtype_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.sw1pr0g.goxtype_android.api.ApiInterface
import com.sw1pr0g.goxtype_android.api.RetrofitInstance
import com.sw1pr0g.goxtype_android.api.UserBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthSignUpFragment: Fragment() {

    interface Callbacks {
        fun showFragment(fragment: Fragment,
                         statusBarColor: Int,
                         statusBarDarkText: Boolean,
                         firstShowing: Boolean)
    }

    private var callbacks: Callbacks? = null

    private lateinit var logInTextView: TextView
    private lateinit var logInImageView: ImageView

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var signUpButton: Button


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_sign_up, container, false)

        logInTextView = view.findViewById(R.id.log_in_text_view)
        logInImageView = view.findViewById(R.id.log_in_image_view)
        signUpButton = view.findViewById(R.id.sign_up_button)

        nameEditText = view.findViewById(R.id.name_edit_text)
        emailEditText = view.findViewById(R.id.email_edit_text)
        passwordEditText = view.findViewById(R.id.password_edit_text)


        logInTextView.setOnClickListener { showLogInFragment() }
        logInImageView.setOnClickListener { showLogInFragment() }
        signUpButton.setOnClickListener {
            signUp(emailEditText.text.toString(),
                passwordEditText.text.toString(),
                nameEditText.text.toString())

        }



        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun showLogInFragment() = callbacks?.showFragment(AuthLogInFragment(),
        getColor(requireActivity(), R.color.background), true,
        firstShowing = false)

    private fun signUp(email: String,
                       password: String,
                       name: String) {

        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val signUpInfo = UserBody(email, password, name)

        retIn.signUp(signUpInfo).enqueue(object :
            Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.code() == 201) {
                    Toast.makeText(activity, "SignUp Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "ERROR! SignUp Non Success", Toast.LENGTH_SHORT).show()
                }

            }

            })

    }

}