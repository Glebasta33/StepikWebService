package com.github.gltrusov._1_simple_web_server.main

import com.github.gltrusov._1_simple_web_server.servlets.AllRequestServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder

fun main() {
    val allRequestServlet = AllRequestServlet()

    val servletHandler = ServletContextHandler(ServletContextHandler.SESSIONS)
    servletHandler.addServlet(ServletHolder(allRequestServlet), "/form")

    Server(8080).apply {
        handler = servletHandler
        start()
        join()
    }
}