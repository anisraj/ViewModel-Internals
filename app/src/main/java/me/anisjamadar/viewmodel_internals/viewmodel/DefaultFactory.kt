package me.anisjamadar.viewmodel_internals.viewmodel

import androidx.lifecycle.ViewModel

class DefaultFactory : ViewModelFactory {
    override fun create(modelClass: Class<out ViewModel>): ViewModel {
        return modelClass.getDeclaredConstructor().newInstance()
    }
}