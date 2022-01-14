package com.ramoncinp.mydollars.ui.transaction

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.ramoncinp.mydollars.R
import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.TransactionType
import com.ramoncinp.mydollars.databinding.AddTransactionFragmentBinding
import com.ramoncinp.mydollars.utils.hideKeyboard
import timber.log.Timber

class AddTransactionFragment : Fragment(), AddTransactionContract.View {

    private val addTransactionPresenter = AddTransactionPresenter(TransactionsManager, this)
    private var _binding: AddTransactionFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getCurrentBalance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddTransactionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getCurrentBalance() {
        val arguments = AddTransactionFragmentArgs.fromBundle(requireArguments())
        val currentBalance = arguments.currentBalance
        Timber.d("Current balance is $currentBalance")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        val description = binding.descriptionEt.text.toString()
        val descriptionValid = addTransactionPresenter.validateDescription(description)
        if (descriptionValid.not()) binding.descriptionInputLayout.error = "Field mandatory"

        val amount = binding.amountEt.text.toString()
        val amountValid = addTransactionPresenter.validateAmount(amount)
        if (amountValid.not()) binding.amountInputLayout.error = "Amount not valid"

        return descriptionValid && amountValid
    }

    private fun createTransaction(transactionType: TransactionType) {
        val description = binding.descriptionEt.text.toString()
        val amount = binding.amountEt.text.toString().toDouble()
        addTransactionPresenter.addTransaction(description, amount, transactionType)
    }

    override fun transactionCreated() {
        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                requireActivity().hideKeyboard()
            }

            override fun onAnimationEnd(p0: Animator?) {
                findNavController().navigateUp()
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
        })

        binding.animationView.setAnimation(R.raw.anim_money)
        binding.animationView.playAnimation()
        binding.animationView.visibility = View.VISIBLE
    }
}
