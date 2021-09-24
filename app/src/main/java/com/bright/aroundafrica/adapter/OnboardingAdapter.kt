package com.bright.aroundafrica.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bright.aroundafrica.R
import com.bright.aroundafrica.adapter.OnboardingAdapter.OnboardingViewHolder
import com.bright.aroundafrica.model.OnBoardingItem

class OnboardingAdapter(private val onBoardingItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_tutorial_screen, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.setOnBoardingData(onBoardingItems[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    inner class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textTitle: TextView
        private val textDescription: TextView
        private val imageOnboarding: ImageView

        fun setOnBoardingData(onBoardingItem: OnBoardingItem) {
            textTitle.text = onBoardingItem.title
            textDescription.text = onBoardingItem.message
            imageOnboarding.setImageResource(onBoardingItem.image)
        }

        init {
            textTitle = itemView.findViewById(R.id.tutorial_screen_title)
            textDescription = itemView.findViewById(R.id.tutorial_screen_message)
            imageOnboarding = itemView.findViewById(R.id.tutorial_screen_image)
        }
    }
}