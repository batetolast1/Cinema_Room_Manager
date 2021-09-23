fun main() {
    val integer = readLine()!!.toInt()
    print(if (integer in Integer.MIN_VALUE..-15 || integer in 13..14 || integer in 17..18) "False" else "True")
}