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

class MainTrainerFragment : Fragment() {

    private lateinit var trainerEditText: EditText
    private lateinit var trainerTextView: TextView
    private lateinit var trainerStartButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_trainer, container, false)

        trainerEditText = view.findViewById(R.id.trainer_edit_text)
        trainerStartButton = view.findViewById(R.id.trainer_start_button)
        var trainerStatus = false

        trainerStartButton.setOnClickListener {
            if (!trainerStatus) {
                trainerStatus = true
                trainerStartButton.text = "Press to end"
                trainerEditText.requestFocus()
                trainerEditText.postDelayed({ trainerEditText.showKeyboard()}, 50)
            }
            else {
                trainerStatus = false
                trainerStartButton.text = "Press to start"
                hideKeyboard(requireContext(), requireView())
            }

        }

        var editLetter = 0

        trainerEditText.addTextChangedListener {

            trainerTextView = view.findViewById(R.id.trainer_text_view)

            if (trainerEditText.text.isNotEmpty() && editLetter < trainerTextView.text.length) {

                val toast = Toast.makeText(activity, "Error! - ${trainerEditText.text.last()}", Toast.LENGTH_SHORT)

                if (trainerEditText.text.last() == trainerTextView.text[editLetter]) {
                    toast.cancel()
                    val spannableString = trainerTextView.text.toSpannable()
                    spannableString.setSpan(ForegroundColorSpan(Color.BLUE), editLetter,
                        editLetter+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    trainerTextView.setText(spannableString, TextView.BufferType.SPANNABLE)
                    editLetter++
                } else {
                    toast.show()
                }
            }

        }
        return view
    }

    private fun EditText.showKeyboard() {
        val inputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        requestFocus()
        inputMethodManager.showSoftInput(this, 0)
        setSelection(length())
    }
    private fun hideKeyboard(context: Context, view: View) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}