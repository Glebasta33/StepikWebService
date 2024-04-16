package _2_auth.servlets

import _2_auth.accounts.AccountService
import _2_auth.accounts.model.UserProfile
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UsersServlet(
    private val accountService: AccountService
) : HttpServlet() {

    //get public user profile
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {

    }

    //sign up
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val login = request.getParameter("login")
        val password = request.getParameter("pass")

        response.contentType = "text/html;charset=utf-8"

        val user = accountService.getUserByLogin(login)
        if (user != null) {
            response.status = HttpServletResponse.SC_CONFLICT
            response.writer.println("User with this login already exists")
        } else {
            accountService.addNewUser(UserProfile(login, password, null))
            response.writer.println("User created: $login")
            response.status = HttpServletResponse.SC_OK
        }
    }

    //change profile
    override fun doPut(request: HttpServletRequest, response: HttpServletResponse) {

    }

    //unregister
    override fun doDelete(request: HttpServletRequest, response: HttpServletResponse) {

    }
}