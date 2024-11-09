package ru.netology.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(resources.displayMetrics.heightPixels) // 1794
        println(resources.displayMetrics.widthPixels) // 1080
        println(resources.displayMetrics.densityDpi) // 420
        println(resources.displayMetrics.density) // 2.625

        val avatar: ImageView = findViewById(R.id.avatar)
        avatar.setImageResource(R.drawable.ic_netology_48dp)

        val author: TextView = findViewById(R.id.author)
        author.setText(R.string.author_text)

        val published: TextView = findViewById(R.id.published)
        published.setText(R.string.published_text)

        val content: TextView = findViewById(R.id.content)
        content.setText(R.string.content_text)

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
        }
    }
}


//        println(R.string.hello) // число
//        println(getString(R.string.hello)) // "Привет, Мир!" или "Hello World!"
