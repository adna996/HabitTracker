package com.example.accomplished.database

class ActivityRepository (private val activityDao: ActivityDao) {
    fun updateAct(activity: Activity){
        activityDao.updateAct(activity)
    }
}