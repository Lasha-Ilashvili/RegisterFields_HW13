package com.example.registerfields_hw13.fragment

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.registerfields_hw13.adapter.FieldAdapter
import com.example.registerfields_hw13.base.BaseFragment
import com.example.registerfields_hw13.databinding.FragmentRegisterFieldsBinding
import com.example.registerfields_hw13.json_reader.getJsonDataFromAsset
import com.example.registerfields_hw13.model.Field
import com.example.registerfields_hw13.view_model.FieldViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RegisterFieldsFragment :
    BaseFragment<FragmentRegisterFieldsBinding>(FragmentRegisterFieldsBinding::inflate) {

    private lateinit var fields: List<List<Field>>

    private lateinit var adapter: FieldAdapter

    private val fieldViewModel: FieldViewModel by activityViewModels()

    override fun setData() {
        fields = getData()
    }

    private fun getData(): List<List<Field>> {
        val json = getJsonDataFromAsset(requireContext(), "fields.json")

        return Gson().fromJson(json, object : TypeToken<List<List<Field>>>() {}.type)
    }


    override fun setRecycler() {
        binding.rvFields.adapter = FieldAdapter().apply {
            itemSave = ::addFieldValues
            setFieldData(fields)
        }
    }

    private fun addFieldValues(position: Int, innerPosition: Int, input: String) {
        val field = fields[position][innerPosition]
        fieldViewModel.setFieldValue(field.id, Pair(field, input))
    }

    override fun setListeners() {
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val fieldIdList: MutableList<Int> = mutableListOf()

        fieldViewModel.enteredFieldValues.forEach { (key, value) ->
            val (field, input) = value

            if (field.required && input.isEmpty()) {
                showToast("${field.hint} is required.")
                return
            }

            if (input.isNotEmpty()) {
                fieldIdList.add(key)
            }

        }

        startNewFragment(fieldIdList)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun startNewFragment(idList: List<Int>) {
        findNavController().navigate(
            RegisterFieldsFragmentDirections.actionRegisterFieldsFragmentToRegisteredFieldsFragment(
                idList = idList.toIntArray()
            )
        )
    }
}