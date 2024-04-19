package _4_web_sockets


import _4_web_sockets.chat.WebSocketChatServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder

fun main() {

    val contextHandler = ServletContextHandler(ServletContextHandler.SESSIONS).apply {
        addServlet(ServletHolder(WebSocketChatServlet()), "/chat")
    }

    val resourceHandler = ResourceHandler().apply {
        isDirectoriesListed = false
        resourceBase = "static_resources/chat"
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