package cz.inventi.inventiskeleton.presentation.post.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cz.inventi.inventiskeleton.R
import cz.inventi.inventiskeleton.data.model.Post
import cz.inventi.inventiskeleton.presentation.common.ViewBinder
import cz.inventi.inventiskeleton.presentation.common.bindView


/**
 * Created by tomas.valenta on 5/16/2017.
 */


class PostListAdapter : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    val postList = mutableListOf<Post>()
    var onPostSelectedListener: ((Post) -> Unit) = {}
        get
        set(value) {
             field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.bind(post)
        holder.itemView.setOnClickListener { onPostSelectedListener(post) }

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun updateData(posts: List<Post>) {
        postList.clear()
        postList.addAll(posts)
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal val titleText: TextView by bindView(R.id.title)

        init {
            ViewBinder.setup(this, itemView)
        }

        fun bind(post: Post) {
            titleText.text = post.title
        }
    }
}

