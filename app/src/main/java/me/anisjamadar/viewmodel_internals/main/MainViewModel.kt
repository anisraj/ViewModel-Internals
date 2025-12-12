package me.anisjamadar.viewmodel_internals.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.anisjamadar.viewmodel_internals.viewmodel.MyViewModel

class MainViewModel : MyViewModel {

    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    fun increaseCounter() {
        _counter.value = _counter.value + 1
    }
}