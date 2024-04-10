package com.github.gltrusov._1_simple_web_server.templater

import freemarker.template.Configuration
import freemarker.template.Template
import java.io.StringWriter

object PageGenerator {
    private const val HTML_DIR = "\\src\\main\\kotlin\\_1_simple_web_server\\templates\\"
    private val config: Configuration = Configuration()

    fun getPage(filename: String, data: Map<String, Any>): String {

        val writer = StringWriter()
        try {
            val template: Template = config.getTemplate(HTML_DIR + filename)
            template.process(data, writer)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return writer.toString()
    }
}