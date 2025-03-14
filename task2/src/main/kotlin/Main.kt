package org.example

data class Node(var left: Node? = null, var right: Node? = null, var value: Int)

fun insert(root: Node?, value : Int) : Node? {
    if (root == null) {
        return Node(value=value)
    }
    if (value < root.value) {
        root.left = insert(root.left, value)
    } else {
        root.right = insert(root.right, value)
    }
    return root
}

fun makeTree(list : List<Int>) : Node? {
    var root: Node? = null
    for (elem in list) {
        root = insert(root, elem)
    }
    return root
}

fun printTree(root : Node?) : Unit {
    if (root == null) return
    printTree(root.left)
    print(root.value.toString() + " ")
    printTree(root.right)
}


fun main() {
    var list = listOf(15, 2, 13, 42, 511, 16)
    var treeRoot : Node? = makeTree(list)
    printTree(treeRoot)
}