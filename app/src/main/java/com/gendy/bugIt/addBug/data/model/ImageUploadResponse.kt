package com.gendy.bugIt.addBug.data.model

data class ImageUploadResponse(
    val data: ImageUploadData? = null,
    val status: Int? = null,
    val success: Boolean? = null
)

data class ImageUploadData(
    val delete_url: String? = null,
    val display_url: String? = null,
    val expiration: Int? = null,
    val height: Int? = null,
    val id: String? = null,
    val image: ImageUploadImageData? = null,
    val size: Int? = null,
    val thumb: ImageUploadImageData? = null,
    val time: Int? = null,
    val title: String? = null,
    val url: String? = null,
    val url_viewer: String? = null,
    val width: Int? = null
)


data class ImageUploadImageData(
    val extension: String? = null,
    val filename: String? = null,
    val mime: String? = null,
    val name: String? = null,
    val url: String? = null
)
