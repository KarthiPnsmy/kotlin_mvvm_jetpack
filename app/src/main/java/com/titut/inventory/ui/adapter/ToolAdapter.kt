package com.titut.inventory.ui.adapter


import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.titut.inventory.R
import com.titut.inventory.db.entity.Tool

class ToolAdapter(val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ToolAdapter.ToolHolder>() {
    private var tools: List<Tool> = ArrayList()

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

    fun setTools(tools: List<Tool>) {
        this.tools = tools
        notifyDataSetChanged()
    }

    inner class ToolHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvToolName: TextView = itemView.findViewById(R.id.tvToolName)
        var ivToolImage: ImageView = itemView.findViewById(R.id.ivToolImage)
        var tvLoanStatus: TextView = itemView.findViewById(R.id.tvLoan)
        var tvInventory: TextView = itemView.findViewById(R.id.tvInventory)

        fun bind(tool: Tool, clickListener: OnItemClickListener) {
            tvToolName.text = tool.name
            ivToolImage.setImageDrawable(ContextCompat.getDrawable(ivToolImage.context, tool.image))
            tvLoanStatus.text = "Inventory: ${tool.quantity}"
            tvInventory.text = "On Loan: 4"

            itemView.setOnClickListener {
                clickListener.onItemClicked(tool)
            }
        }
    }
}

interface OnItemClickListener{
    fun onItemClicked(tool: Tool)
}