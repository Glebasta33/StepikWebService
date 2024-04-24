package com.github.gltrusov

import com.github.gltrusov.account_server.AccountServer
import com.github.gltrusov.account_server.AccountServerController
import com.github.gltrusov.account_server.AccountServerControllerMBean
import com.github.gltrusov.account_server.AccountServerI
import com.github.gltrusov.servlet.HomePageServlet
import org.apache.logging.log4j.LogManager
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import java.lang.management.ManagementFactory
import javax.management.MBeanServer
import javax.management.ObjectName

private val logger = LogManager.getLogger()

fun main(args: Array<String>) {
    if (args.size != 1) {

        logger.error("Use port as the first argument of program")
        System.exit(1)
    }

    val port = args[0].toInt()

    logger.info("Starting server at http://127.0.0.1:$port")

    val accountServer: AccountServerI = AccountServer(usersLimit = 1)

    /**
     * Комнда jconsole запустит консоль для управления AccountServerController,
     * в которой можно посмотреть статистику и даже изменить конфигурацию сервера (лимит пользователей).
     */
    val serverStatistics: AccountServerControllerMBean = AccountServerController(accountServer)
    val mbs: MBeanServer = ManagementFactory.getPlatformMBeanServer()
    val name = ObjectName("ServerManager:type=${AccountServerController::class.java.kotlin.simpleName}")
    mbs.registerMBean(serverStatistics, name)

    val server = Server(port)
    val contextHandler = ServletContextHandler(ServletContextHandler.SESSIONS).apply {
        addServlet(ServletHolder(HomePageServlet(accountServer)), HomePageServlet.PAGE_URL)
    }

    val resourceHandler = ResourceHandler()
    resourceHandler.isDirectoriesListed = true
    resourceHandler.resourceBase = "static"

    val handlers = HandlerList()
    handlers.handlers = arrayOf(resourceHandler, contextHandler)

    server.handler = handlers

    server.start()
    logger.info("Server started")
    server.join()
}