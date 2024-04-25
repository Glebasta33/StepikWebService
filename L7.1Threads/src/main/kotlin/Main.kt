package com.github.gltrusov

internal class RandomSequenceThread : Thread() {
    override fun run() {
        println("Run: ${this.name}")
    }
}

internal class SerialSequenceThread(
    private val mainId: Int
) : Thread() {
    override fun run() {
        println("Run: $mainId")

        synchronized(waitObject) {
            while (mainId > currentMax) {
                waitObject.wait()
                println("Awakened: $mainId")
            }

            currentMax++
            println("Finished: $mainId")
            waitObject.notifyAll()
        }
    }

    companion object {
        private var currentMax = 1
        private val waitObject = Object()
    }
}

fun main() {
    repeat(10) { i ->
//        val thread = RandomSequenceThread()
        val thread = SerialSequenceThread(i)
        println("Start: ${thread.name}")
        thread.start()
    }
}