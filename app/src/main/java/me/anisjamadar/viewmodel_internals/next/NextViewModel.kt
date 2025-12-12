package me.anisjamadar.viewmodel_internals.next

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.anisjamadar.viewmodel_internals.viewmodel.MyViewModel

class NextViewModel(repository: NextRepository) : MyViewModel {
    private val _counter = MutableStateFlow(repository.initialCounter)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    fun increaseCounter() {
        _counter.value = _counter.value + 1
    }
}