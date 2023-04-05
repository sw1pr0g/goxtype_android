package com.sw1pr0g.goxtype_android.ui.auth

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
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

    private val dataValidation = DataValidation()
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

        val userAuthAction = UserAuthAction()

        binding.logInButton.setOnClickListener {
            val dialogAuthLoading = DialogAuthLoading(requireActivity())

            if (dataValidation.authLogIn(
                    binding.logInEmailTextLayout,
                    binding.logInPasswordTextLayout)) {
                dialogAuthLoading.startLoadingDialog()

                Thread(Runnable {
                        val userAuthResult = userAuthAction.logIn(
                            binding.logInEmailEditText.text.toString(),
                            binding.logInPasswordEditText.text.toString()
                        )

                        when (userAuthResult) {
                            true -> component.newActivity(MainActivity::class.java)
                            else -> {
                                dataValidation.authLogIn(
                                    binding.logInEmailTextLayout,
                                    binding.logInPasswordTextLayout
                                )
                                Looper.prepare()
                                Toast.makeText(activity, "Error with fetching data from server! Try again later", Toast.LENGTH_SHORT).show()
                                Looper.loop()

                            }
                        }
                    }
                ).start()

            }
            dialogAuthLoading.dismissDialog()
        }

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

}