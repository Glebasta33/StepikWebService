package com.github.gltrusov

import sax.ReadXMLFileSAX

fun main() {
    val resource = ReadXMLFileSAX.readXML("L6.3XML/data/MySqlResource.xdb")
    println(resource)
}