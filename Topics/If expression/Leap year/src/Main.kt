fun main() {
    val year = readLine()!!.toInt()
    print(if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) "Leap" else "Regular")
}