package com.example.accomplished.database

class CategoryRepository(private val categoryDao: CategoryDao){
    suspend fun updateCat(category: Category){
        categoryDao.updateCat(category)
    }
}