package com.ramoncinp.mydollars.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.databinding.HomeFragmentBinding

class HomeFragment : Fragment(), HomeInteractor {

    private val homeController = HomePresenter(TransactionsManager, this)

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
        getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        setViewListeners()
    }

    private fun setViewListeners() {
        binding.addTransactionButton.setOnClickListener { navigateToAddTransaction() }
    }

    private fun getData() {
        homeController.getBalanceData()
        homeController.getTransactions()
    }

    override fun setBalanceData(balance: String) {
        binding.balanceTv.text = balance
    }

    override fun setTransactionsData(transactions: List<Transaction>) {
        val transactionsAdapter = TransactionsAdapter()
        binding.transactionsList.apply {
            adapter = transactionsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        transactionsAdapter.submitList(transactions)
    }

    override fun noTransactionsData() {
        showNoTransactions()
    }

    private fun showNoTransactions() {
        binding.noTransactionsTv.visibility = View.VISIBLE
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
