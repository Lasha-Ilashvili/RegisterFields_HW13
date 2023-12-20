package com.example.registerfields_hw13.adapter

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.registerfields_hw13.databinding.FieldItemBinding
import com.example.registerfields_hw13.model.Field

class InnerFieldAdapter : RecyclerView.Adapter<InnerFieldAdapter.InnerFieldViewHolder>() {

    var itemOnType: ((Int, String) -> Unit)? = null

    private lateinit var currentInnerList: List<Field>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerFieldViewHolder {
        return InnerFieldViewHolder(
            FieldItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setInnerFieldData(newList: List<Field>) {
        currentInnerList = newList
    }

    override fun getItemCount(): Int {
        return currentInnerList.size
    }

    override fun onBindViewHolder(holder: InnerFieldViewHolder, position: Int) {
        holder.bind()
    }

    inner class InnerFieldViewHolder(private val binding: FieldItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.edField.doOnTextChanged { input, _, _, _ ->
                itemOnType?.invoke(adapterPosition, input.toString())
            }
        }

        fun bind() {
            val field = currentInnerList[adapterPosition]

            binding.edField.apply {
                hint = field.hint
                inputType = getInputType(field.keyboard)
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