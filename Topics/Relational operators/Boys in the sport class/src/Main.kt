fun main() {
    // put your code here
    val boy1 = readLine()!!.toInt()
    val boy2 = readLine()!!.toInt()
    val boy3 = readLine()!!.toInt()
    print(boy2 in boy1..boy3 || boy2 in boy3..boy1)
}