package com.sw1pr0g.goxtype_android

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import org.w3c.dom.Text
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.TimerTask
import kotlin.math.roundToInt

class MainTrainerFragment : Fragment() {

    private lateinit var trainerEditText: EditText
    private lateinit var trainerTextView: TextView
    private lateinit var trainerStartButton: Button

    private lateinit var userClicksTextView: TextView
    private lateinit var userAccuracyTextView: TextView
    private lateinit var userCPMTextView: TextView
    private lateinit var userTimeChronometer: Chronometer

    private var userClicks = 0
    private var userCorrectClicks = 0
    private var userAccuracy = 0.0
    private var userCPM = 0.0
    private var userTime = 0
    private var editLetter = 0
    private lateinit var incorrectSymbolToast: Toast

    private var trainerStatus = false

    private val df = DecimalFormat("#.##")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_trainer, container, false)

        trainerEditText = view.findViewById(R.id.trainer_edit_text)
        trainerStartButton = view.findViewById(R.id.trainer_start_button)

        userClicksTextView = view.findViewById(R.id.user_clicks_text_view)
        userAccuracyTextView = view.findViewById(R.id.user_accuracy_text_view)
        userCPMTextView = view.findViewById(R.id.user_CPM_text_view)
        userTimeChronometer = view.findViewById(R.id.user_time_chronometer)

        trainerStartButton.setOnClickListener { trainerStatusCheck() }

        trainerEditText.addTextChangedListener {

            trainerTextView = view.findViewById(R.id.trainer_text_view)

            incorrectSymbolToast = Toast.makeText(activity,
                "Error! - ${trainerEditText.text.last()}", Toast.LENGTH_SHORT)

            if (trainerEditText.text.isNotEmpty() && editLetter < trainerTextView.text.length) {

                if (trainerEditText.text.last() == trainerTextView.text[editLetter]) correctSymbol()
                else incorrectSymbol()

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

    private fun correctSymbol() {
        incorrectSymbolToast.cancel()
        val spannableString = trainerTextView.text.toSpannable()
        spannableString.setSpan(ForegroundColorSpan(Color.BLUE), editLetter,
            editLetter+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        trainerTextView.setText(spannableString, TextView.BufferType.SPANNABLE)
        editLetter++
        userClicks++
        userCorrectClicks++
        userClicksTextView.text = "Clicks: $userClicks"

        userAccuracy = (userCorrectClicks*100).toDouble()/userClicks
        userAccuracyTextView.text = "Accuracy: ${df.format(userAccuracy)}%"
    }

    private fun incorrectSymbol() {
        incorrectSymbolToast.show()
        userClicks++
        userClicksTextView.text = "Clicks: $userClicks"
        userAccuracy = (userCorrectClicks*100).toDouble()/userClicks
        userAccuracyTextView.text = "Accuracy: ${df.format(userAccuracy)}%"
    }

    private fun trainerStatusCheck() {

        when (trainerStatus) {

            true -> {
                userTimeChronometer.stop()
                userTimeChronometer.base = SystemClock.elapsedRealtime()
                trainerStatus = false
                trainerStartButton.text = "Press to start"
                hideKeyboard(requireContext(), requireView())
                userClicks = 0
                userAccuracy = 0.0
                userCPM = 0.0
                userClicksTextView.text = "Clicks: $userClicks"
                userAccuracyTextView.text = "Accuracy: ${userAccuracy.toInt()}%"
                userCPMTextView.text = "CPM: ${userCPM.toInt()}"
            }

            false -> {
                userTimeChronometer.start()
                trainerStatus = true
                trainerStartButton.text = "Press to end"
                trainerEditText.requestFocus()
                trainerEditText.postDelayed({ trainerEditText.showKeyboard()}, 50)

            }
        }

    }

}