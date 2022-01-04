package com.ramoncinp.mydollars.ui.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ramoncinp.mydollars.data.database.transactions.Transaction
import com.ramoncinp.mydollars.data.database.transactions.TransactionType
import com.ramoncinp.mydollars.databinding.AddTransactionFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {

    private var _binding: AddTransactionFragmentBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: AddTransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddTransactionFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        viewModel.isTransactionDone.observe(this) {
            if (it) findNavController().navigateUp()
        }
    }

    private fun initViews() {
        binding.incomeButton.setOnClickListener {
            if (validateData()) createTransaction(TransactionType.INCOME)
        }
        binding.expenseButton.setOnClickListener {
            if (validateData()) createTransaction(TransactionType.EXPENSE)
        }
        binding.descriptionEt.addTextChangedListener {
            if (binding.descriptionInputLayout.error != null) {
                binding.descriptionInputLayout.error = null
            }
        }
        binding.amountEt.addTextChangedListener {
            if (binding.amountInputLayout.error != null) {
                binding.amountInputLayout.error = null
            }
        }
    }

    private fun validateData(): Boolean {
        val descriptionValid = binding.descriptionEt.text?.isNotEmpty() ?: false
        if (descriptionValid.not()) binding.descriptionInputLayout.error = "Field mandatory"

        val amountValid = binding.amountEt.text?.isNotEmpty() ?: false
        if (amountValid.not()) binding.amountInputLayout.error = "Field mandatory"

        return descriptionValid && amountValid
    }

    private fun createTransaction(transactionType: TransactionType) {
        val description = binding.descriptionEt.text.toString()
        val amount = binding.amountEt.text.toString().toDouble()
        val transaction = Transaction(
            description = description,
            amount = amount,
            type = transactionType.name
        )

        viewModel.createTransaction(transaction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
