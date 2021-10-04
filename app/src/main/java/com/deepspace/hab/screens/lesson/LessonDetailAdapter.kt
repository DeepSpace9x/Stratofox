package com.deepspace.hab.screens.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deepspace.hab.R

/**
 * Created by abhinav on 03/10/21.
 */
class LessonDetailAdapter :
    ListAdapter<LessonDetailFragment.Content, LessonDetailAdapter.LessonViewHolder>(
        LessonDiffCallback()
    ) {

    class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val heading: TextView = itemView.findViewById(R.id.tv_subheading)
        private val contentText: TextView = itemView.findViewById(R.id.tv_subheading_content)
        private val contentImage: ImageView = itemView.findViewById(R.id.iv_subheading)
        fun onBind(content: LessonDetailFragment.Content) {
            heading.text = content.heading
            contentText.text = content.content
            if (content.imageDrawableId != null) {
                contentImage.visibility = View.VISIBLE
                contentImage.setImageResource(content.imageDrawableId)
            } else {
                contentImage.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonDetailAdapter.LessonViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lesson_detail, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonDetailAdapter.LessonViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class LessonDiffCallback : DiffUtil.ItemCallback<LessonDetailFragment.Content>() {
        override fun areItemsTheSame(
            oldItem: LessonDetailFragment.Content,
            newItem: LessonDetailFragment.Content
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: LessonDetailFragment.Content,
            newItem: LessonDetailFragment.Content
        ): Boolean {
            return oldItem.heading.equals(newItem.heading)
        }

    }

}