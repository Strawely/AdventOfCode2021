package day1.second

import DAY_1
import getStringsInput
import inputPath

fun main() {
    println(getStringsInput(DAY_1.inputPath).countIncreasesWindows())
}

private fun List<String>.countIncreasesWindows(): Int {
    var prevSum = Int.MAX_VALUE
    return asSequence().map { it.toInt() }.countIndexed { idx, it ->
        val newSum = sumN(idx)
        (newSum > prevSum).apply { prevSum = newSum }
    }
}

private inline fun Sequence<Int>.countIndexed(predicate: (Int, Int) -> Boolean): Int {
    var count = 0
    for ((index, element) in this.withIndex()) if (predicate(index, element)) ++count
    return count
}

private fun List<String>.sumN(start: Int, n: Int = 3): Int {
    if (start + n > size) return Int.MIN_VALUE
    return subList(start, start + n).map { it.toInt() }.sum()
}
