package org.example

fun onlyGoodNums(numsStr: String, goodNums : List<Int>) : String =
    numsStr.filter{ it.digitToInt() in goodNums }

fun main() {
    val numsStr = "9813274923794319"
    val goodNums = listOf(1, 2, 3)
    print(onlyGoodNums(numsStr, goodNums))
}