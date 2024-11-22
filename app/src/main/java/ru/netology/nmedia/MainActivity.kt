package ru.netology.nmedia

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import java.util.Locale
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ик в нутри рпиложения
        val avatar: ImageView = findViewById(R.id.avatar)
        avatar.setImageResource(R.drawable.ic_netology_48dp)


        var post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb ",
            likedByMy = false,
            likes = 999,
            shares = 900,
        )

        // Инициализация значений для отображения
        binding.likeCount.text = formatCount(post.likes)
        binding.shareCount.text = formatCount(post.shares)

        binding.content.text = post.content
        binding.published.text = post.published
        binding.author.text = post.author

        // Клик на like
        binding.like.setOnClickListener {
            val updatedPost = post.copy(
                likedByMy = !post.likedByMy,
                likes = if (post.likedByMy) post.likes - 1 else post.likes + 1
            )
            post = updatedPost
            binding.likeCount.text = formatCount(updatedPost.likes)
            updateLikeImage(binding, updatedPost)
            //        Log.d("MainActivity", "Клик на like")

        }

        // Клик на share
        binding.share.setOnClickListener {
            post.shares++

            binding.shareCount.text = formatCount(post.shares)
            //  Log.d("MainActivity", "Клик на share")
        }

        // Обработчик на корневой элемент (binding.root) - выводит LOG если нажат любой другой элемент
        binding.root.setOnClickListener {
            //     Log.d("MainActivity", "Клик на root")

        }

        binding.avatar.setOnClickListener {
            //     Log.d("MainActivity", "Клик на аватар")

        }

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
    //   исправленная версия:

    private fun formatCount(count: Int): String {
        return when {
            count < 1000 -> count.toString()
            count < 1000000 -> {
                val bd = BigDecimal(count.toDouble() / 1000.0)
                val formatted = bd.setScale(1, RoundingMode.FLOOR).toDouble()
                String.format(Locale.getDefault(), "%.1fK", formatted)
            }
            else -> {
                val bd = BigDecimal(count.toDouble() / 1000000.0)
                val formatted = bd.setScale(1, RoundingMode.FLOOR).toDouble()
                String.format(Locale.getDefault(), "%.1fM", formatted)
            }
        }
    }


}