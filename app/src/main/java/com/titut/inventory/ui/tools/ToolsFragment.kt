package com.titut.inventory.ui.tools

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.BaseFragment
import com.titut.inventory.R
import com.titut.inventory.db.entity.Friend
import com.titut.inventory.db.entity.ToolFriendCrossRef
import com.titut.inventory.db.entity.ToolsWithFriends
import com.titut.inventory.ui.adapter.OnItemClickListener
import com.titut.inventory.ui.adapter.ToolsAdapter
import com.titut.inventory.ui.friends.FriendsViewModel
import org.koin.android.ext.android.inject


class ToolsFragment : BaseFragment(), OnItemClickListener {

    private lateinit var toolsRecyclerView: RecyclerView
    private lateinit var toolsAdapter: ToolsAdapter
    private lateinit var tools: List<ToolsWithFriends>
    private lateinit var friends: List<Friend>
    private lateinit var selectedTool: ToolsWithFriends
    private lateinit var selectedFriend: Friend

    private val toolsViewModel: ToolsViewModel by inject()
    private val friendsViewModel: FriendsViewModel by inject()
    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        toolsRecyclerView = root.findViewById(R.id.rvToolsView)

        setupToolsList()
        loadToolsList()

        //test
        val editor = sharedPreferences.edit()
        editor.putString("userName", "asdf")
        editor.apply()

        return root
    }

    private fun setupToolsList() {
        toolsAdapter = ToolsAdapter(this)
        toolsRecyclerView.adapter = toolsAdapter
        toolsRecyclerView.addItemDecoration(getItemDecoration())
    }

    private fun loadToolsList() {
        toolsViewModel.getToolsWithFriends()
            .observe(viewLifecycleOwner, Observer<List<ToolsWithFriends>> { toolWithFriends ->
                this.tools = toolWithFriends
                toolsAdapter.setTools(toolWithFriends)
            })
    }

    override fun onItemClicked(tool: ToolsWithFriends) {
        this.selectedTool = tool
        friendsViewModel.getFriends()
            .observe(viewLifecycleOwner, Observer<List<Friend>> { friends ->
                this.friends = friends
                val friendsArray = arrayListOf<String>()
                friends.map {
                    friendsArray.add(it.name)
                }

                showSelectionDialog(friendsArray)
            })
    }

    private fun showSelectionDialog(friends: ArrayList<String>) {
        val friendsArray = friends.toArray(arrayOfNulls<CharSequence>(friends.size))
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.msg_select_friend_to_loan))

        var selectedToolIndex = -1
        builder.setSingleChoiceItems(friendsArray, -1) { _, which ->
            selectedToolIndex = which
        }

        builder.setPositiveButton(getString(R.string.btn_ok)) { _, _ ->
            this.friends.let {
                this.selectedFriend = it[selectedToolIndex]
                saveLoanStatus()
            }
        }
        builder.setNegativeButton(getString(R.string.btn_cancel), null)
        builder.create().show()
    }

    private fun saveLoanStatus() {
        if (listOfNotNull(selectedTool, selectedFriend).size == 2) {
            toolsViewModel.saveToolWithFriend(
                ToolFriendCrossRef(
                    selectedTool.tool.toolId,
                    selectedFriend.friendId
                )
            )
            loadToolsList()
        }
    }
}
