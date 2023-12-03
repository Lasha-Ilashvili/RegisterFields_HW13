package com.example.registerfields_hw13.view_model

import androidx.lifecycle.ViewModel
import com.example.registerfields_hw13.model.Field

class FieldViewModel : ViewModel() {
    private val _enteredFieldValues: MutableMap<Int, Pair<Field, String>> = mutableMapOf()
    val enteredFieldValues get() = _enteredFieldValues.toMap()

    fun setFieldValue(fieldId: Int, value: Pair<Field, String>) {
        _enteredFieldValues[fieldId] = value
    }

    fun getFieldValue(fieldId: Int): Pair<Field, String> {
        return _enteredFieldValues[fieldId]!!
    }
}