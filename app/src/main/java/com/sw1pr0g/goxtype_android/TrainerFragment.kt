package com.sw1pr0g.goxtype_android

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

class TrainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_trainer, container, false)

        val editTextTrainer: EditText = view.findViewById(R.id.editTextTrainer)
        val startTrainerButton: Button = view.findViewById(R.id.startTrainerButton)

        startTrainerButton.setOnClickListener {
            editTextTrainer.requestFocus()
            editTextTrainer.postDelayed(Runnable { editTextTrainer.showKeyboard()} , 50)
        }

        var editLetter: Int = 0

        editTextTrainer.addTextChangedListener {

            val textViewTrainer: TextView = view.findViewById(R.id.textViewTrainer)

            if (editTextTrainer.text.isNotEmpty() && editLetter < textViewTrainer.text.length) {

                var toast = Toast.makeText(activity, "Error! - ${editTextTrainer.text.last()}", Toast.LENGTH_SHORT)

                if (editTextTrainer.text.last() == textViewTrainer.text[editLetter]) {
                    toast.cancel()
                    var spannableString = textViewTrainer.text.toSpannable()
                    spannableString.setSpan(ForegroundColorSpan(Color.BLUE), editLetter,
                        editLetter+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    textViewTrainer.setText(spannableString, TextView.BufferType.SPANNABLE)
                    editLetter++
                } else {
                    toast.show()
                }
            }

        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TrainerFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    fun EditText.showKeyboard() {
        val inputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        requestFocus()
        inputMethodManager.showSoftInput(this, 0)
        setSelection(length())
    }
}