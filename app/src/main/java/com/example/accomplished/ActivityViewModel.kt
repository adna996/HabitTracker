package com.example.accomplished

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.accomplished.database.Activity
import com.example.accomplished.database.ActivityRepository
import com.example.accomplished.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ActivityRepository
    init {
        val activityDao = AppDatabase.getInstance(
            application
        ).activityDao()
        repository = ActivityRepository(activityDao)

    }
    fun updateCat(activity: Activity){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateAct(activity)
        }
    }

}