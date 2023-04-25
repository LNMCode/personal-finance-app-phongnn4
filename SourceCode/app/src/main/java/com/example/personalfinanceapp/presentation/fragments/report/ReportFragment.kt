package com.example.personalfinanceapp.presentation.fragments.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentPaymentBinding
import com.example.personalfinanceapp.databinding.FragmentReportBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

}