package com.titut.inventory.ui.tools

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.R
import com.titut.inventory.db.entity.Friend
import com.titut.inventory.db.entity.Tool
import com.titut.inventory.ui.adapter.OnItemClickListener
import com.titut.inventory.ui.adapter.ToolAdapter
import com.titut.inventory.ui.friends.FriendsViewModel


class ToolsFragment : Fragment(), OnItemClickListener {

    private lateinit var toolsViewModel: ToolsViewModel
    private lateinit var friendsViewModel: FriendsViewModel

    private lateinit var toolsRecyclerView: RecyclerView
    private lateinit var toolsAdapter: ToolAdapter
    private lateinit var tools: List<Tool>
    private lateinit var friends: List<Friend>
    private lateinit var selectedTool: Tool
    private lateinit var selectedFriend: Friend

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel = ViewModelProvider(this).get(ToolsViewModel::class.java)
        friendsViewModel = ViewModelProvider(this).get(FriendsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        toolsRecyclerView = root.findViewById(R.id.rvToolsView)

        setupToolsList()

        toolsViewModel.getTools()?.observe(viewLifecycleOwner, Observer<List<Tool>> { tools ->
            this.tools = tools
            toolsAdapter.setTools(tools)
        })

        return root
    }

    private fun setupToolsList() {
        toolsAdapter = ToolAdapter(this)
        toolsRecyclerView.adapter = toolsAdapter
        toolsRecyclerView.addItemDecoration(getItemDecoration())
    }

    private fun getItemDecoration(): RecyclerView.ItemDecoration {
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.recycler_divider)?.let {
            divider.setDrawable(it)
        }
        return divider
    }

    override fun onItemClicked(tool: Tool) {
        this.selectedTool = tool
        Toast.makeText(activity, "Tool ${tool.name} selected", Toast.LENGTH_LONG).show()
        friendsViewModel.getFriends()?.observe(viewLifecycleOwner, Observer<List<Friend>> { friends ->
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
        builder.setTitle("Select a friend to loan")

        var selectedToolIndex = -1
        builder.setSingleChoiceItems(friendsArray, -1) { _, which ->
            selectedToolIndex = which
        }

        builder.setPositiveButton("OK") { _, which ->
            this.friends.let {
                this.selectedFriend = it[selectedToolIndex]
                saveLoanStatus()
            }
        }
        builder.setNegativeButton("Cancel", null)
        builder.create().show()
    }

    private fun saveLoanStatus(){
        if (listOfNotNull(selectedTool, selectedFriend).size == 2) {
            println("@@@@ All variables are non-null")

        }
    }
}
