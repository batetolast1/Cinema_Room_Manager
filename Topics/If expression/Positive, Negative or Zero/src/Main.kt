fun main() {
    val number = readLine()!!.toInt()
    print(if (number < 0) "negative" else if (number == 0) "zero" else "positive")
}
