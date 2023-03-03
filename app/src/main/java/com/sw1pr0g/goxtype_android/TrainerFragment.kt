package com.sw1pr0g.goxtype_android

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged

class TrainerFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextTrainer: EditText = view.findViewById(R.id.editTextTrainer)
        val textViewTrainer: TextView = view.findViewById(R.id.textViewTrainer)

        var editLetter: Int = 0

        editTextTrainer.addTextChangedListener {

            var toast = Toast.makeText(activity, "Incorrect letter - ${editTextTrainer.text}!", Toast.LENGTH_SHORT)
            if (editTextTrainer.text[0] == textViewTrainer.text[editLetter]) {
                toast.cancel()
                var spannableString: Spannable = SpannableStringBuilder(textViewTrainer.text)
                spannableString.setSpan(ForegroundColorSpan(Color.RED), editLetter,
                    editLetter+1, 0)
                textViewTrainer.setText(spannableString)
                editTextTrainer.setText("")
                editLetter++
            } else {
                toast.show()
                editTextTrainer.setText("")
            }

        }

    }
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