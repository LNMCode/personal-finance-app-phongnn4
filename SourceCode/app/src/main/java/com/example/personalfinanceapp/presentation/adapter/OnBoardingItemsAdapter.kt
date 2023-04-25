package com.example.personalfinanceapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalfinanceapp.databinding.OnboardingItemContainerBinding
import com.example.personalfinanceapp.domain.model.OnBoardingItem

class OnBoardingItemsAdapter(private val onBoardingItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingItemsAdapter.OnBoardingItemViewHolder>() {


    inner class OnBoardingItemViewHolder(private val binding: OnboardingItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onBoardingItem: OnBoardingItem) {
            binding.apply {
                imgOnBoarding.setImageResource(onBoardingItem.onBoardingImage)
                txtTitle.text = onBoardingItem.title
                txtDescription.text = onBoardingItem.description
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        val binding = OnboardingItemContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnBoardingItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])
    }
}