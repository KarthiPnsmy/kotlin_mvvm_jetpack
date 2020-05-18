package com.titut.inventory.ui.detail

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.BaseFragment
import com.titut.inventory.R
import com.titut.inventory.db.entity.ToolsOnLoan
import com.titut.inventory.ui.adapter.DetailAdapter
import com.titut.inventory.ui.adapter.OnToolItemClickListener
import com.titut.inventory.ui.friends.FriendsFragment.Companion.ARG_FRIEND_ID
import com.titut.inventory.ui.tools.ToolsViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject

class DetailFragment : BaseFragment(), OnToolItemClickListener {
    private lateinit var detailRecyclerView: RecyclerView
    private lateinit var detailAdapter: DetailAdapter
    private var selectedFriendId: Long = 0L
    private lateinit var selectedTool: ToolsOnLoan

    private val toolsViewModel: ToolsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
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

    private fun loadFriendsWithTools() {
        toolsViewModel.getToolsWithFriendOnLoan(selectedFriendId)
            .observe(viewLifecycleOwner, Observer<List<ToolsOnLoan>> { toolsOnLoan ->
                tvEmptyView.isVisible = toolsOnLoan.isEmpty()
                detailAdapter.setTools(toolsOnLoan)
            })
    }

    override fun onItemClicked(tool: ToolsOnLoan) {
        this.selectedTool = tool
        showSelectionDialog()
    }

    private fun showSelectionDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(getString(R.string.msg_want_to_mark_as_returned))
        builder.setPositiveButton(getString(R.string.btn_ok)) { _, _ ->
            saveReturnStatus()
        }
        builder.setNegativeButton(getString(R.string.btn_cancel), null)
        builder.create().show()
    }

    private fun saveReturnStatus() {
        selectedTool.let {
            toolsViewModel.deleteToolFromFriend(
                selectedFriendId,
                selectedTool.toolId
            )
            loadFriendsWithTools()
        }
    }
}
