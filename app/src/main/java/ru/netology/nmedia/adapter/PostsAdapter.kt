package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
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
        likeCount.text = post.likes.toString()
        shareCount.text = post.shares.toString()
    }
}

object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
}


