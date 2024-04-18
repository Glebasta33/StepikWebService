package _3_3_home_work

import _3_3_home_work.accounts.UsersProfileService
import _3_3_home_work.db.UserProfileDBService
import _3_3_home_work.servlets.UsersServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder

fun main() {
    val dbService = UserProfileDBService()
    val usersProfileService = UsersProfileService(dbService)

    val contextHandler = ServletContextHandler(ServletContextHandler.SESSIONS).apply {
        addServlet(ServletHolder(UsersServlet(usersProfileService)), "/api/v2/signup")
    }

    val resourceHandler = ResourceHandler().apply {
        isDirectoriesListed = false
        resourceBase = "static_resources"
    }

    val handlers = HandlerList().apply {
        handlers = arrayOf(resourceHandler, contextHandler)
    }

    Server(8080).apply {
        handler = handlers
        start()
        join()
    }
}