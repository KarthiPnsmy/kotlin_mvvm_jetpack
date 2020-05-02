package com.titut.inventory.ui.adapter


import android.annotation.SuppressLint
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.R
import com.titut.inventory.db.entity.ToolsWithFriends

class ToolsAdapter(private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ToolsAdapter.ToolHolder>() {
    private var tools: List<ToolsWithFriends> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tool_item, parent, false)
        return ToolHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToolHolder, position: Int) {
        val tool = tools[position]
        holder.bind(tool, itemClickListener)
    }

    override fun getItemCount(): Int {
        return tools.size
    }

    fun setTools(tools: List<ToolsWithFriends>) {
        this.tools = tools
        notifyDataSetChanged()
    }

    inner class ToolHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvToolName: TextView = itemView.findViewById(R.id.tvToolName)
        var ivToolImage: ImageView = itemView.findViewById(R.id.ivToolImage)
        var tvLoanStatus: TextView = itemView.findViewById(R.id.tvLoan)
        var tvInventory: TextView = itemView.findViewById(R.id.tvInventory)

        @SuppressLint("SetTextI18n")
        fun bind(toolWithFriends: ToolsWithFriends, clickListener: OnItemClickListener) {
            tvToolName.text = toolWithFriends.tool.name
            ivToolImage.setImageDrawable(
                ContextCompat.getDrawable(
                    ivToolImage.context,
                    toolWithFriends.tool.image
                )
            )
            tvLoanStatus.text = "On Loan: ${toolWithFriends.friends.size}"
            tvInventory.text = "Total Items: ${toolWithFriends.tool.quantity}"

            itemView.setOnClickListener {
                clickListener.onItemClicked(toolWithFriends)
            }
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(tool: ToolsWithFriends)
}