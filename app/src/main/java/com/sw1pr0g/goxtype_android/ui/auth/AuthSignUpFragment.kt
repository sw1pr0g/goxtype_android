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
import com.sw1pr0g.goxtype_android.databinding.FragmentAuthSignUpBinding
import com.sw1pr0g.goxtype_android.domain.validation.SignUpValidation
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback
import com.sw1pr0g.goxtype_android.viewmodel.AuthViewModel

class AuthSignUpFragment: Fragment() {
    private var _binding: FragmentAuthSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()

    private var showFragmentCallback: ShowFragmentCallback? = null
    private var authActivityCallback: AuthActivityCallback? = null

    private lateinit var dialogAuthLoading: DialogAuthLoading
    private lateinit var signUpValidation: SignUpValidation
    private lateinit var component: Component

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
        _binding = FragmentAuthSignUpBinding.inflate(inflater, container, false)
        val view = binding.root

        signUpValidation = SignUpValidation(requireContext(),
                                            binding.signUpEmailTextLayout,
                                            binding.signUpPasswordTextLayout,
                                            binding.signUpRepeatPasswordTextLayout,
                                            binding.signUpAcceptTermsCheckbox)
        dialogAuthLoading = DialogAuthLoading(requireActivity())
        component = Component(requireContext())

        viewModel.authResult.observe(requireActivity()) {
            when(it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    stopLoading()
                    authActivityCallback?.processAuth(it.data)
                }
                is BaseResponse.Error -> {
                    authActivityCallback?.showErrorSnackBar(it.msg)
                    // signUpValidation.fieldsIncorrect()
                    stopLoading()
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.goLogInButton.setOnClickListener {
            showFragmentCallback?.showFragment(AuthLogInFragment(), false)
        }

        binding.signUpButton.setOnClickListener { doSignUp() }

        binding.signUpEmailEditText.addTextChangedListener { signUpValidation.offFieldsMistakes() }
        binding.signUpPasswordEditText.addTextChangedListener { signUpValidation.offFieldsMistakes() }
        binding.signUpRepeatPasswordEditText.addTextChangedListener { signUpValidation.offFieldsMistakes() }

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

    private fun doSignUp() {
        if(signUpValidation.checkEmptyFields()) {
            val email = binding.signUpEmailEditText.text.toString()
            val pwd = binding.signUpPasswordEditText.text.toString()
            viewModel.logInAction = false
            viewModel.authUser(email = email, pwd = pwd)
        }
    }

    private fun showLoading() {
        dialogAuthLoading.startLoadingDialog()
    }

    private fun stopLoading() {
        dialogAuthLoading.dismissDialog()
    }

}