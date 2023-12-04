package com.example.registerfields_hw13.fragment

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.registerfields_hw13.adapter.RegisteredFieldAdapter
import com.example.registerfields_hw13.base.BaseFragment
import com.example.registerfields_hw13.databinding.FragmentRegisteredFieldsBinding
import com.example.registerfields_hw13.view_model.FieldViewModel

class RegisteredFieldsFragment :
    BaseFragment<FragmentRegisteredFieldsBinding>(FragmentRegisteredFieldsBinding::inflate) {

    private val fieldViewModel: FieldViewModel by activityViewModels()

    private val args: RegisteredFieldsFragmentArgs by navArgs()

    private lateinit var adapter: RegisteredFieldAdapter

    private lateinit var result: List<Pair<String, String>>

    override fun setData() {
        result = getList()
    }

    private fun getList(): List<Pair<String, String>> {
        return args.idList.toList().map { id ->
            val input = fieldViewModel.getFieldValue(id)
            input.first.hint to input.second
        }
    }


    override fun setRecycler() {
        adapter = RegisteredFieldAdapter().apply {
            fieldList = result
        }
        binding.rvRegisteredFields.adapter = adapter
    }
}