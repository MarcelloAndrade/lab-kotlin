fun main() {
    var valueS1: String = "My string"
    var valueS2 = "My string"

    val finalValue = "Final Value"

    //var b = "Elvis Operator"
    var b : String? = null
    val l = b?.length ?: -1
    println(l)

    // NULL SAFE
    var a: Int? = null
    val aInt: Int? = a as? Int
    println(aInt)

    var nullSafe : String? = null
    println("Null Safe: ${nullSafe?.length}")
    println("Null Safe: ${nullSafe!!.length}")
}

