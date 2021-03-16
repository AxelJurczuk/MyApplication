package com.example.android.myapplication.home_activity.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.myapplication.commons.BaseFragment
import com.example.android.myapplication.databinding.FragmentDetailBinding
import com.example.android.myapplication.utils.SharedTransactionVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment: BaseFragment() {

    private val sharedTransactionVM: SharedTransactionVM by sharedViewModel()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction = sharedTransactionVM.transaction.value
        transaction?.let {
            binding.tvDetails.text =
                    "${transaction.id} ${transaction.description}${transaction.amount} " +
                            "${transaction.fee} ${transaction.total}"
            //${transaction.date}
        }
    }
    override fun loadObservers() {
    }

}