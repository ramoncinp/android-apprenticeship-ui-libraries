package com.ramoncinp.mydollars.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.databinding.HomeFragmentBinding
import com.ramoncinp.mydollars.domain.formatters.toFormattedAmount
import timber.log.Timber

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        Timber.d("Transactions -> ${TransactionsManager.transactions}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        setViewListeners()
        setBalanceData(TransactionsManager.balance)
        setTransactionsData(orderTransactions(TransactionsManager.transactions))
    }

    private fun setBalanceData(balance: Double) {
        val formattedBalance = "\$${balance.toFormattedAmount()}"
        binding.balanceTv.text = formattedBalance
    }

    private fun orderTransactions(transactions: List<Transaction>) =
        transactions.sortedByDescending { it.date }

    private fun setTransactionsData(transactions: List<Transaction>) {
        if (transactions.isEmpty()) {
            showNoTransactions()
            return
        }

        val transactionsAdapter = TransactionsAdapter()
        binding.transactionsList.apply {
            adapter = transactionsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        transactionsAdapter.submitList(transactions)
    }

    private fun showNoTransactions() {
        binding.noTransactionsTv.visibility = View.VISIBLE
    }

    private fun setViewListeners() {
        binding.addTransactionButton.setOnClickListener { navigateToAddTransaction() }
    }

    private fun navigateToAddTransaction() {
        val currentBalance = TransactionsManager.balance
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToAddTransactionFragment(
                currentBalance.toFloat()
            )
        )
    }
}
