package com.titut.inventory.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.R
import com.titut.inventory.db.entity.Friend
import com.titut.inventory.ui.adapter.FriendsAdapter

class FriendsFragment : Fragment() {

    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var friendsRecyclerView: RecyclerView
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        friendsViewModel = ViewModelProvider(this).get(FriendsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_friends, container, false)
        friendsRecyclerView = root.findViewById(R.id.rvFriendsView)

        setupFriendsList()

        friendsViewModel.getFriends()?.observe(viewLifecycleOwner, Observer<List<Friend>> { friends ->
            friendsAdapter.setFriends(friends)
        })

        return root
    }

    private fun setupFriendsList() {
        friendsAdapter = FriendsAdapter()
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
}
