package com.titut.inventory.ui.adapter


import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.R
import com.titut.inventory.db.entity.FriendsWithTools

class FriendsAdapter(private val clickListener: OnFriendItemClickListener) :
    RecyclerView.Adapter<FriendsAdapter.FriendHolder>() {
    private var friends: List<FriendsWithTools> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_item, parent, false)
        return FriendHolder(itemView)
    }

    override fun onBindViewHolder(holder: FriendHolder, position: Int) {
        val friendsWithTools = friends[position]
        holder.bind(friendsWithTools, clickListener)

    }

    override fun getItemCount(): Int {
        return friends.size
    }

    fun setFriends(friends: List<FriendsWithTools>) {
        this.friends = friends
        notifyDataSetChanged()
    }

    inner class FriendHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFriendName: TextView = itemView.findViewById(R.id.tvFriendName)
        var tvLoanStatus: TextView = itemView.findViewById(R.id.tvLoan)

        fun bind(friendsWithTools: FriendsWithTools, clickListener: OnFriendItemClickListener) {
            tvFriendName.text = friendsWithTools.friend.name
            tvLoanStatus.text = "Total items on loan: ${friendsWithTools.tools.size}"

            itemView.setOnClickListener {
                clickListener.onItemClicked(friendsWithTools)
            }
        }
    }
}

interface OnFriendItemClickListener {
    fun onItemClicked(friendsWithTools: FriendsWithTools)
}