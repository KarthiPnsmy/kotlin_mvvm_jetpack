package com.titut.inventory.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.R
import com.titut.inventory.db.entity.Tool
import com.titut.inventory.ui.adapter.ToolAdapter

class ToolsFragment : Fragment() {

    private lateinit var toolsViewModel: ToolsViewModel
    private lateinit var toolsRecyclerView: RecyclerView
    private lateinit var toolsAdapter: ToolAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProvider(this).get(ToolsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        toolsRecyclerView = root.findViewById(R.id.rvToolsView)

        setupToolsList()

        toolsViewModel.getTools()?.observe(viewLifecycleOwner, Observer<List<Tool>> {tools ->
            toolsAdapter.setTools(tools)
        })


        return root
    }

    private fun setupToolsList() {
        toolsAdapter = ToolAdapter()
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
}
