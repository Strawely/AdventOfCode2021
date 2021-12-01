package day1.first

import java.io.FileInputStream

fun main() {
    val data = getData()
    println(data.countIncreases())
}

private fun List<Int>.countIncreases(): Int {
    var prev = get(0)
    return asSequence().drop(1).count { (it > prev).apply { prev = it } }
}

private fun getData(fileName: String = INPUT_FILE_NAME) = FileInputStream(fileName).use { input ->
    val data = mutableListOf<Int>()
    val currentNumber = StringBuilder()
    var readByte = 0
    while (readByte != -1) {
        val readChar = input.read().run {
            readByte = this
            toChar()
        }
        if (readChar == '\n') {
            data.add(currentNumber.toString().toInt())
            currentNumber.clear()
        } else {
            currentNumber.append(readChar)
        }
    }
    return@use data
}

private const val INPUT_FILE_NAME = "src/main/kotlin/day1/first/input.txt"