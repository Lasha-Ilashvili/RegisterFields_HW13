package com.example.registerfields_hw13

import com.google.gson.annotations.SerializedName

data class Field(
    @SerializedName("field_id") val id: Int,
    val hint: String,
    @SerializedName("field_type") val type: String,
    val keyboard: String?,
    val required: Boolean,
    @SerializedName("is_active") val isActive: Boolean,
    val icon: String
)