package com.example.fooddelivery.presentation.order.order_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.core.domain.util.onError
import com.example.fooddelivery.core.domain.util.onSuccess
import com.example.fooddelivery.data.network.WebsocketListener
import com.example.fooddelivery.domain.models.order.Order
import com.example.fooddelivery.domain.use_case.order.GetOrderDetailsUseCase
import com.example.fooddelivery.domain.use_case.order.GetOrdersUseCase
import com.example.fooddelivery.domain.use_case.order.UpdateOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersListViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val updateOrderUseCase: UpdateOrderUseCase,
    private val getOrderDetailsUseCase: GetOrderDetailsUseCase
) : ViewModel() {

    val websocketListener = WebsocketListener(updateOrderUseCase)

    private val _state = MutableStateFlow(OrderListState())
    val state = _state
        .onStart { loadOrders() }
        .stateIn(
            viewModelScope,
            SharingStarted.Companion.WhileSubscribed(5000L),
            OrderListState()
        )

    private val _events = Channel<OrderListEvent>()
    val events = _events.receiveAsFlow()

    init {
        fetchLatestOrders()
        websocketListener.connectToWebsocket()
    }

    private fun fetchLatestOrders() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            getOrdersUseCase.fetchLatestOrders()
                .onSuccess {
                    _state.update { it.copy(isLoading = false) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(OrderListEvent.Error(error))
                }
        }
    }


    fun onAction(action: OrderListAction) {
        when (action) {
            is OrderListAction.OnOrderClick -> {
                selectOrder(action.order)
                fetchOrderDetails(action.order.id)
            }
        }
    }

    private fun selectOrder(order: Order) {
        _state.update { it.copy(selectedOrderId = order.id) }
    }

    private fun fetchOrderDetails(orderId: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            getOrderDetailsUseCase.fetchLatestOrderDetails(orderId)
                .onSuccess {
                    _state.update { it.copy(isLoading = false) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(OrderListEvent.Error(error))
                }
        }
    }

    private fun loadOrders() {
        viewModelScope.launch {
            getOrdersUseCase.getOrder().collect { orders ->
                _state.update {
                    it.copy(
                        orders = orders
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        websocketListener.disconnectFromWebsocket()
    }
}