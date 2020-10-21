package com.example.accomplished.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Type(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val typeName: String?,
    @ColumnInfo(name = "measurement") val actAttribute: String?
)

