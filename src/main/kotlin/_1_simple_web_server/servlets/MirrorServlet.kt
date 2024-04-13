package _1_simple_web_server.servlets

import _1_simple_web_server.templater.PageGenerator
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MirrorServlet : HttpServlet() {

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val pageVariables = createPageVariablesMap(request)
        val key = request.getParameter("key") //получения значения параметра

        pageVariables["key"] = key ?: ""

        with(response) {
            writer.println(PageGenerator.getPage("page_hello.html", pageVariables))
            contentType = "text/html;charset=utf-8"
            status = HttpServletResponse.SC_OK
        }
    }

    companion object {
        private  fun createPageVariablesMap(request: HttpServletRequest) = mutableMapOf(
            "method" to request.method
        )
    }
}