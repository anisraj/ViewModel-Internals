package me.anisjamadar.viewmodel_internals.viewmodel

import androidx.lifecycle.ViewModel

fun interface ViewModelFactory {
    fun create(modelClass: Class<out MyViewModel>): MyViewModel
}