package com.sw1pr0g.goxtype_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TrainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trainer, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TrainerFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}