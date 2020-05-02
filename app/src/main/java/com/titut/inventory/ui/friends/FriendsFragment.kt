package com.titut.inventory.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.R
import com.titut.inventory.db.entity.Friend
import com.titut.inventory.db.entity.FriendsWithTools
import com.titut.inventory.ui.adapter.FriendsAdapter
import com.titut.inventory.ui.adapter.OnFriendItemClickListener

class FriendsFragment : Fragment(), OnFriendItemClickListener {

    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var friendsRecyclerView: RecyclerView
    private lateinit var friendsAdapter: FriendsAdapter

    companion object {
        const val ARG_FRIEND_ID: String = "friendId"
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        friendsViewModel = ViewModelProvider(this).get(FriendsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_friends, container, false)
        friendsRecyclerView = root.findViewById(R.id.rvFriendsView)

        setupFriendsList()

        friendsViewModel.getFriendsWithTools()?.observe(viewLifecycleOwner, Observer<List<FriendsWithTools>> { friendsWithTools ->
            friendsAdapter.setFriends(friendsWithTools)
        })

        return root
    }

    private fun setupFriendsList() {
        friendsAdapter = FriendsAdapter(this)
        friendsRecyclerView.adapter = friendsAdapter
        friendsRecyclerView.addItemDecoration(getItemDecoration())
    }

    private fun getItemDecoration(): RecyclerView.ItemDecoration {
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.recycler_divider)?.let {
            divider.setDrawable(it)
        }
        return divider
    }

    override fun onItemClicked(friendsWithTools: FriendsWithTools) {
        println("@@@@ friendsWithTools $friendsWithTools")
        val bundle = Bundle()
        bundle.putLong(ARG_FRIEND_ID, friendsWithTools.friend.friendId)
        findNavController().navigate(R.id.navigation_detail, bundle)
    }
}
