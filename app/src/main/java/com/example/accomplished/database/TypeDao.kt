package com.example.accomplished.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TypeDao {
    @Query("SELECT * FROM type")
    fun getAll(): List<Type>

    @Query("SELECT * FROM type WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Type>

    @Query("SELECT * FROM type WHERE name LIKE :first  LIMIT 1")
    fun findByName(first: String): Type

    @Insert
    fun insertAll(cats: List<Type>)

    @Delete
    fun delete(cat: Type)

}