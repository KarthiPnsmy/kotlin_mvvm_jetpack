package com.titut.inventory.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.BaseFragment
import com.titut.inventory.R
import com.titut.inventory.db.entity.FriendsWithTools
import com.titut.inventory.ui.adapter.FriendsAdapter
import com.titut.inventory.ui.adapter.OnFriendItemClickListener
import javax.inject.Inject

class FriendsFragment : BaseFragment(), OnFriendItemClickListener {

    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var friendsRecyclerView: RecyclerView
    private lateinit var friendsAdapter: FriendsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        const val ARG_FRIEND_ID: String = "friendId"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        friendsViewModel = ViewModelProvider(this, viewModelFactory).get(FriendsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_friends, container, false)
        friendsRecyclerView = root.findViewById(R.id.rvFriendsView)

        setupFriendsList()

        friendsViewModel.getFriendsWithTools()
            ?.observe(viewLifecycleOwner, Observer<List<FriendsWithTools>> { friendsWithTools ->
                friendsAdapter.setFriends(friendsWithTools)
            })

        return root
    }

    private fun setupFriendsList() {
        friendsAdapter = FriendsAdapter(this)
        friendsRecyclerView.adapter = friendsAdapter
        friendsRecyclerView.addItemDecoration(getItemDecoration())
    }

    override fun onItemClicked(friendsWithTools: FriendsWithTools) {
        val bundle = Bundle()
        bundle.putLong(ARG_FRIEND_ID, friendsWithTools.friend.friendId)
        findNavController().navigate(R.id.navigation_detail, bundle)
    }
}
