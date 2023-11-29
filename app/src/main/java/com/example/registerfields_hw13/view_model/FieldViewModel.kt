package com.example.registerfields_hw13.view_model

import androidx.lifecycle.ViewModel

class FieldViewModel : ViewModel() {
    private val enteredFieldValues: MutableMap<Int, String> = mutableMapOf()

    fun setFieldValue(fieldId: Int, value: String) {
        enteredFieldValues[fieldId] = value
    }

    fun getFieldValue(fieldId: Int): String {
        return enteredFieldValues[fieldId] ?: ""
    }
}