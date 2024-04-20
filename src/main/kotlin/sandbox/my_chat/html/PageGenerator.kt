package sandbox.my_chat.html

import freemarker.template.Configuration
import freemarker.template.Template
import java.io.StringWriter

object PageGenerator {
    private const val HTML_DIR = "\\src\\main\\kotlin\\sandbox\\my_chat\\html\\"
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