package sandbox.my_chat.chat

import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage
import org.eclipse.jetty.websocket.api.annotations.WebSocket

@WebSocket
class ChatWebSocket(
    private val chatService: ChatService
) {
    private lateinit var session: Session

    @OnWebSocketConnect
    fun onOpen(session: Session) {
        chatService.add(this)
        this.session = session
    }

    @OnWebSocketMessage
    fun onMessage(data: String) {
        chatService.sendMessage(data)
    }

    @OnWebSocketClose
    fun onClose(statusCode: Int, reason: String) {
        chatService.remove(this)
    }

    fun sendString(data: String) {
        try {
            session.remote.sendString(data)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}