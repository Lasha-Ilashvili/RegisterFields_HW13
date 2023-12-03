package com.example.registerfields_hw13.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.registerfields_hw13.adapter.RegisteredFieldAdapter
import com.example.registerfields_hw13.databinding.FragmentRegisteredFieldsBinding
import com.example.registerfields_hw13.view_model.FieldViewModel

class RegisteredFieldsFragment : Fragment() {

    private val fieldViewModel: FieldViewModel by activityViewModels()

    private val args: RegisteredFieldsFragmentArgs by navArgs()

    private var _binding: FragmentRegisteredFieldsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RegisteredFieldAdapter

    private lateinit var result: List<Pair<String, String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        result = getList()
    }

    private fun getList(): List<Pair<String, String>> {
        return args.idList.toList().map { id ->
            val input = fieldViewModel.getFieldValue(id)
            input.first.hint to input.second
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisteredFieldsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
    }

    private fun setUpRecycler() {
        adapter = RegisteredFieldAdapter().apply {
            fieldList = result
        }
        binding.rvRegisteredFields.adapter = adapter
    }
}