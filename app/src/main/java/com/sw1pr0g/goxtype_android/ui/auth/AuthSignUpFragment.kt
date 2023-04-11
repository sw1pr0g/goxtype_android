package com.sw1pr0g.goxtype_android.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.sw1pr0g.goxtype_android.databinding.FragmentAuthSignUpBinding
import com.sw1pr0g.goxtype_android.domain.validation.SignUpValidation
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback

class AuthSignUpFragment: Fragment() {
    private var _binding: FragmentAuthSignUpBinding? = null
    private val binding get() = _binding!!

    private var showFragmentCallback: ShowFragmentCallback? = null
    private var authActivityCallback: AuthActivityCallback? = null

    private lateinit var dialogAuthLoading: DialogAuthLoading
    private lateinit var dataValidation: SignUpValidation
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

        dataValidation = SignUpValidation(requireContext(),
                                            binding.signUpEmailTextLayout,
                                            binding.signUpPasswordTextLayout,
                                            binding.signUpRepeatPasswordTextLayout,
                                            binding.signUpAcceptTermsCheckbox)

        binding.goLogInButton.setOnClickListener {
            showFragmentCallback?.showFragment(AuthLogInFragment(), false)
        }

        binding.signUpButton.setOnClickListener { doSignUp() }

        binding.signUpEmailEditText.addTextChangedListener { dataValidation.offFieldsMistakes() }
        binding.signUpPasswordEditText.addTextChangedListener { dataValidation.offFieldsMistakes() }
        binding.signUpRepeatPasswordEditText.addTextChangedListener { dataValidation.offFieldsMistakes() }

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
        if(dataValidation.checkEmptyFields()) {

        }
    }

}