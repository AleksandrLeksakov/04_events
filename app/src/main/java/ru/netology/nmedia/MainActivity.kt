package ru.netology.nmedia

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.PostViewModel

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
                likeCount.text = formatCount(post.likes)
                shareCount.text = formatCount(post.shares)
                if (post.likedByMy) like.setImageResource(R.drawable.ic_baseline_favorite_24)
                else like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }
        binding.share.setOnClickListener {
            viewModel.share()
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