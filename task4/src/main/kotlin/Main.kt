package org.example

class StackExtended<T : Comparable<T>> {
    var array = mutableListOf<Pair<T, T>>()

    fun push(value : T) : Unit {
        var maxValue = value
        if (this.size() > 0 && this.max() < maxValue) {
            maxValue = value
        }
        array.add(Pair(value, maxValue))
    }

    fun pop() : T = array.removeLast().first

    fun max() : T = array.last().second

    fun size() : Int = array.size

}


fun main() {
    var stack = StackExtended<Int>()
    stack.push(4)
    stack.push(9)
    stack.push(1)
    stack.push(5)
    stack.push(6)
    println(stack.max().toString() + " -> max in stack")
    println("Элементы в стеке (сверху вниз): ")
    while (stack.size() > 0) {
        print(stack.pop().toString() + " ")
    }

}