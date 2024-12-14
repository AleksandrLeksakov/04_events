package ru.netology.nmedia.dto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.repository.PostRepository
import ru.netology.nmedia.dto.repository.PostRepositoryInMemory

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()

    private var _likeText = MutableLiveData(formatCount(repository.getPost().value?.likes ?: 0))
    val likeText: LiveData<String> = _likeText

    private var _shareCount = MutableLiveData(repository.getPost().value?.shares)
    private var _shareText = MutableLiveData(formatCount(_shareCount.value ?: 0))
    val shareText: LiveData<String> = _shareText

    fun updateLikeText() {
        repository.like()
        val likes = repository.getPost().value?.let { it.likes } ?: 0
        _likeText.value = formatCount(likes)
    }

    fun updateShareText() {
        _shareCount.value?.let {
            val result = it + 1
            _shareCount.value = result
            _shareText.value = formatCount(result)
            result
        }
    }

    val data = repository.getPost()


    private fun formatCount(count: Int): String {
        return when {
            count == 1000 -> "${(count / 1000)} K"
            count in 1000 until 999999 -> {
                val thousands = count/1000
                val reminders = (count % 1000) / 100
                if (reminders > 0) "$thousands.${reminders} K" else "$thousands K"
            }
            else -> count.toString()
        }
    }
}




