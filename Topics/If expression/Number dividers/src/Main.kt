fun main() {
    val integer = readLine()!!.toInt()
    for (i in arrayListOf(2, 3, 5, 6)) {
        printIfDivided(integer, i)
    }
}

private fun printIfDivided(integer: Int, divisor: Int) {
    if (integer % divisor == 0) {
        println("Divided by $divisor")
    }
}