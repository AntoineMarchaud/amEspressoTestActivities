package com.amespressotestactivities.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amespressotestactivities.simple.databinding.ActivityStartBinding

/** The first shown Activity. */
class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            continueButton.setOnClickListener {
                gotoNextPage()
            }
        }
    }

    /** Opens the next page. */
    private fun gotoNextPage() {
        startActivity(AskIdentityActivity.createIntent(this))
    }
}