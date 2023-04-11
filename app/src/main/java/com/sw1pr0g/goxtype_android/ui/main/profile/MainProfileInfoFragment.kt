package com.sw1pr0g.goxtype_android.ui.main.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.sw1pr0g.goxtype_android.R

class MainProfileInfoFragment: Fragment() {

    interface Callbacks {

        fun showFragment(fragment: Fragment, firstShowing: Boolean)

    }

    private var callbacks: Callbacks? = null

    private lateinit var closeSubPageImageButton: ImageButton
    private lateinit var editUserDataButton: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_profile_info, container, false)

        closeSubPageImageButton = view.findViewById(R.id.close_sub_page_image_button)
        editUserDataButton = view.findViewById(R.id.edit_user_data_button)

        closeSubPageImageButton.setOnClickListener { callbacks?.showFragment(MainProfileFragment(), false) }
        editUserDataButton.setOnClickListener { callbacks?.showFragment(MainProfileInfoEditFragment(), false) }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

}