import java.io.FileInputStream

fun day1getData(fileName: String = INPUT_FILE_NAME) = FileInputStream(fileName).use { input ->
    val data = mutableListOf<Int>()
    val currentNumber = StringBuilder()
    var readByte: Int
    loop@ while (true) {
        val readChar = input.read().run {
            readByte = this
            toChar()
        }
        when {
            readChar == '\n' -> data.addStrValue(currentNumber)
            readByte != -1 -> currentNumber.append(readChar)
            else -> break@loop
        }
    }
    data.addStrValue(currentNumber)
    return@use data
}

private fun MutableList<Int>.addStrValue(builder: StringBuilder) {
    add(builder.toString().toInt())
    builder.clear()
}

private const val INPUT_FILE_NAME = "src/main/kotlin/day1/input.txt"