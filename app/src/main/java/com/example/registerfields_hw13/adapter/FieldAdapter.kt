package com.example.registerfields_hw13.adapter

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.registerfields_hw13.Field
import com.example.registerfields_hw13.databinding.FieldItemBinding

class FieldAdapter : ListAdapter<Field, FieldAdapter.FieldViewHolder>(FieldDiffUtil) {

    object FieldDiffUtil : DiffUtil.ItemCallback<Field>() {
        override fun areItemsTheSame(oldItem: Field, newItem: Field): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Field, newItem: Field): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        return FieldViewHolder(
            FieldItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        holder.bind()
    }

    inner class FieldViewHolder(private val binding: FieldItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val field = currentList[adapterPosition]

            with(binding) {
                edField.apply {
                    hint = field.hint
                    inputType = getInputType(field.keyboard)
                }
            }
        }


        private fun getInputType(keyboard: String?): Int {
            return when (keyboard) {
                "text" -> InputType.TYPE_CLASS_TEXT
                "number" -> InputType.TYPE_CLASS_NUMBER

                else -> InputType.TYPE_CLASS_TEXT
            }
        }
    }
}
