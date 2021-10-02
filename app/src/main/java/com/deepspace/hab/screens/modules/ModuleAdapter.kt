package com.deepspace.hab.screens.modules

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deepspace.domain.models.Module
import com.deepspace.hab.R
import com.google.android.material.card.MaterialCardView

/**
 * Created by abhinav on 30/09/21.
 */
class ModuleAdapter(private val onClick: (Module) -> Unit) :
    ListAdapter<Module, ModuleAdapter.ModuleViewHolder>(ModuleDiffCallback()) {

    class ModuleViewHolder(itemView: View, val onClick: (Module) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val moduleTitle: TextView = itemView.findViewById(R.id.tv_module_1_title)
        private val moduleNo: TextView = itemView.findViewById(R.id.tv_moduleNo)
        private val moduleImage: ImageView = itemView.findViewById(R.id.iv_moduleImage)
        private val moduleNoInWords: TextView = itemView.findViewById(R.id.tv_moduleNoInWords)
        private val moduleTime: TextView = itemView.findViewById(R.id.tv_moduleTime)
        private val moduleBgColor: MaterialCardView =
            itemView.findViewById(R.id.card_moduleContainer)
        private val startBtn: TextView = itemView.findViewById(R.id.tv_startBtn)

        private var currentModule: Module? = null

        init {
            startBtn.setOnClickListener {
                currentModule?.let {
                    onClick(it)
                }
            }
        }

        fun onBind(module: Module) {

//            if (Utils.isDrawableAvailable(itemView.context, module.imageId)) {
//                moduleImage.setImageDrawable(Utils.getDrawable(itemView.context, module.imageId))
//            } else {
//                moduleImage.setImageResource(R.drawable.bg_module_1)
//            }
            currentModule = module
            setupTextualContent(module)
            setupColors(module)
        }

        private fun setupTextualContent(module: Module) {
            val moduleNoText = "0" + module.rank
            val moduleNoInWordsString = "Module " + module.rank

            moduleTitle.text = module.title
            moduleNoInWords.text = moduleNoInWordsString
            moduleTime.text = module.moduleDuration
            moduleNo.text = moduleNoText
        }

        private fun setupColors(module: Module) {
            moduleImage.setImageResource(getImageDrawable(module.rank))
            moduleBgColor.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    getBackgroundColorID(module.rank)
                )
            )
            ContextCompat.getColor(
                itemView.context,
                getTextColor(module.rank)
            ).apply {
                moduleTitle.setTextColor(this)
                moduleNoInWords.setTextColor(this)
                moduleTime.setTextColor(this)
            }
            moduleNo.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    getModuleNoColor(module.rank)
                )
            )
            startBtn.setBackgroundResource(getStartBtnBg(module.rank))
            startBtn.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    getStartBtnTextColor(module.rank)
                )
            )
        }

        private fun getImageDrawable(rank: Int): Int {
            return when (rank) {
                1 -> R.drawable.ic_module_1
                2 -> R.drawable.ic_module_2
                3 -> R.drawable.ic_module_3
                4 -> R.drawable.ic_module_4
                else -> R.drawable.ic_module_1
            }
        }

        private fun getBackgroundColorID(rank: Int): Int {
            return when (rank) {
                1 -> R.color.bg_module1
                2 -> R.color.bg_module2
                3 -> R.color.bg_module3
                4 -> R.color.bg_module4
                else -> R.color.bg_module1
            }
        }

        private fun getTextColor(rank: Int): Int {
            return when (rank) {
                1, 4 -> R.color.White
                2, 3 -> R.color.DarkGrey
                else -> R.color.White
            }
        }

        private fun getModuleNoColor(rank: Int): Int {
            return when (rank) {
                1, 4 -> R.color.White80
                2, 3 -> R.color.DarkGrey80
                else -> R.color.White80
            }
        }

        private fun getStartBtnBg(rank: Int): Int {
            return when (rank) {
                1, 3 -> R.drawable.bg_start_btn_greywhite
                2, 4 -> R.drawable.bg_start_btn_darkgrey
                else -> R.drawable.bg_start_btn_darkgrey
            }
        }

        private fun getStartBtnTextColor(rank: Int): Int {
            return when (rank) {
                1, 3 -> R.color.DarkGrey
                2, 4 -> R.color.White
                else -> R.color.White
            }
        }
    }

    private class ModuleDiffCallback : DiffUtil.ItemCallback<Module>() {
        override fun areItemsTheSame(oldItem: Module, newItem: Module): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Module, newItem: Module): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_module, parent, false)
        return ModuleViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}