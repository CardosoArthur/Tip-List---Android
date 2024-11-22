package br.com.fiap.arthur_cardoso_rm93186.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import br.com.fiap.arthur_cardoso_rm93186.data.TipDao
import br.com.fiap.arthur_cardoso_rm93186.data.TipDatabase
import br.com.fiap.arthur_cardoso_rm93186.model.TipModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TipsViewModel(application: Application) : AndroidViewModel(application)  {

    private val tipDao: TipDao
    val tipsLiveData: LiveData<List<TipModel>>

    init {
        val database = Room.databaseBuilder(getApplication(),TipDatabase::class.java,"tips_database").build()

        tipDao = database.tipDao()
        tipsLiveData = tipDao.getAll()
    }

    fun addTip(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {val newTip = TipModel(title = title, description = description)
            tipDao.insert(newTip)
        }
    }

}