package com.sw1pr0g.goxtype_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
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
                         firstShowing: Boolean)
    }

    private var callbacks: Callbacks? = null

    private lateinit var signUpEmailEditText: EditText
    private lateinit var signUpPasswordEditText: EditText
    private lateinit var signUpRepeatPasswordEditText: EditText

    private lateinit var signUpButton: Button
    private lateinit var signUpAcceptTermsCheckbox: CheckBox
    private lateinit var goLogInButton: Button

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
        val view = inflater.inflate(R.layout.fragment_auth_sign_up, container, false)



        signUpEmailEditText = view.findViewById(R.id.sign_up_email_edit_text)
        signUpPasswordEditText = view.findViewById(R.id.sign_up_password_edit_text)
        signUpRepeatPasswordEditText = view.findViewById(R.id.sign_up_repeat_password_edit_text)

        signUpButton = view.findViewById(R.id.sign_up_button)
        signUpAcceptTermsCheckbox = view.findViewById(R.id.sign_up_accept_terms_checkbox)

        goLogInButton = view.findViewById(R.id.go_log_in_button)

        dialogAuthLoading = DialogAuthLoading(requireActivity())

        goLogInButton.setOnClickListener { callbacks?.showFragment(AuthLogInFragment(), false) }

        signUpButton.setOnClickListener {

            dialogAuthLoading.startLoadingDialog()

            Thread(
                kotlinx.coroutines.Runnable {
                    signUp(
                        signUpEmailEditText.text.toString(),
                        signUpPasswordEditText.text.toString()
                    )
                }
            ).start()
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun signUp(email: String,
                       password: String) {

        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val signUpInfo = UserBody(email, password)

        retIn.signUp(signUpInfo).enqueue(object :
            Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.code() == 201)
                    Toast.makeText(activity, "SignUp Success", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(activity, "ERROR! SignUp Non Success", Toast.LENGTH_SHORT).show()

            }

            })
        Thread.sleep(1500)
        dialogAuthLoading.dismissDialog()
    }

}