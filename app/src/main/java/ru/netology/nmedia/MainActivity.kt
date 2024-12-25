package ru.netology.nmedia


import android.R.id
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val like: ImageButton = findViewById(R.id.like)
        var likeCounter = 998
        val likeText: TextView = findViewById(R.id.like_count)
        likeText.setText("$likeCounter")
        like.setOnClickListener {
            if (likeCounter >= 1000) {
                likeText.setText("+1k")
            } else {
                likeText.setText("${likeCounter++}")
            }
        }

        val share: ImageButton = findViewById(R.id.share)
        var shareCounter = 350
        val shareText: TextView = findViewById(R.id.share_count)
        shareText.setText("$shareCounter")
        share.setOnClickListener {
            shareText.setText("${shareCounter++}")


            val viewModel: PostViewModel by viewModels()
            val adapter = PostsAdapter {
                viewModel.likeById(it.id)

                viewModel.shareById(it.id)
            }
            binding.container.adapter = adapter
            viewModel.data.observe(this) { posts ->
                adapter.submitList(posts)


            }
        }
    }
}







