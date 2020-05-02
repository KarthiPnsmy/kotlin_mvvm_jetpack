package com.titut.inventory.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.BaseFragment
import com.titut.inventory.R
import com.titut.inventory.db.entity.FriendsWithTools
import com.titut.inventory.db.entity.Tool
import com.titut.inventory.ui.adapter.DetailAdapter
import com.titut.inventory.ui.adapter.FriendsAdapter
import com.titut.inventory.ui.adapter.OnToolItemClickListener
import com.titut.inventory.ui.friends.FriendsFragment.Companion.ARG_FRIEND_ID
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseFragment(), OnToolItemClickListener {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailRecyclerView: RecyclerView
    private lateinit var detailAdapter: DetailAdapter
    private var selectedFriendId: Long = 0L
    private lateinit var tvFriendName: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        tvFriendName = root.findViewById(R.id.tvFriendName)
        detailRecyclerView = root.findViewById(R.id.rvDetailView)

        setupDetailList()

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        selectedFriendId = arguments?.getLong(ARG_FRIEND_ID) ?: 0L
        loadFriendsWithTools()
    }

    private fun setupDetailList() {
        detailAdapter = DetailAdapter(this)
        detailRecyclerView.adapter = detailAdapter
        detailRecyclerView.addItemDecoration(getItemDecoration())
    }

    private fun loadFriendsWithTools(){
        detailViewModel.getFriendsWithToolsByFriend(selectedFriendId)?.observe(viewLifecycleOwner, Observer<FriendsWithTools> { friendsWithTools ->
            tvFriendName.text = friendsWithTools.friend.name
            tvEmptyView.isVisible = friendsWithTools.tools.isEmpty()
            detailAdapter.setTools(friendsWithTools.tools)

        })
    }

    override fun onItemClicked(tool: Tool) {
        println("@@@@ tool $tool")
    }
}
