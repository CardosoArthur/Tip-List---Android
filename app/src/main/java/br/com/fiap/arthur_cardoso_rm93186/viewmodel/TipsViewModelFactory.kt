package br.com.fiap.arthur_cardoso_rm93186.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TipsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <tip : ViewModel> create(modelClass: Class<tip>): tip {
        if (modelClass.isAssignableFrom(TipsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TipsViewModel(application) as tip
        }
        throw IllegalArgumentException("View Model desconhecido")
    }
}