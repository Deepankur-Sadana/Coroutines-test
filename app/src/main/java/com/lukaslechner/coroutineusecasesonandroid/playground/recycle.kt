package com.lukaslechner.coroutineusecasesonandroid.playground

import java.util.PriorityQueue

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

val list = ArrayList<Int>()
fun maxLevelSum(root: TreeNode?): Int {
    dfs(root, 0)
    return list.maxOf { it }
}

fun dfs(root: TreeNode?, level: Int) {
    if (root == null) return
    if (list.size < level + 1){
        list.add(0)
    }
    list[level]+= root.`val`
    dfs(root.left, level + 1)
    dfs(root.right, level + 1)
}
