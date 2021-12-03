package day3.first

import DAY_3
import getStringsInput
import inputPath

fun main() {
    val data = getStringsInput(DAY_3.inputPath)
    println(first(data))
    println(second(data))
}

private fun first(input: List<String>): Int {
    val countArray = getCountArray(input)
    return countArray.epsilon * countArray.gamma
}


private fun second(input: List<String>): Int {
    return input.oxygenRating * input.CO2Rating
}

private fun getCountArray(input: List<String>): IntArray {
    val len = input[0].length
    return input.fold(IntArray(len)) { acc, s ->
        acc.forEachIndexed { index, value -> if (s[index] == '1') acc[index]++ else acc[index]-- }
        acc
    }
}

private val IntArray.gamma get() = joinToString(separator = "") { if (it > 0) "1" else "0" }.toInt(2)
private val IntArray.epsilon get() = joinToString(separator = "") { if (it < 0) "1" else "0" }.toInt(2)

private val List<String>.oxygenRating: Int get() = filterRating { it >= 0 }
private val List<String>.CO2Rating: Int get() = filterRating { it < 0 }

private fun List<String>.filterRating(predicate: (Int) -> Boolean): Int = filterRatingItem(predicate = predicate)[0].toInt(2)

private fun List<String>.filterRatingItem(idx: Int = 0, predicate: (Int) -> Boolean): List<String> {
    if (this.size < 2) return this
    val countArray = getCountArray(this)
    return filter { if (predicate(countArray[idx])) it[idx] == '1' else it[idx] == '0'  }.filterRatingItem(idx + 1, predicate)
}
