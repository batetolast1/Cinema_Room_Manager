fun main() {
    val num1 = readLine()!!.toInt()
    val num2 = readLine()!!.toInt()
    if (num2 == 0) {
        print("Division by zero, please fix the second argument!")
    } else {
        print(num1 / num2)
    }
}