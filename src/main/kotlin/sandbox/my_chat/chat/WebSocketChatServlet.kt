package sandbox.my_chat.chat

import org.eclipse.jetty.websocket.servlet.*
import javax.servlet.annotation.WebServlet

@WebServlet(name = "WebSocketChatServlet", urlPatterns = ["/chat"])
class WebSocketChatServlet(
    private val chatService: ChatService = ChatService()
) : WebSocketServlet() {

    override fun configure(factory: WebSocketServletFactory?) {
        factory?.policy?.idleTimeout = LOGOUT_TIME
            factory?.creator = WebSocketCreator { req, resp -> ChatWebSocket(chatService) }
    }

    companion object {
        private const val LOGOUT_TIME = 10 * 60 * 1000L
    }

}