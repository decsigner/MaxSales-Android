package com.otokansosoti.maxsales.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AutoLoginDao {

    @Insert
    fun insert(autologin: AutoLoginTable)

    @Update
    fun update(autologin: AutoLoginTable)

    @Query("select * from autologin_table")
    fun getAllUsers() : LiveData<AutoLoginTable>

    @Query("DELETE FROM autologin_table WHERE cpf = :cpf")
    fun delete(cpf: String)
}