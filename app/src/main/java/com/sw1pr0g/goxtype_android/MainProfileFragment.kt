package com.sw1pr0g.goxtype_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainProfileFragment: Fragment() {

    interface Callbacks {

        fun showFragment(fragment: Fragment, firstShowing: Boolean)

    }

    private var callbacks: Callbacks? = null

    private lateinit var textView: TextView
    private lateinit var profileInfoCardView: MaterialCardView
    private lateinit var logOutCardView: MaterialCardView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_profile, container, false)

        textView = view.findViewById(R.id.textView)
        profileInfoCardView = view.findViewById(R.id.profile_info_card_view)
        logOutCardView = view.findViewById(R.id.log_out_card_view)

        profileInfoCardView.setOnClickListener { callbacks?.showFragment(MainProfileInfoFragment(), false) }

        logOutCardView.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString((R.string.alert_exit_title)))
                .setMessage(resources.getString(R.string.alert_exit_supporting_text))
                .setNegativeButton(resources.getString(R.string.alert_exit_no)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.alert_exit_yes)) { _, _ ->

                    textView.text = textView.width.toString()

                    /*val intent = Intent(activity, AuthActivity::class.java)
                    startActivity(intent)
                    activity?.finish()*/

                }
                .show()

        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

}