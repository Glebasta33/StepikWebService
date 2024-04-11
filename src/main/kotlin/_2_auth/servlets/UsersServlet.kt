package _2_auth.servlets

import _2_auth.accounts.AccountService
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UsersServlet(
    private val accountService: AccountService
) : HttpServlet() {

    //get public user profile
    override fun doGet(requset: HttpServletRequest, response: HttpServletResponse) {

    }

    //sign up
    override fun doPost(requset: HttpServletRequest, response: HttpServletResponse) {

    }

    //change profile
    override fun doPut(requset: HttpServletRequest, response: HttpServletResponse) {

    }

    //unregister
    override fun doDelete(requset: HttpServletRequest, response: HttpServletResponse) {

    }
}