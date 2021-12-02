package day1.first

import DAY_1
import getStringsInput
import inputPath

fun main() {
    val data = getStringsInput(DAY_1.inputPath).asSequence()
    println(data.countIncreases())
}

private fun Sequence<String>.countIncreases(): Int {
    var prev = this.first().toInt()
    return map { it.toInt() }.count { (it > prev).apply { prev = it } }
}