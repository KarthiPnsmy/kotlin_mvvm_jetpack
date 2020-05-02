package com.titut.inventory

import com.titut.inventory.db.entity.Friend
import com.titut.inventory.db.entity.Tool
import com.titut.inventory.db.entity.ToolFriendCrossRef

object TestUtils {
    val TOOL1 = Tool(
        "Wrench", 34355, 10
    )
    private val TOOL2: Tool = Tool(
        "Pliers", 75655, 12
    )
    private val TOOL3: Tool = Tool(
        "Hammer", 76757, 5
    )

    val TOOLS = listOf(TOOL1, TOOL2, TOOL3)

    val TOOL_FRIEND_CROSS_REF1 = ToolFriendCrossRef(1,1)
    val TOOL_FRIEND_CROSS_REF2 = ToolFriendCrossRef(2,1)

    val FRIEND1: Friend = Friend("Martin", 45435)

    private val FRIEND2: Friend = Friend("Ryan", 76768)

    val FRIENDS = listOf(FRIEND1, FRIEND2)

}