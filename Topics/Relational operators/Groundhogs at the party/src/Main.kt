fun main() {
    val cups = readLine()!!.toInt()
    val isWeekend = readLine()!!.toBoolean()
    print(if (isWeekend) cups in 15..25 else cups in 10..20)
}
