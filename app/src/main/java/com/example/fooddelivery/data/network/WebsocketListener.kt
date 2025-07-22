package com.example.fooddelivery.data.network

import android.util.Log
import com.example.fooddelivery.domain.models.order.OrderUpdate
import com.example.fooddelivery.domain.use_case.order.UpdateOrderUseCase
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebsocketListener(
    private val updateOrderUseCase: UpdateOrderUseCase
) : WebSocketListener(){

    private val gson = Gson()
    private var webSocket: WebSocket? = null

    fun connectToWebsocket(){
        val websocketUrl = "ws://10.0.2.2:8080/orders/updates"
        val request = Request.Builder()
            .url(websocketUrl)
            .build()

        val client = OkHttpClient()
        webSocket = client.newWebSocket(request, this)
    }

    fun disconnectFromWebsocket(){
        webSocket?.close(1000, "Disconnected")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }

    override fun onFailure(
        webSocket: WebSocket,
        t: Throwable,
        response: Response?
    ) {
        super.onFailure(webSocket, t, response)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        val orderUpdate = gson.fromJson(text, OrderUpdate::class.java)
        Log.i("asdasdasdasdasd", "onMessage: $orderUpdate")
        updateOrderUseCase.updateOrderStatus(orderUpdate)
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
    }
}