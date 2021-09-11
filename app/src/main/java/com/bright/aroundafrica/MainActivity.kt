package com.bright.aroundafrica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val delay = 1000L

        val intent = Intent()
        val hasLaunched = PreferenceManager.getDefaultSharedPreferences(this)
            .getBoolean(AppConstants.hasLaunchedPref, false)
        Handler().postDelayed(
            {
                if (hasLaunched) {
                    intent.setClass(this, HomeActivity::class.java)
                } else {
                    intent.setClass(this, TutorialActivity::class.java)
                }

                startActivity(intent)
                finish()
            },
            delay
        )
    }
}