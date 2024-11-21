package ru.netology.nmedia.dto

data class Post(
    val id: Int,
    val content: String,
    val published: String,
    val author: String,
    val likedByMy: Boolean,
    var likes: Int,
    var shares: Int,

    )
