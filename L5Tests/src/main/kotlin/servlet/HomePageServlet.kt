package com.github.gltrusov.servlet

import com.github.gltrusov.account_server.AccountServerI
import org.apache.logging.log4j.LogManager
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HomePageServlet(
    private val accountServer: AccountServerI
) : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/html;charset=utf-8"
        val remove = req.getParameter("remove")

        if (remove != null) {
            accountServer.removerUser()
            resp.writer.println("Hasta la vista")
            resp.status = HttpServletResponse.SC_OK
            return
        }

        val limit = accountServer.getUserLimit()
        val count = accountServer.getUserCount()

        logger.info("Limit: {}. Count: {}", limit, count)

        if (limit > count) {
            logger.info("User pass")
            accountServer.addNewUser()
            resp.writer.println("Hello, world!")
            resp.status = HttpServletResponse.SC_OK
        } else {
            logger.info("User were rejected")
            resp.writer.println("Server is closed for maintenance!")
            resp.status = HttpServletResponse.SC_FORBIDDEN
        }
    }

    companion object {
        const val PAGE_URL = "/home"
        val logger = LogManager.getLogger(HomePageServlet::class.java.kotlin.simpleName)
    }
}