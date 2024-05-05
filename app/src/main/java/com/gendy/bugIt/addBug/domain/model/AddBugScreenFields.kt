package com.gendy.bugIt.addBug.domain.model

import java.io.File
import java.time.LocalDate

data class AddBugScreenFields(
    val title: String? = null,
    var photoFile: File? = null,
    val description: String? = null,
    val date: LocalDate? = null,
)
