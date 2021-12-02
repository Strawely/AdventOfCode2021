package day2.first

import DAY_2
import getStringsInput
import inputPath

fun main() {
    val input = getStringsInput(DAY_2.inputPath).asSequence()
    val result = alg(input)
    println(result)
}

private fun alg(input: Sequence<String>): Int {
    var horizontal = 0
    var depth = 0
    input.map { Command.fromString(it) }.forEach { command ->
        horizontal += command.horizontalValue
        depth += command.depthValue
    }
    return horizontal * depth
}

enum class Direction(val horizontalMultiplier: Int, val depthMultiplier: Int) {
    FORWARD(horizontalMultiplier = 1, depthMultiplier = 0),
    DOWN(horizontalMultiplier = 0, depthMultiplier = 1),
    UP(horizontalMultiplier = 0, depthMultiplier = -1)
}

data class Command(
    val direction: Direction,
    val value: Int
) {
    val horizontalValue get() = value * direction.horizontalMultiplier
    val depthValue get() = value * direction.depthMultiplier

    companion object {
        fun fromString(input: String): Command {
            val (rawDirection, rawValue) = input.split(' ')
            return Command(
                direction = Direction.valueOf(rawDirection.toUpperCase()),
                value = rawValue.toInt()
            )
        }
    }
}