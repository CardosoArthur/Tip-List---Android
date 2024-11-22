package br.com.fiap.arthur_cardoso_rm93186.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fiap.arthur_cardoso_rm93186.model.TipModel


@Database(entities = [TipModel::class], version = 1)
abstract class TipDatabase: RoomDatabase() {
    abstract fun tipDao(): TipDao
}