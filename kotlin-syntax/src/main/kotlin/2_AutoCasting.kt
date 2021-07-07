fun main() {

    fun printValue(obj: Any) {
        if (obj is String)
            println(obj)
    }

    printValue("Auto Casting")
}

