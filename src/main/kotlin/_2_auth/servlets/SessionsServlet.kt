package _2_auth.servlets

import _2_auth.accounts.AccountService
import com.google.gson.Gson
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SessionsServlet(
    private val accountService: AccountService
) : HttpServlet() {

    private val gson = Gson()

    //get logged user profile
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val sessionId = request.session.id
        val profile = accountService.getUserBySessionId(sessionId)
        response.contentType = "text/html;charset=utf-8"
        if (profile == null) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
        } else {
            val json = gson.toJson(profile)
            response.writer.println(json)
            response.status = HttpServletResponse.SC_OK
        }
    }

    //sign in
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {

    }

    //sing out
    override fun doDelete(request: HttpServletRequest, response: HttpServletResponse) {

    }
}