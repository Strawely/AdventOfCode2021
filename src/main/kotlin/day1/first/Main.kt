package day1.first

import day1getData

fun main() {
    val data = day1getData()
    println(data.countIncreases())
}

private fun List<Int>.countIncreases(): Int {
    var prev = get(0)
    return asSequence().drop(1).count { (it > prev).apply { prev = it } }
}