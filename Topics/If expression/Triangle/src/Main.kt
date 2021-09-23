fun main() {
    val a = readLine()!!.toInt()
    val b = readLine()!!.toInt()
    val c = readLine()!!.toInt()

    print(if (isTriangle(a, b, c)) "YES" else "NO")
}

fun isTriangle(a: Int, b: Int, c: Int) = a + b > c && a + c > b && b + c > a
