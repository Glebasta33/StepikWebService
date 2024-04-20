package sandbox.my_chat.servlets

import sandbox.my_chat.html.PageGenerator
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UsersServlet : HttpServlet() {

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val pageVariables = createPageVariablesMap(request)
        pageVariables["message"] = ""

        with(response) {
            writer.println(PageGenerator.getPage("enter.html", pageVariables))
            contentType = "text/html;charset=utf-8"
            status = HttpServletResponse.SC_OK
        }
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val pageVariables = createPageVariablesMap(request)

        val message = request.getParameter("message") //получения значения параметра

        response.contentType = "text/html;charset=utf-8"

        if (message.isNullOrEmpty()) {
            response.status = HttpServletResponse.SC_FORBIDDEN
        } else {
            response.status = HttpServletResponse.SC_OK
        }

        pageVariables["message"] = message ?: ""

        response.writer.println(PageGenerator.getPage("enter.html", pageVariables))
    }

    companion object {
        private  fun createPageVariablesMap(request: HttpServletRequest) = mutableMapOf(
            "method" to request.method,
            "URL" to request.requestURL.toString(),
            "pathInfo" to (request.pathInfo ?: "not found"),
            "sessionId" to request.session.id,
            "parameters" to request.parameterMap.toString()
        )
    }
}