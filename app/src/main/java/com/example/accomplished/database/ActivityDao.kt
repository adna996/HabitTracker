package com.example.accomplished.database

import androidx.room.*

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activity")
    fun getAll(): List<Activity>

    @Query("SELECT * FROM activity WHERE category LIKE (:Id)")
    fun loadAllByIds(Id: Int): List<Activity>

    @Query("SELECT * FROM activity WHERE name LIKE :first  LIMIT 1")
    fun findByName(first: String): Activity

    @Insert
     fun insertAll(obj: List<Activity>)

    @Query("delete from activity")
     fun deleteAll()

    @Query("delete from activity where uid like :id")
    fun deleteActivity(id: Int)

    @Query("insert into activity(name, attribute, type, category, value) values (:n, :a, :t, :c, :v)")
    fun insertActivity(n: String, a: String, t: Int, c: Int, v:String)

    @Query("update activity set value = :v where uid like :id")
    fun setValue(id: Int, v:String)

    @Query("select value from activity where uid like :id")
    fun getValue(id: Int): String

    @Query("select attribute from activity where uid like :id")
    fun getAttr(id: Int): String

    @Update
    fun updateAct(obj: Activity)

    @Query("update activity set attribute = :at where uid like :id")
    fun setAttribute(id: Int, at: String)
}

