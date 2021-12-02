import java.io.FileInputStream

fun getStringsInput(fileName: String) = FileInputStream(fileName).use { input ->
    mutableListOf<String>().apply {
        val currentNumber = StringBuilder()
        var readByte: Int
        loop@ while (true) {
            val readChar = input.read().run {
                readByte = this
                toChar()
            }
            when {
                readChar == '\n' -> {
                    add(currentNumber.toString())
                    currentNumber.clear()
                }
                readByte != -1 -> currentNumber.append(readChar)
                else -> break@loop
            }
        }
        add(currentNumber.toString())
    }
}

val Int.inputPath get() = "src/main/kotlin/day$this/input.txt"