package ru.netology.nmedia.dto

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.repository.PostRepository
import ru.netology.nmedia.dto.repository.PostRepositoryInMemory

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()

    val data = repository.getPost()

    fun like() = repository.like()
    fun share() = repository.share()
}




