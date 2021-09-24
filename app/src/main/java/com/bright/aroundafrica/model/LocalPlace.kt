package com.bright.aroundafrica.model

data class LocalPlace(
    var id: String,
    var albumId: String,
    var title: String,
    var url: String,
    var thumbnailUrl: String
) {
    override fun toString(): String {
        return "{ title:$title, thumbnailUrl:$thumbnailUrl  }"
    }
}