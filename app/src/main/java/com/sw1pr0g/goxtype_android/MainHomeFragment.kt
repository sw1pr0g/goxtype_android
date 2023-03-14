package com.sw1pr0g.goxtype_android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

class MainHomeFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileImageButton: ImageButton = view.findViewById(R.id.profile_image_button)

        profileImageButton.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainHomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}