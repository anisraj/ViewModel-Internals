package me.anisjamadar.viewmodel_internals.next

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NextViewModel(repository: NextRepository) : ViewModel() {
    private val _counter = MutableStateFlow(repository.initialCounter)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    fun increaseCounter() {
        _counter.value = _counter.value + 1
    }
}