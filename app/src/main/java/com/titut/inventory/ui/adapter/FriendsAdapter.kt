package com.titut.inventory.ui.adapter


import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.R
import com.titut.inventory.db.entity.Friend

class FriendsAdapter : RecyclerView.Adapter<FriendsAdapter.FriendHolder>() {
    private var friends: List<Friend> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_item, parent, false)
        return FriendHolder(itemView)
    }

    override fun onBindViewHolder(holder: FriendHolder, position: Int) {
        val friend = friends[position]
        holder.tvFriendName.text = friend.name
        holder.tvLoanStatus.text = "Total items on loan: 4"
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    fun setFriends(friends: List<Friend>) {
        this.friends = friends
        notifyDataSetChanged()
    }

    inner class FriendHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFriendName: TextView = itemView.findViewById(R.id.tvFriendName)
        var ivFriendImage: ImageView = itemView.findViewById(R.id.ivFriendImage)
        var tvLoanStatus: TextView = itemView.findViewById(R.id.tvLoan)
    }
}