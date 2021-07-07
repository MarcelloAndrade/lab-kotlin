import java.time.Duration
import java.time.Instant

fun main() {
    measureTime {
        println("Start Sleep")
        Thread.sleep(200)
        println("End Sleep")
    }.also { (unit, duration) ->
        println("Duracao: ${duration.toMillis()}")
    }
}

inline fun <T> measureTime(callFunction: () -> T): Pair<T, Duration> {
    val markNow = Instant.now()
    val t = callFunction()
    return Pair(t, Duration.between(markNow, Instant.now()))
}