fun String.masked() : String {
    val regex = ".[0-9]{3}.[0-9]{3}-[0-9]{2}".toRegex()
    return this.replace(regex, ".***.***-**")
}

fun main() {
    val cpf: String = "234.543.457-92"
    println(cpf.masked())
}