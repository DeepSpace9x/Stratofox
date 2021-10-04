package com.deepspace.hab.screens.welcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deepspace.hab.R

/**
 * Created by abhinav on 03/10/21.
 */
class DisclaimerListAdapter :
    ListAdapter<String, DisclaimerListAdapter.DisclaimerViewHolder>(DisclaimerDiffCallback()) {

    class DisclaimerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_condition)
        fun onBind(condition: String) {
            title.text = condition
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DisclaimerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_disclaimer, parent, false)
        return DisclaimerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DisclaimerViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }

    private class DisclaimerDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.equals(newItem)
        }

    }
}