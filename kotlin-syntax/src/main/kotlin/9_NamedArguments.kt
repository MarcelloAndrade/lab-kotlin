fun format(name: String, age: Int) : String = "Name: ${name.uppercase()}, Age: ${age}"

fun main() {
    println(format("Marcello", 31))
    println(format(name = "Marcello", age = 31))
    println(format(age = 31, name = "Marcello"))
}