package com.github.gltrusov

import java.io.*

fun main() {
    stringExample()
    descriptorModelExample()
}

private fun stringExample() {
    val fileName = "L6.2Serialization/string.bin"
    write("Test string for writing to bin", fileName)
    val fromFileString = read<String>(fileName)
    println(fromFileString)
}

private fun descriptorModelExample() {
    val descriptor = DescriptorModel("John", 33)
    val fileName = "L6.2Serialization/descriptor.bin"
    write(descriptor, fileName)
    val fromFileDescriptor = read<DescriptorModel>(fileName)
    println(fromFileDescriptor)
}

private fun write(obj: Any, fileName: String) {
    FileOutputStream(fileName).use { outputStream ->
        val bout = BufferedOutputStream(outputStream)
        val out = ObjectOutputStream(bout)
        out.writeObject(obj)
        out.flush()
    }
}

private inline fun <reified T> read(fileName: String): T {
    return FileInputStream(fileName).use { inputStream ->
        val bufferedInputStream = BufferedInputStream(inputStream)
        val objectStream = ObjectInputStream(bufferedInputStream)
        objectStream.readObject() as T
    }
}