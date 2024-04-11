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

class Main

fun main() {
    val accountService = AccountService().apply {
        addNewUser(UserProfile("admin", "admin", "admin@mail.com"))
    }

    val contextHandler = ServletContextHandler(ServletContextHandler.SESSIONS).apply {
        addServlet(ServletHolder(UsersServlet(accountService)), "/api/v1/users")
        addServlet(ServletHolder(SessionsServlet(accountService)), "/api/v1/sessions")
    }

    val staticDir = Main::class.java.classLoader.getResource("index.html")?.toExternalForm()
    println(staticDir)

    //TODO: Изучить ResourceHandler
    val resourceHandler = ResourceHandler()
    resourceHandler.resourceBase = staticDir?.replace("index.html", "")

    val handlers = HandlerList().apply {
        handlers = arrayOf(resourceHandler, contextHandler)
    }

    Server(8080).apply {
        handler = resourceHandler
        handler = contextHandler
        start()
        join()
    }
}