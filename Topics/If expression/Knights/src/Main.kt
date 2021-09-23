import kotlin.math.abs

fun main() {
    val (x1, y1) = readLine()!!.split(" ")
        .map { it.toDouble() }
        .toList()
    val (x2, y2) = readLine()!!.split(" ")
        .map { it.toDouble() }
        .toList()

    print(if (abs(x1 - x2) == 1.0 && abs(y1 - y2) == 2.0 || abs(x1 - x2) == 2.0 && abs(y1 - y2) == 1.0) "YES" else "NO")
}