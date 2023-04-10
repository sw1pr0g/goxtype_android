package com.sw1pr0g.goxtype_android.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.databinding.FragmentMainHomeBinding
import com.sw1pr0g.goxtype_android.databinding.FragmentMainProfileBinding
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback
import com.sw1pr0g.goxtype_android.ui.auth.AuthActivity

class MainProfileFragment: Fragment() {
    private var _binding: FragmentMainProfileBinding? = null
    private val binding get() = _binding!!

    private var callbacks: ShowFragmentCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as ShowFragmentCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.profileInfoCardView.setOnClickListener { callbacks?.showFragment(MainProfileInfoFragment(), false) }

        binding.logOutCardView.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString((R.string.alert_exit_title)))
                .setMessage(resources.getString(R.string.alert_exit_supporting_text))
                .setNegativeButton(resources.getString(R.string.alert_exit_no)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.alert_exit_yes)) { _, _ ->

                    val intent = Intent(activity, AuthActivity::class.java)
                    startActivity(intent)
                    activity?.finish()

                }
                .show()

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