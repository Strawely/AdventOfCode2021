package day1.second

import day1getData

fun main() {
    println(day1getData().countIncreasesWindows())
}

private fun List<Int>.countIncreasesWindows(): Int {
    var prevSum = Int.MAX_VALUE
    return asSequence().countIndexed { idx, it ->
        val newSum = sumN(idx)
        (newSum > prevSum).apply { prevSum = newSum }
    }
}

private inline fun Sequence<Int>.countIndexed(predicate: (Int, Int) -> Boolean): Int {
    var count = 0
    for ((index, element) in this.withIndex()) if (predicate(index, element)) ++count
    return count
}

private fun List<Int>.sumN(start: Int, n: Int = 3): Int {
    if (start + n > size) return Int.MIN_VALUE
    return subList(start, start + n).sum()
}
