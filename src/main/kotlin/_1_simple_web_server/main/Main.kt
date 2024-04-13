package _1_simple_web_server.main

import _1_simple_web_server.servlets.AllRequestServlet
import _1_simple_web_server.servlets.MirrorServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder

fun main() {
    val servletHandler = ServletContextHandler(ServletContextHandler.SESSIONS).apply {
        addServlet(ServletHolder(AllRequestServlet()), "/form")
        addServlet(ServletHolder(MirrorServlet()), "/mirror")
    }

    Server(8080).apply {
        handler = servletHandler
        start()
        println("Server started")
        join()
    }
}