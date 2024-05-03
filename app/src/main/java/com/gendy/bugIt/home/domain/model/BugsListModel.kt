package com.gendy.bugIt.home.domain.model

data class BugsListModel (
    val id:String,
    val title:String,
    val description:String,
    val imageUrl:String,
    val reporterName:String,
    val date:String
)