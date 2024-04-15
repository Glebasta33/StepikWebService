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
        response.contentType = "application/json"
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
        val login = request.getParameter("login")
        val password = request.getParameter("pass")

        response.contentType = "application/json"
        if (login == null || password == null) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            return
        }

        val profile = accountService.getUserByLogin(login)
        if (profile == null || profile.pass != password) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return
        }

        accountService.addSession(request.session.id, profile)
        val json = gson.toJson(profile)
        response.writer.println(json)
        response.status = HttpServletResponse.SC_OK
    }

    //sing out
    override fun doDelete(request: HttpServletRequest, response: HttpServletResponse) {
        val sessionId = request.session.id
        val profile = accountService.getUserBySessionId(sessionId)

        response.contentType = "text/html;charset=utf-8"
        if (profile == null) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
        } else {
            accountService.deleteSession(sessionId)
            response.writer.println("Goodbye!")
            response.status = HttpServletResponse.SC_OK
        }
    }
}