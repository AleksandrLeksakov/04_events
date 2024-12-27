package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostViewHolder.PostDiffCallback
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import java.util.Locale

sealed class PostAction {
    data class Like(val postId: Int) : PostAction()
    data class Share(val postId: Int) : PostAction()
}

class PostsAdapter(private val callback: (PostAction) -> Unit) :
    ListAdapter<Post, PostViewHolder>(PostDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            CardPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callback
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostViewHolder(private val binding: CardPostBinding, private val callback: (PostAction) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) = with(binding) {
        author.text = post.author
        published.text = post.published
        content.text = post.content
        share.setOnClickListener {
            callback(PostAction.Share(post.id)) // Call callback with PostAction.Share
        }
        like.setImageResource(
            if (post.likedByMy) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
        )
        like.setOnClickListener {
            callback(PostAction.Like(post.id))  // Call callback with PostAction.Like
        }
        likeCount.text = post.likes.formatCount()
        shareCount.text = post.shares.formatCount()
    }


    private fun Int.formatCount(): String {
        return when {
            this < 1000 -> this.toString()
            this < 10000 -> String.format(Locale.getDefault(), "%.1fK", this / 1000.0)
            this < 1000000 -> String.format(Locale.getDefault(), "%.0fK", this / 1000.0)
            else -> String.format(Locale.getDefault(), "%.1fM", this / 1000000.0)
        }
    }
    object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
    }
}








