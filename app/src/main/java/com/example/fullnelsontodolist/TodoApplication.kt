package com.example.fullnelsontodolist

import android.app.Application

class TodoApplication : Application()
{
    private val database by lazy { TaskItemDatabase.getDatabase(this) }
    val repository by lazy { TaskItemRepository(database.taskItemDao()) }
    // REMEMBER, NOW WE NEED TO UPDATE THE ANDROID MANIFEST!
}