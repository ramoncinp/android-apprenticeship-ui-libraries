package com.ramoncinp.mydollars.ui.home

import com.ramoncinp.mydollars.data.database.transactions.Transaction
import com.ramoncinp.mydollars.databinding.TransactionLayoutBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramoncinp.mydollars.domain.formatters.simpleDateFormatter
import com.ramoncinp.mydollars.domain.formatters.toFormattedAmount
import java.util.*

class TransactionsAdapter : ListAdapter<Transaction,
        TransactionsAdapter.ViewHolder>(StationsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = getItem(position)
        holder.bind(transaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: TransactionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Transaction) {
            val amountText = "\$${item.amount.toFormattedAmount()}"
            binding.amountTv.text = amountText
            binding.descriptionTv.text = item.description
            binding.datetimeTv.text = simpleDateFormatter().format(Date(item.date))
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TransactionLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class StationsDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }
}
