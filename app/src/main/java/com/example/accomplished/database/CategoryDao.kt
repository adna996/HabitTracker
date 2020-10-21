package com.example.accomplished.database

import androidx.room.*

@Dao
public interface CategoryDao{
    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Category>

    @Query("SELECT * FROM category WHERE name LIKE :first  LIMIT 1")
    fun findByName(first: String): Category

    @Insert
    fun insertAll(obj: List<Category>)

    @Query("insert into category(name, `desc`) values (:n, :d) ")
    fun insertCat(n: String, d: String)

    @Delete
    fun delete(obj: Category)

    @Query("delete from category where uid like :id")
    fun deleteCategory(id: Int)

    @Update
    fun updateCat(obj: Category)

    @Query("update category set name=:c_name, `desc`=:c_desc where uid = :id")
    fun updateNameDesc(c_name: String?, c_desc: String?, id: Int)
}