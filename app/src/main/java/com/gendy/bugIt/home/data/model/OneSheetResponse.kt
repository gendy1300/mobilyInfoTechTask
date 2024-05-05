package com.gendy.bugIt.home.data.model

data class OneSheetResponse(
    val majorDimension: String? = null,
    val range: String? = null,
    val values: List<List<String?>?>? = null
)