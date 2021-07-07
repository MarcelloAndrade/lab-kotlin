fun main() {

    fun sum1(a: Int, b: Int) : Int = a + b

    fun sum2(a: Int, b: Int) = a + b

    fun printSum(a: Int, b: Int) {
        println("sum of $a and $b is ${a + b}")
    }

    println(sum1(2, 6))
}

