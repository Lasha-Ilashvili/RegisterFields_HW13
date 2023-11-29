package com.example.registerfields_hw13.model

data class Field(
    val field_id: Int,
    val hint: String,
    val field_type: String,
    val keyboard: String,
    val required: Boolean,
    val is_active: Boolean,
    val icon: String
)