package com.ramoncinp.mydollars.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ramoncinp.mydollars.data.database.transactions.Transaction
import com.ramoncinp.mydollars.databinding.HomeFragmentBinding
import com.ramoncinp.mydollars.domain.formatters.toFormattedAmount
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: TransactionsAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        viewModel.getBalance()
        viewModel.getTransactions()
    }

    private fun initViews() {
        binding.addTransactionButton.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAddTransactionFragment()
            )
        }

        adapter = TransactionsAdapter()
        binding.transactionsList.adapter = adapter
    }

    private fun initObservers() {
        viewModel.accountBalance.observe(this) { setBalanceData(it) }
        viewModel.transactions.observe(this) {
            setTransactionsData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setBalanceData(balance: Double) {
        val formattedBalance = "\$${balance.toFormattedAmount()}"
        binding.balanceTv.text = formattedBalance
    }

    private fun setTransactionsData(transactions: List<Transaction>) {
        adapter.submitList(transactions)
    }
}
