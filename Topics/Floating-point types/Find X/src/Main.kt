fun main() = DoubleArray(3) { readLine()!!.toDouble() }
    .let { (it[2] - it[1]) / it[0] }
    .let(::print)
