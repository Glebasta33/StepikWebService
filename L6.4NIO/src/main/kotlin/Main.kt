package com.github.gltrusov

import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

fun main() {
    val raf = RandomAccessFile("L6.4NIO/data/data.txt", "rw")

    // Двустороннее соединение для IO операций
    val channel: FileChannel = raf.channel

    // Буфер байт при чтении
    val buffer: ByteBuffer = ByteBuffer.allocate(64)

    var bytes: Int = channel.read(buffer) // запуск чтения

    while (bytes != -1) {
        println("Read $bytes bytes")
        buffer.flip() // сброс позиции буфера до 0

        while (buffer.hasRemaining()) {
            print(buffer.get().toChar()) // получение данных из буфера
        }
        println("\n")
        buffer.clear() // очистка буфера

        bytes = channel.read(buffer) // запуск чтения
    }

    raf.close() // закрытие доступа к файлу
}