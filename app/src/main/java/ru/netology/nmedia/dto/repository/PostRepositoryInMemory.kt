package ru.netology.nmedia.dto.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likedByMy = false,
        likes = 999,
        shares = 10998
    )
    private val _data = MutableLiveData(post)

    override fun getPost(): LiveData<Post> = _data

    override fun like() {
        post = if (!post.likedByMy) post.copy(likes = post.likes + 1)
        else post.copy(likes = post.likes - 1)
        post = post.copy(likedByMy = !post.likedByMy)
        _data.value = post
    }
}