package ru.netology.nmedia.dto

data class Post(
    val id: Long = 0,
    val content: String = "",
    val published: String = "",
    val author: String = "",
    var likedByMy: Boolean = false,
    var likes: Int = 0,
    var shares: Int = 0

    )
