package com.gendy.bugIt.addBug.data.model

data class AddBugRequest(
    val requests: List<AddSheetRequest>
)

data class AddSheetRequest(
    val addSheet: AddSheet
)

data class AddSheet(
    val properties: SheetProperties
)

data class SheetProperties(
    val title: String
)