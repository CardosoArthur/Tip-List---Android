package br.com.fiap.arthur_cardoso_rm93186.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.arthur_cardoso_rm93186.model.TipModel

@Dao
interface TipDao {

    @Query("SELECT * FROM tips")
    fun getAll(): LiveData<List<TipModel>>

    @Insert
    fun insert(tip: TipModel)

    @Delete
    fun delete(tip: TipModel)
}