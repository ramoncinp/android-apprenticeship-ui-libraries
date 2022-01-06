package com.ramoncinp.mydollars.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.ramoncinp.mydollars.R
import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.databinding.HomeFragmentBinding
import com.ramoncinp.mydollars.domain.formatters.toFormattedAmount

class HomeFragment : Fragment(R.layout.home_fragment) {

    private var _binding: HomeFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        setVariablesToBinding()
        return binding.root
    }

    private fun setVariablesToBinding() {
        binding.transactionsManager = TransactionsManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Init views
    }

    private fun initViews() {
        // TODO: Take views references and set them
        setBalanceData(TransactionsManager.balance)
        setTransactionsData(TransactionsManager.transactions)
    }

    private fun setBalanceData(balance: Double) {
        val formattedBalance = "\$${balance.toFormattedAmount()}"
        // TODO: Show balance data in UI
    }

    private fun setTransactionsData(transactions: List<Transaction>) {

    }

    private fun showNoTransactions() {
        // TODO: Show in UI that there are no transactions
    }
}
