package com.sw1pr0g.goxtype_android.ui.auth

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.databinding.FragmentAuthLogInBinding
import com.sw1pr0g.goxtype_android.domain.DataValidation
import com.sw1pr0g.goxtype_android.domain.UserAuthAction
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.main.MainActivity
import kotlinx.coroutines.*


class AuthLogInFragment: Fragment() {
    private var _binding: FragmentAuthLogInBinding? = null
    private val binding get() = _binding!!

    private var callbacks: AuthShowFragmentCallback? = null

    private lateinit var dataValidation: DataValidation
    private lateinit var userAuthAction: UserAuthAction
    private lateinit var dialogAuthLoading: DialogAuthLoading

    private val component = Component(activity)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as AuthShowFragmentCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthLogInBinding.inflate(inflater, container, false)
        val view = binding.root

        dataValidation = DataValidation(binding.logInEmailTextLayout, binding.logInPasswordTextLayout)

        userAuthAction = UserAuthAction()

        dialogAuthLoading = DialogAuthLoading(requireActivity())

        binding.logInButton.setOnClickListener { logIn() }

        binding.logInEmailEditText.addTextChangedListener { dataValidation.authOffMistakes(binding.logInEmailTextLayout, binding.logInPasswordTextLayout) }
        binding.logInPasswordEditText.addTextChangedListener { dataValidation.authOffMistakes(binding.logInEmailTextLayout, binding.logInPasswordTextLayout) }

        binding.goSignUpButton.setOnClickListener {
            callbacks?.showFragment(
                AuthSignUpFragment(), false)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun logIn() {

        /*val mainLooper = Looper.getMainLooper()

        var userLogInResponse = 0*/

        Thread(Runnable {

            userAuthAction.logIn(binding.logInEmailEditText.text.toString(),
                binding.logInPasswordEditText.text.toString())

            /*if(dataValidation.authLogIn()) {


                Handler(mainLooper).post {
                    dataValidation.authFieldsUpdate(userLogInResponse, requireContext())
                }
            }*/

            Thread.sleep(1000)

        }).start()

        val i = 0

        /*var userAuthResult = 0

        Thread(Runnable {
            dialogAuthLoading.startLoadingDialog()

            if (dataValidation.authLogIn()) {
                userAuthResult = userAuthAction.logIn(
                    binding.logInEmailEditText.text.toString(),
                    binding.logInPasswordEditText.text.toString()
                )
            }

            Thread.sleep(1000)
            dialogAuthLoading.dismissDialog()
        })

        if (userAuthResult != 0) {
            when (userAuthResult) {
                201 -> component.newActivity(MainActivity::class.java)
                404 -> {
                    Looper.prepare()
                    dataValidation.authDataIncorrect()
                    Toast.makeText(
                        activity,
                        R.string.log_in_server_error,
                        Toast.LENGTH_SHORT
                    ).show()
                    Looper.loop()
                }
                else -> {
                    Looper.prepare()
                    dataValidation.authDataIncorrect()
                    Looper.loop()
                }
            }
        }*/
    }

}