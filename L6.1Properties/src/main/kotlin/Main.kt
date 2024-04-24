package com.github.gltrusov

import java.io.FileInputStream
import java.util.Properties

fun main() {
    //Properties - утилита для доступа к статичным параметрам конфигурации,
    //которая позволяет получить значение свойства по имени ключа
    val properties = Properties()

    FileInputStream("config.properties").use { inputStream ->
        properties.load(inputStream)

        println(properties.getProperty("database"))
        println(properties.getProperty("db_user"))
        println(properties.getProperty("db_password"))
    }

}