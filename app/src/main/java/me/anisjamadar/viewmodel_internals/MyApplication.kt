package me.anisjamadar.viewmodel_internals

import android.app.Application
import me.anisjamadar.viewmodel_internals.viewmodel.DefaultFactory
import me.anisjamadar.viewmodel_internals.viewmodel.MyViewModel
import me.anisjamadar.viewmodel_internals.viewmodel.ViewModelFactory

class MyApplication : Application() {
    private val viewModelMap = mutableMapOf<Class<out MyViewModel>, MyViewModel>()

    fun getViewModel(modelClass: Class<out MyViewModel>, factory: ViewModelFactory = DefaultFactory()): MyViewModel {
        viewModelMap[modelClass]?.let {
            return it
        }
        val viewModelInstance = factory.create(modelClass)
        viewModelMap[modelClass] = viewModelInstance
        return viewModelInstance
    }

    fun clearViewModel(modelClass: Class<MyViewModel>) {
        viewModelMap.remove(modelClass)
    }
}