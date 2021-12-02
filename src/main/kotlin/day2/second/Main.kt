package day2.second

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
    var aim = 0
    input.map(Command::fromString).forEach { command ->
        horizontal += command.horizontalValue
        aim += command.aimValue
        depth += command.getDepthValue(aim)
    }
    return horizontal * depth
}

enum class Direction(val horizontalMultiplier: Int, val depthMultiplier: (aim: Int) -> Int, val aimMultiplier: Int) {
    FORWARD(horizontalMultiplier = 1, depthMultiplier = { it }, aimMultiplier = 0),
    DOWN(horizontalMultiplier = 0, depthMultiplier = { 0 }, aimMultiplier = 1),
    UP(horizontalMultiplier = 0, depthMultiplier = { 0 }, aimMultiplier = -1)
}

data class Command(
    val direction: Direction,
    val value: Int
) {
    val horizontalValue get() = value * direction.horizontalMultiplier
    val aimValue get() = value * direction.aimMultiplier
    fun getDepthValue(aim: Int): Int {
        return value * direction.depthMultiplier(aim)
    }

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