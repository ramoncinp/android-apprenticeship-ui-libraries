package com.ramoncinp.mydollars.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramoncinp.mydollars.R
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.databinding.TransactionLayoutBinding
import com.ramoncinp.mydollars.domain.formatters.toFormattedAmount
import com.ramoncinp.mydollars.domain.formatters.toFormattedDate
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
            binding.apply {
                amountTv.text = amountText
                descriptionTv.text = item.description
                datetimeTv.text = Date(item.date).toFormattedDate()
                typeTv.text = item.type.uppercase()
            }
            showImage(item)
        }

        private fun showImage(item: Transaction) {
            val image = "https://picsum.photos/seed/${item.date}/200"
            Glide.with(binding.root).load(image)
                .placeholder(R.drawable.ic_cash)
                .into(binding.transactionImage)
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
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }
}
