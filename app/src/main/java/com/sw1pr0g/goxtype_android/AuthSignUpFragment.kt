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
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
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
    private var validateChecks: Boolean = false

    private lateinit var signUpEmailTextLayout: TextInputLayout
    private lateinit var signUpEmailEditText: EditText

    private lateinit var signUpPasswordTextLayout: TextInputLayout
    private lateinit var signUpPasswordEditText: EditText

    private lateinit var signUpRepeatPasswordTextLayout: TextInputLayout
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

        signUpEmailTextLayout = view.findViewById(R.id.sign_up_email_text_layout)
        signUpEmailEditText = view.findViewById(R.id.sign_up_email_edit_text)

        signUpPasswordTextLayout = view.findViewById(R.id.sign_up_password_text_layout)
        signUpPasswordEditText = view.findViewById(R.id.sign_up_password_edit_text)

        signUpRepeatPasswordTextLayout = view.findViewById(R.id.sign_up_repeat_password_text_layout)
        signUpRepeatPasswordEditText = view.findViewById(R.id.sign_up_repeat_password_edit_text)

        signUpButton = view.findViewById(R.id.sign_up_button)
        signUpAcceptTermsCheckbox = view.findViewById(R.id.sign_up_accept_terms_checkbox)

        goLogInButton = view.findViewById(R.id.go_log_in_button)

        dialogAuthLoading = DialogAuthLoading(requireActivity())

        goLogInButton.setOnClickListener { callbacks?.showFragment(AuthLogInFragment(), false) }

        signUpButton.setOnClickListener {

            validateSignUpData()
            if (validateChecks) {
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

        }

        signUpEmailEditText.addTextChangedListener { checkOffMistakes() }
        signUpPasswordEditText.addTextChangedListener { checkOffMistakes() }
        signUpRepeatPasswordEditText.addTextChangedListener { checkOffMistakes() }

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
                else {

                    if (response.message() == "User email already exists!") {
                        Toast.makeText(activity, "Please", Toast.LENGTH_SHORT).show()
                        validateChecks = true
                    }

                }

            }

            })
        Thread.sleep(1500)
        dialogAuthLoading.dismissDialog()
    }

    private fun validateSignUpData() {
        val passwordMatches = signUpPasswordEditText.text.toString() == signUpRepeatPasswordEditText.text.toString()

        if (signUpEmailEditText.text.isEmpty() && signUpPasswordEditText.text.isEmpty() &&
                signUpRepeatPasswordEditText.text.isEmpty()) {
            signUpEmailTextLayout.isErrorEnabled = true
            signUpEmailTextLayout.error = "Email is required"
            signUpPasswordTextLayout.isErrorEnabled = true
            signUpPasswordTextLayout.error = "Password is required"
            signUpRepeatPasswordTextLayout.isErrorEnabled = true
            signUpRepeatPasswordTextLayout.error = "Repeat password is required"
            validateChecks = false
        } else if (signUpEmailEditText.text.isEmpty()) {
            signUpEmailTextLayout.isErrorEnabled = true
            signUpEmailTextLayout.error = "Email is required"
            validateChecks = false
        } else if (signUpPasswordEditText.text.isEmpty()) {
            signUpPasswordTextLayout.isErrorEnabled = true
            signUpPasswordTextLayout.error = "Password is required"
            validateChecks = false
        } else if (signUpRepeatPasswordEditText.text.isEmpty()) {
            signUpRepeatPasswordTextLayout.isErrorEnabled = true
            signUpRepeatPasswordTextLayout.error = "Repeat password is required"
            validateChecks = false
        } else if (!signUpEmailEditText.isEmailValid()){
            signUpEmailTextLayout.isErrorEnabled = true
            signUpEmailTextLayout.error = "Email is incorrect"
            validateChecks = false
        } else if (!passwordMatches) {
            signUpPasswordTextLayout.isErrorEnabled = true
            signUpPasswordTextLayout.error = "Passwords don't match"
            signUpRepeatPasswordTextLayout.isErrorEnabled = true
            signUpRepeatPasswordTextLayout.error = "Passwords don't match"
            validateChecks = false
        } else {
            if (!signUpAcceptTermsCheckbox.isChecked) {
                Toast.makeText(activity, "Please read and accept all our terms and conditions!", Toast.LENGTH_SHORT).show()
                validateChecks = false
            }
        }


    }

    private fun checkOffMistakes() {
        if (signUpEmailEditText.text.isNotEmpty()) {
            signUpEmailTextLayout.isErrorEnabled = false
            validateChecks = true
        }
        if (signUpPasswordEditText.text.isNotEmpty()) {
            signUpPasswordTextLayout.isErrorEnabled = false
            validateChecks = true
        }
        if (signUpRepeatPasswordEditText.text.isNotEmpty()) {
            signUpRepeatPasswordTextLayout.isErrorEnabled = false
            validateChecks = true
        }
    }

    private fun EditText.isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }
}