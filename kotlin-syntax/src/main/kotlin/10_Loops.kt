fun main() {

    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println("for ${item}")
    }

    for (index in items.indices) {
        println("for indices item at $index is ${items[index]}")
    }

    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    if ("apple" in items) {
        println("OK apple")
    }

}

