package ru.netology.nmedia

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ик в нутри рпиложения
        val avatar: ImageView = findViewById(R.id.avatar)
        avatar.setImageResource(R.drawable.ic_netology_48dp)


        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb ",
            likedByMy = false,
            likes = 999,
            shares = 999,
        )

        // Инициализация значений для отображения
        binding.likeCount.text = formatCount(post.likes)
        binding.shareCount.text = formatCount(post.shares)

        binding.content.text = post.content
        binding.published.text = post.published
        binding.author.text = post.author

        // Клик на like
        binding.like.setOnClickListener {
            post.likedByMy = !post.likedByMy
            post.likes = if (post.likedByMy) post.likes + 1 else post.likes - 1
            binding.likeCount.text = formatCount(post.likes)
                  updateLikeImage(binding, post)
            //        Log.d("MainActivity", "Клик на like")

        }

        // Клик на share
        binding.share.setOnClickListener {
            post.shares++
            binding.shareCount.text = formatCount(post.shares)
            Log.d("MainActivity", "Клик на share")
        }

        // Обработчик на корневой элемент (binding.root) - выводит LOG если нажат любой другой элемент
        //    binding.root.setOnClickListener {
        //       Log.d("MainActivity", "Клик на root")
        //   }

        //   binding.avatar.setOnClickListener {
        //         Log.d("MainActivity", "Клик на аватар")
        //    }

        updateLikeImage(binding, post) // Обновляем картинку лайка при запуске
    }


    private fun updateLikeImage(binding: ActivityMainBinding, post: Post) {
        binding.like.setImageResource(
            if (post.likedByMy) {
                R.drawable.ic_baseline_favorite_24
            } else {
                R.drawable.ic_baseline_favorite_border_24
            }
        )
    }


    private fun formatCount(count: Int): String {
        return when {
            count < 1000 -> count.toString()
            count < 10000 -> (count / 1000).toString() + "K"
            count < 1000000 -> (count / 1000).toString() + "K"
            else -> (count / 1000000).toString() + "M"
        }
    }
}