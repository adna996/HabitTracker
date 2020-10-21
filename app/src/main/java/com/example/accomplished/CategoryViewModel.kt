package com.example.accomplished

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.accomplished.database.AppDatabase
import com.example.accomplished.database.Category
import com.example.accomplished.database.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application): AndroidViewModel(application) {
    private val repository: CategoryRepository
    init {
        val categoryDao = AppDatabase.getInstance(
            application
        ).categoryDao()
        repository = CategoryRepository(categoryDao)

    }
    fun updateCat(category: Category){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateCat(category)
        }
    }

}