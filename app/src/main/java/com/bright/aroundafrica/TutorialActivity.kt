package com.bright.aroundafrica

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.bright.aroundafrica.adapter.OnboardingAdapter
import com.bright.aroundafrica.model.OnBoardingItem
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity() {
    private var onboardingAdapter: OnboardingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        onboardingAdapter = OnboardingAdapter(setOnboardingItem())
        tutorialPager.adapter = onboardingAdapter
        setOnboadingIndicator()
        setCurrentOnboardingIndicators(0)
        tutorialPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnboardingIndicators(position)
            }
        })
        buttonSignIn.setOnClickListener {
            if (tutorialPager.currentItem + 1 < onboardingAdapter!!.itemCount) {
                tutorialPager.currentItem = tutorialPager.currentItem + 1
            } else {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }
        }
    }

    private fun setOnboadingIndicator() {
        val indicators = arrayOfNulls<ImageView>(
            onboardingAdapter!!.itemCount
        )
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.tab_indicator_default
                )
            )
            indicators[i]!!.layoutParams = layoutParams
            layoutOnboardingIndicators!!.addView(indicators[i])
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentOnboardingIndicators(index: Int) {
        val childCount = layoutOnboardingIndicators!!.childCount
        for (i in 0 until childCount) {
            val imageView = layoutOnboardingIndicators!!.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.tab_indicator_selected
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.tab_indicator_default
                    )
                )
            }
        }
        if (index == onboardingAdapter!!.itemCount - 1) {
            buttonSignIn!!.text = "Get Started"
        } else {
            buttonSignIn!!.text = "Next"
        }
    }

    private fun setOnboardingItem(): List<OnBoardingItem> {
        val firstItem = OnBoardingItem(
            getString(R.string.app_name),
            getString(R.string.tutorial_one),
            R.drawable.ic_climbing
        )
        val secondItem = OnBoardingItem(
            getString(R.string.app_name),
            getString(R.string.tutorial_two),
            R.drawable.ic_running
        )
        val thirdItem = OnBoardingItem(
            getString(R.string.app_name),
            getString(R.string.tutorial_three),
            R.drawable.ic_singing
        )
        return arrayListOf(firstItem,secondItem,thirdItem)
    }
}