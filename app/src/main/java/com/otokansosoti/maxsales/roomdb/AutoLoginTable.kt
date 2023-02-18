package com.otokansosoti.maxsales.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "autologin_table")
data class AutoLoginTable(
    val cpf: String,
    val password: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
    )