package me.anisjamadar.viewmodel_internals

import android.app.Application
import androidx.lifecycle.ViewModel
import me.anisjamadar.viewmodel_internals.viewmodel.DefaultFactory
import me.anisjamadar.viewmodel_internals.viewmodel.ViewModelFactory

class MyApplication : Application() {
    private val viewModelMap = mutableMapOf<Class<out ViewModel>, ViewModel>()

    fun getViewModel(modelClass: Class<out ViewModel>, factory: ViewModelFactory = DefaultFactory()): ViewModel {
        viewModelMap[modelClass]?.let {
            return it
        }
        val viewModelInstance = factory.create(modelClass)
        viewModelMap[modelClass] = viewModelInstance
        return viewModelInstance
    }

    fun clearViewModel(modelClass: Class<ViewModel>) {
        viewModelMap.remove(modelClass)
    }
}