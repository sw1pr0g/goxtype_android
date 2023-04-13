package com.sw1pr0g.goxtype_android.ui.main.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.databinding.FragmentMainProfileBinding
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback
import com.sw1pr0g.goxtype_android.ui.auth.AuthActivity
import com.sw1pr0g.goxtype_android.utils.SessionManager

class MainProfileFragment: Fragment() {
    private var _binding: FragmentMainProfileBinding? = null
    private val binding get() = _binding!!

    private var showFragmentCallback: ShowFragmentCallback? = null

    private lateinit var component: Component

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragmentCallback = context as ShowFragmentCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        component = Component(requireContext())

        binding.userNameTextView.text = SessionManager.getUserName(requireContext())

        binding.profileInfoCardView.setOnClickListener { /*showFragmentCallback?.showFragment(
            MainProfileInfoFragment(), false)*/ }

        binding.logOutCardView.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString((R.string.alert_exit_title)))
                .setMessage(resources.getString(R.string.alert_exit_supporting_text))
                .setNegativeButton(resources.getString(R.string.alert_exit_no)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.alert_exit_yes)) { _, _ ->
                    SessionManager.clearData(requireActivity())
                    component.newActivity(AuthActivity::class.java)
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
        showFragmentCallback = null
    }

}