fun main() = DoubleArray(2) { readLine()!!.toDouble() }
    .let { it[0] / it[1] }
    .let(::print)
