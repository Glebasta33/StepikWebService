package sandbox.my_chat

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import sandbox.my_chat.chat.WebSocketChatServlet
import sandbox.my_chat.servlets.UsersServlet

fun main() {
    val servletHandler = ServletContextHandler(ServletContextHandler.SESSIONS).apply {
        addServlet(ServletHolder(UsersServlet()), "/enter")
        addServlet(ServletHolder(WebSocketChatServlet()), "/chat")
    }

    val resourceHandler = ResourceHandler().apply {
        isDirectoriesListed = false
        resourceBase = "static_resources/my_chat"
    }

    val handlers = HandlerList().apply {
        handlers = arrayOf(resourceHandler, servletHandler)
    }

    Server(8080).apply {
        handler = handlers
        start()
        join()
    }
}