package com.bright.aroundafrica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        buttonSignIn.setOnClickListener { v ->
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Set the pager with an adapter
        tutorialPager!!.offscreenPageLimit = 3
        tutorialPager!!.adapter = TutorialPagerAdapter(supportFragmentManager)
        circleIndicator.setupWithViewPager(tutorialPager, true)
    }
}