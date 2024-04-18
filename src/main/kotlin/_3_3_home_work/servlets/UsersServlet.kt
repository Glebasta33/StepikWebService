package _3_3_home_work.servlets

import _3_3_home_work.accounts.UsersProfileService
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UsersServlet(
    private val usersProfileService: UsersProfileService
) : HttpServlet() {

    //get public user profile
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val login = request.getParameter("login")

        response.contentType = "text/html;charset=utf-8"

        val user = usersProfileService.getUserByLogin(login)

        if (user != null) {
            response.writer.println("User: $user")
            response.status = HttpServletResponse.SC_OK
        } else {
            response.status = HttpServletResponse.SC_EXPECTATION_FAILED
            response.writer.println("User not found")
        }
    }

    //sign up
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val login = request.getParameter("login")
        val password = request.getParameter("pass")

        response.contentType = "text/html;charset=utf-8"

        val user = usersProfileService.getUserByLogin(login)
        if (user != null) {
            response.status = HttpServletResponse.SC_CONFLICT
            response.writer.println("User with this login already exists")
        } else {
            usersProfileService.addNewUser(login, password, "")
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