package _4_web_sockets.chat

import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

class ChatService(
    private val webSockets: MutableSet<ChatWebSocket> = Collections.newSetFromMap(ConcurrentHashMap())
) {

    fun sendMessage(data: String) {
        webSockets.forEach { userSocket ->
            try {
                userSocket.sendString(data)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun add(webSocket: ChatWebSocket) {
        webSockets.add(webSocket)
    }

    fun remove(webSocket: ChatWebSocket) {
        webSockets.remove(webSocket)
    }

}