package com.deepspace.hab.screens.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deepspace.domain.models.ModuleSection
import com.deepspace.hab.R

/**
 * Created by abhinav on 01/10/21.
 */
class LessonListAdapter(private val onClick: (ModuleSection) -> Unit) :
    ListAdapter<ModuleSection, LessonListAdapter.LessonViewHolder>(LessonDiffCallback()) {

    class LessonViewHolder(itemView: View, val onClick: (ModuleSection) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_lesson_title)
        private val lessonDuration: TextView = itemView.findViewById(R.id.tv_lesson_duration)
        private val playBtn: ImageView = itemView.findViewById(R.id.iv_playBtn)

        private var currentModuleSection: ModuleSection? = null

        init {
            itemView.setOnClickListener {
                currentModuleSection?.let {
                    onClick(it)
                }
            }
        }

        fun onBind(moduleSection: ModuleSection) {
            currentModuleSection = moduleSection
            title.text = moduleSection.title
            lessonDuration.text = moduleSection.duration
            playBtn.setImageResource(getPlayBtn(moduleSection.moduleNo))
        }

        private fun getPlayBtn(moduleNo: Int): Int {
            return when (moduleNo) {
                1 -> R.drawable.ic_play_purple
                2 -> R.drawable.ic_play_light_orange
                3 -> R.drawable.ic_play_green
                4 -> R.drawable.ic_play_red
                else -> R.drawable.ic_play_purple
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class LessonDiffCallback : DiffUtil.ItemCallback<ModuleSection>() {
        override fun areItemsTheSame(oldItem: ModuleSection, newItem: ModuleSection): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ModuleSection, newItem: ModuleSection): Boolean =
            oldItem.id == newItem.id
    }

}