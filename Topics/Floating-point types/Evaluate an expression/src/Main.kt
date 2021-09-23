import kotlin.math.pow

fun main() = readLine()!!.toDouble()
    .let { it.pow(3) + it.pow(2) + it + 1 }
    .let { print(it) }
