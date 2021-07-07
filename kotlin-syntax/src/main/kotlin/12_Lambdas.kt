fun main() {
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits.filter { it.startsWith("a") }
          .sortedBy { it }
          .map { it.uppercase() }
          .forEach { println(it) }

    val fruits2 = fruits.filter { it.startsWith("a") }
                        .sortedBy { it }
                        .map { it.uppercase() }
    println(fruits2)
}