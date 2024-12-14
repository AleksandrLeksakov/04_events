package ru.netology.nmedia

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.PostViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.avatar.setImageResource(R.drawable.ic_netology_48dp)
        viewModel.data.observe(this) { post ->
            Log.d(TAG, "onCreate: $post")
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                if (post.likedByMy) like.setImageResource(R.drawable.ic_baseline_favorite_24)
                else like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
        viewModel.likeText.observe(this) { text ->
            with(binding) {
                likeCount.text = text
            }
        }
        viewModel.shareText.observe(this) { text ->
            Log.d(TAG, "onCreate: $text")
            with(binding) {
                shareCount.text = text
            }
        }
        binding.like.setOnClickListener {
            viewModel.updateLikeText()
        }
        binding.share.setOnClickListener {
            viewModel.updateShareText()
        }


        fun updataPostUI(post: Post) {
            // Инициализация значений для отображения
//        binding.avatar.setImageResource(R.drawable.ic_netology_48dp)
//        binding.likeCount.text = formatCount(post.likes)
//        binding.shareCount.text = formatCount(post.shares)
//        binding.content.text = post.content
//        binding.published.text = post.published
//        binding.author.text = post.author
//
//        lifecycleScope {
//            viewModel.posts.collect { post ->
//                updataPostUI(post)
//            }
//            }
//        }
//
//        // Клик на like
//        binding.like.setOnClickListener {
//            val updatedPost = post.copy(
//                likedByMy = post.likedByMy,
//                likes = if  (post.likedByMy) likes - 1 else post.likes + 1
//            )
//            updatedPost = updatedPost
//            binding.likeCount.text = formatCount(updatedPost.likes)
//            updateLikeImage(binding, updatedPost)
//
//
//        }
//
//        // Клик на share
//        binding.share.setOnClickListener {
//            viewModel onCaresCliked(
//            post.shares++)
//
//            binding.shareCount.text = formatCount(post.shares)
//
//        }
//
//        updateLikeImage(binding, post) // Обновляем картинку лайка при запуске
        }


        fun updateLikeImage(binding: ActivityMainBinding, post: Post) {
            binding.like.setImageResource(
                if (post.likedByMy) {
                    R.drawable.ic_baseline_favorite_24
                } else {
                    R.drawable.ic_baseline_favorite_border_24
                }
            )
        }
        //   исправленная версия:


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }
}