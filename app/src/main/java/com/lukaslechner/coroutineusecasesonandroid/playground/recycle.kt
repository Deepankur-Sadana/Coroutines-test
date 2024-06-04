package com.lukaslechner.coroutineusecasesonandroid.playground

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    var res: TreeNode?= null
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        dfs(root, p, q)
        return res
    }

    fun dfs(root: TreeNode?, p: TreeNode?, q: TreeNode?) : Boolean {
        if(root == null) return false
        val left = if(dfs(root.left, p, q))  1 else  0
        val right = if(dfs(root.right, p, q))  1 else 0
        val mid = if((p == root || q == root))  1 else 0
        if(mid + left + right == 2) {
            res = root
        }
        return mid + left + right > 0
    }
}