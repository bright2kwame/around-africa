package com.bright.aroundafrica

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bright.aroundafrica.fragment.TutorialScreenFragment


class TutorialPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return TutorialScreenFragment.newInstance(
            IMAGE_RES_IDS[position],
            MESSAGE_RES_IDS[position]
        )
    }

    override fun getCount(): Int {
        return IMAGE_RES_IDS.size
    }

    companion object {

        // Images resources
        private val IMAGE_RES_IDS = intArrayOf(
            R.drawable.ic_climbing,
            R.drawable.ic_running,
            R.drawable.ic_singing
        )

        // Text resources
        private val MESSAGE_RES_IDS =
            intArrayOf(R.string.tutorial_one, R.string.tutorial_two, R.string.tutorial_three)
    }
}
