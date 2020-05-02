package com.titut.inventory

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

abstract class BaseFragment: Fragment() {

    fun getItemDecoration(): RecyclerView.ItemDecoration {
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.recycler_divider)?.let {
            divider.setDrawable(it)
        }
        return divider
    }
}