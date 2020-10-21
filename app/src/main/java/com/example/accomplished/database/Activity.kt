package com.example.accomplished.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = "activity")
data class Activity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val actName: String?,
    @ColumnInfo(name = "attribute") val actAttribute: String?,
    @ColumnInfo(name = "type") val actType: Int,
    @ColumnInfo(name = "category") val category: Int,
    @ColumnInfo(name = "value", defaultValue = "0") val value: String,
    @ColumnInfo(name= "feeling") val feel: String?
): Parcelable

