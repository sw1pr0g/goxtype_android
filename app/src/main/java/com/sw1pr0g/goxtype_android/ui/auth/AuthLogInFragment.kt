package com.sw1pr0g.goxtype_android.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sw1pr0g.goxtype_android.data.api.response.BaseResponse
import com.sw1pr0g.goxtype_android.data.api.response.LogInResponse
import com.sw1pr0g.goxtype_android.databinding.FragmentAuthLogInBinding
import com.sw1pr0g.goxtype_android.domain.validation.LogInValidation
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.main.MainActivity
import com.sw1pr0g.goxtype_android.utils.SessionManager
import com.sw1pr0g.goxtype_android.viewmodel.LogInViewModel


class AuthLogInFragment: Fragment() {
    private var _binding: FragmentAuthLogInBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LogInViewModel>()

    private var showFragmentCallback: ShowFragmentCallback? = null
    private var authActivityCallback: AuthActivityCallback? = null

    private lateinit var component: Component
    private lateinit var dataValidation: LogInValidation
    private lateinit var dialogAuthLoading: DialogAuthLoading

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragmentCallback = context as ShowFragmentCallback?
        authActivityCallback = context as AuthActivityCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthLogInBinding.inflate(inflater, container, false)
        val view = binding.root

        dataValidation = LogInValidation(binding.logInEmailTextLayout, binding.logInPasswordTextLayout)
        dialogAuthLoading = DialogAuthLoading(requireActivity())
        component = Component(requireContext())

        viewModel.logInResult.observe(requireActivity()) {
            when(it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    stopLoading()
                    authActivityCallback?.processLogIn(it.data)
                }
                is BaseResponse.Error -> {
                    dataValidation.fieldsIncorrect()
                    stopLoading()
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.logInButton.setOnClickListener { doLogIn() }

        binding.logInEmailEditText.addTextChangedListener { dataValidation.offFieldsMistakes() }
        binding.logInPasswordEditText.addTextChangedListener { dataValidation.offFieldsMistakes() }

        binding.goSignUpButton.setOnClickListener {
            showFragmentCallback?.showFragment(
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
        showFragmentCallback = null
        authActivityCallback = null
    }

    private fun doLogIn() {
        if (dataValidation.checkEmptyFields()) {
            val email = binding.logInEmailEditText.text.toString()
            val pwd = binding.logInPasswordEditText.text.toString()
            viewModel.logInUser(email = email, pwd = pwd)
        }
    }

    private fun showLoading() {
        dialogAuthLoading.startLoadingDialog()
    }

    private fun stopLoading() {
        dialogAuthLoading.dismissDialog()
    }

}