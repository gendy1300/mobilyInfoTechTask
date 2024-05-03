package com.gendy.bugIt.home.data.model

data class SpreadSheetResponseData(
    val sheets: List<Sheet>? = listOf(),
    val spreadsheetId: String? = "",
    val spreadsheetUrl: String? = ""
)

data class Sheet(
    val properties: PropertiesData? = PropertiesData()
)

data class PropertiesData(
    val index: Int? = null,
    val sheetId: String? = null,
    val sheetType: String? = null,
    val title: String? = null,
    val gridProperties: GridData? = null
)


data class GridData(
    val rowCount: Int,
    val columnCount: Int
)