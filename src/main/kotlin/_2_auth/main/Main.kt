package _2_auth.main

import _2_auth.accounts.AccountService
import _2_auth.accounts.model.UserProfile
import _2_auth.servlets.SessionsServlet
import _2_auth.servlets.UsersServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder

fun main() {
    val accountService = AccountService().apply {
        val admin = UserProfile("admin", "admin", "admin@mail.com")
        addNewUser(admin)
    }

    val contextHandler = ServletContextHandler(ServletContextHandler.SESSIONS).apply {
        addServlet(ServletHolder(UsersServlet(accountService)), "/api/v1/users")
        addServlet(ServletHolder(SessionsServlet(accountService)), "/api/v1/sessions")
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