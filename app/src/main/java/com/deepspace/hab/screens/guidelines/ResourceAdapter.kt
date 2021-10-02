package com.deepspace.hab.screens.guidelines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deepspace.hab.R
import com.google.android.material.card.MaterialCardView

/**
 * Created by abhinav on 01/10/21.
 */

class ResourceAdapter :
    ListAdapter<GuidelinesFragment.Resource, ResourceAdapter.ResourceViewHolder>(
        ResourceDiffCallback()
    ) {

    class ResourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val resourceContainer: MaterialCardView =
            itemView.findViewById(R.id.card_resourceContainer)
        private val title: TextView = itemView.findViewById(R.id.tv_resource_title)
        private val subtitle: TextView = itemView.findViewById(R.id.tv_resource_subtitle)
        private val image: ImageView = itemView.findViewById(R.id.iv_resourceImage)
        private val readBtn: TextView = itemView.findViewById(R.id.tv_readBtn)
        fun onBind(resource: GuidelinesFragment.Resource) {
            setupTextualContent(resource)
            setupColors(resource)
        }

        private fun setupTextualContent(resource: GuidelinesFragment.Resource) {
            title.text = resource.title
            subtitle.text = resource.subtitle
        }

        private fun setupColors(resource: GuidelinesFragment.Resource) {
            image.setImageResource(resource.imageId)
            resourceContainer.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    resource.bgColor
                )
            )
            ContextCompat.getColor(
                itemView.context,
                resource.textColor
            ).apply {
                title.setTextColor(this)
                subtitle.setTextColor(this)
            }
            readBtn.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    resource.readBtnTextColor
                )
            )
            readBtn.setBackgroundResource(resource.readBtnBgDrawable)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResourceViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        return ResourceViewHolder(inflator.inflate(R.layout.cardview_resources, parent, false))
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class ResourceDiffCallback : DiffUtil.ItemCallback<GuidelinesFragment.Resource>() {
        override fun areItemsTheSame(
            oldItem: GuidelinesFragment.Resource,
            newItem: GuidelinesFragment.Resource
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: GuidelinesFragment.Resource,
            newItem: GuidelinesFragment.Resource
        ): Boolean =
            oldItem.id == newItem.id
    }

}