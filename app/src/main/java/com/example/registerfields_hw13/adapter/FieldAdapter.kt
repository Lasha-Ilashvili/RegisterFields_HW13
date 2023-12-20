package com.example.registerfields_hw13.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registerfields_hw13.databinding.InnerRegisterFieldsBinding
import com.example.registerfields_hw13.model.Field

class FieldAdapter : RecyclerView.Adapter<FieldAdapter.FieldViewHolder>() {

    var itemSave: ((Int, Int, String) -> Unit)? = null

    private lateinit var currentList: List<List<Field>>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        return FieldViewHolder(
            InnerRegisterFieldsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = currentList.size

    fun setFieldData(newList: List<List<Field>>) {
        currentList = newList
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        holder.bind()
    }

    inner class FieldViewHolder(private val binding: InnerRegisterFieldsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind() {
            val fields = currentList[adapterPosition]
            binding.rvInnerFields.adapter = InnerFieldAdapter().apply {
                itemOnType = { position, input ->
                    itemSave?.invoke(adapterPosition, position, input)
                }

                setInnerFieldData(fields)
            }
        }
    }
}
