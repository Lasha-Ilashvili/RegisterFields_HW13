package com.example.registerfields_hw13.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registerfields_hw13.databinding.RegisterItemBinding

class RegisteredFieldAdapter :
    RecyclerView.Adapter<RegisteredFieldAdapter.RegisteredFieldViewHolder>() {

    var fieldList: List<Pair<String, String>>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisteredFieldViewHolder {
        return RegisteredFieldViewHolder(
            RegisterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return fieldList!!.size
    }

    override fun onBindViewHolder(holder: RegisteredFieldViewHolder, position: Int) {
        holder.bind()
    }

    inner class RegisteredFieldViewHolder(private val binding: RegisterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val field = fieldList!![adapterPosition]

            with(binding) {
                tvField.text = "${field.first} : ${field.second}"
            }
        }
    }
}