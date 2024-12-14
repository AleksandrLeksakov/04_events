package ru.netology.nmedia.dto.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getPost(): LiveData<Post>
    fun like()
    fun share()
}