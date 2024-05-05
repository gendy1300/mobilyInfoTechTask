package com.gendy.bugIt.addBug.domain.model

import java.io.File

data class AddBugScreenFields(
    val title: String? = null,
    var photoFile: File? = null,
    val description: String? = null,
)
