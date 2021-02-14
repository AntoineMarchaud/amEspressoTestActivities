package com.amespressotestactivities.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.amespressotestactivities.simple.databinding.ActivityAskIdentityBinding

/** Asks the name of the user. He cannot proceed if no name is entered. */
class AskIdentityActivity : AppCompatActivity() {

    companion object {
        /**
         * @returns the Intent to create this Activity.
         * @param context a Context.
         */
        fun createIntent(context: Context) = Intent(context, AskIdentityActivity::class.java)
    }

    private lateinit var binding: ActivityAskIdentityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAskIdentityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            continueButton.setOnClickListener {
                gotoNextPage()
            }

            enterNameEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    updateContinueButton()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) { /* Nothing to do. */
                }

                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) { /* Nothing to do. */
                }
            })
        }


        updateContinueButton()
    }

    /** Opens the next page, giving the typed name to it. */
    private fun gotoNextPage() {
        with(binding) {
            val name = enterNameEditText.text.trim().toString()

            val intent = EndActivity.createIntent(this@AskIdentityActivity, name)
            startActivity(intent)
        }
    }

    /** Enables or disables the button according to the text. */
    private fun updateContinueButton() {
        with(binding) {
            continueButton.isEnabled = enterNameEditText.text.trim()
                .isNotBlank()        // Is blank, the button is disabled.
        }
    }
}