package com.example.registerfields_hw13.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.registerfields_hw13.adapter.FieldAdapter
import com.example.registerfields_hw13.databinding.FragmentRegisterFieldsBinding
import com.example.registerfields_hw13.json_reader.getJsonDataFromAsset
import com.example.registerfields_hw13.model.Field
import com.example.registerfields_hw13.view_model.FieldViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RegisterFieldsFragment : Fragment() {

    private var _binding: FragmentRegisterFieldsBinding? = null
    private val binding get() = _binding!!

    private lateinit var fields: List<Field>

    private lateinit var adapter: FieldAdapter

    private val fieldViewModel: FieldViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fields = getData()
    }

    private fun getData(): List<Field> {
        val json = getJsonDataFromAsset(requireContext(), "fields.json")

        val configurations: List<List<Field>> =
            Gson().fromJson(json, object : TypeToken<List<List<Field>>>() {}.type)

        return configurations.flatten()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterFieldsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()

        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun setUpRecycler() {
        adapter = FieldAdapter().apply {
            itemOnType = ::addFieldValues
            submitList(fields)
        }
        binding.rvFields.adapter = adapter
    }

    private fun addFieldValues(position: Int, input: String) {
        val field = fields[position]
        fieldViewModel.setFieldValue(field.id, Pair(field, input))
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}