package org.example

fun List<Int>.sumOddCubes(): Int =
    this.filter{ it % 2 != 0 }.map{ it * it * it }.sum()

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6)
    println(list.sumOddCubes())
}