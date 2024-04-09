package com.github.gltrusov

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Jetty включает:
 * Connector - принимает запрос по определённому порту.
 * Server: ThreadPool + Handler(обработчик запросов).
 *
 */
fun main() {
    val frontend = FrontendServlet()
    val servletHolder = ServletHolder(frontend)

    val server = Server(8080)
    val contextHandler = ServletContextHandler(ServletContextHandler.SESSIONS)
    server.handler = contextHandler
    contextHandler.addServlet(servletHolder, "/authform")

    server.start()
    server.join()
}

/**
 * Servlet - класс, расширяющий возможности сервера. (Как applet - сущность, расширяющая возможности браузера).
 * Servlet - принимает http-request, (...может сходить за данными в БД, или файловую систему, ...) и возвращает response (html страницу).ё
 */
class FrontendServlet : HttpServlet() {
    private var login: String = ""

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {

    }
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {

    }
}