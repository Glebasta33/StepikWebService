package sandbox.my_chat

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import sandbox.my_chat.servlets.UsersServlet

fun main() {
    val servletHandler = ServletContextHandler(ServletContextHandler.SESSIONS).apply {
        addServlet(ServletHolder(UsersServlet()), "/enter")
    }

    Server(8080).apply {
        handler = servletHandler
        start()
        join()
    }
}