package com.sw1pr0g.goxtype_android.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sw1pr0g.goxtype_android.databinding.FragmentMainHomeBinding
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback
import com.sw1pr0g.goxtype_android.utils.SessionManager

class MainHomeFragment : Fragment() {
    private var _binding: FragmentMainHomeBinding? = null
    private val binding get() = _binding!!

    private var callbacks: ShowFragmentCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as ShowFragmentCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.greetingMessageTextView.text = SessionManager.getGreetingMessage()
        binding.userNameGreetingTextView.text = SessionManager.getUserName(requireContext())
        binding.typingTestStartButton.setOnClickListener {  }

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