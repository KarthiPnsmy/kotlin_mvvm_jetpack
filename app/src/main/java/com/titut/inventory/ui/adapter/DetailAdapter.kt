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

class DetailAdapter(private val itemClickListener: OnToolItemClickListener) : RecyclerView.Adapter<DetailAdapter.SimpleToolHolder>() {
    private var tools: List<Tool> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleToolHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tool_simple_item, parent, false)
        return SimpleToolHolder(itemView)
    }

    override fun onBindViewHolder(holder: SimpleToolHolder, position: Int) {
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

    inner class SimpleToolHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvToolName: TextView = itemView.findViewById(R.id.tvToolName)
        var ivToolImage: ImageView = itemView.findViewById(R.id.ivToolImage)

        fun bind(tool: Tool, clickListener: OnToolItemClickListener) {
            tvToolName.text = tool.name
            ivToolImage.setImageDrawable(ContextCompat.getDrawable(ivToolImage.context, tool.image))

            itemView.setOnClickListener {
                clickListener.onItemClicked(tool)
            }
        }
    }
}

interface OnToolItemClickListener{
    fun onItemClicked(tool: Tool)
}