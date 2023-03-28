package com.sw1pr0g.goxtype_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

class MainHomeFragment : Fragment() {

    /*interface Callbacks {
        fun showFragment(fragment: Fragment, firstShowing: Boolean)
    }

    private var callbacks: Callbacks? = null*/

    private lateinit var typingTestStartButton: Button
    private lateinit var typingRuleFullDescButton: Button

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_home, container, false)

        /*typingTestStartButton = view.findViewById(R.id.typing_test_start_button)
        typingRuleFullDescButton = view.findViewById(R.id.typing_rule_full_desc_button)

        typingTestStartButton.setOnClickListener { TODO("Write Typing test and call fragment here") }
        typingRuleFullDescButton.setOnClickListener { TODO("Write show all func") }*/
    }

    /*override fun onDetach() {
        super.onDetach()
        callbacks = null
    }*/

}