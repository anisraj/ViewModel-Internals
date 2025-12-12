package me.anisjamadar.viewmodel_internals.viewmodel

class DefaultFactory : ViewModelFactory {
    override fun create(modelClass: Class<out MyViewModel>): MyViewModel {
        return modelClass.getDeclaredConstructor().newInstance()
    }
}