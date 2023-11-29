package com.example.registerfields_hw13.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.registerfields_hw13.Field
import com.example.registerfields_hw13.adapter.FieldAdapter
import com.example.registerfields_hw13.databinding.FragmentRegisterFieldsBinding
import com.example.registerfields_hw13.json_reader.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RegisterFieldsFragment : Fragment() {

    private var _binding: FragmentRegisterFieldsBinding? = null
    private val binding get() = _binding!!

    private lateinit var fields: List<Field>

    private lateinit var adapter: FieldAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterFieldsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fields = getData()

        setUpRecycler()


        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun setUpRecycler() {
        adapter = FieldAdapter().apply {
            submitList(fields)
        }
        binding.rvFields.adapter = adapter
    }

    private fun getData(): List<Field> {
        val json = getJsonDataFromAsset(requireContext(), "fields.json")

        val gson = Gson()

        val configurations: List<List<Field>> =
            gson.fromJson(json, object : TypeToken<List<List<Field>>>() {}.type)

        return configurations.flatten()
    }

    private fun register() {

    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}