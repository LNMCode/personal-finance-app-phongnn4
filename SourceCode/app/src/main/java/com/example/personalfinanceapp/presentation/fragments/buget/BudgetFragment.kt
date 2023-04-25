package com.example.personalfinanceapp.presentation.fragments.buget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentBillsListBinding
import com.example.personalfinanceapp.databinding.FragmentBudgetBinding
import com.example.personalfinanceapp.databinding.FragmentDashBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetFragment : Fragment() {

    private lateinit var binding: FragmentBudgetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

}