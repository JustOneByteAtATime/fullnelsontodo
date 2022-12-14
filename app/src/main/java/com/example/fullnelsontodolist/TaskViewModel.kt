package com.example.fullnelsontodolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TaskViewModel: ViewModel() {
    var taskItems = MutableLiveData<MutableList<TaskItem>>()

    init{
        taskItems.value = mutableListOf()
    }

    fun addTaskItem(newTask: TaskItem){
        val list = taskItems.value
        list!!.add(newTask)
        taskItems.postValue(list)
    }
    //The double exclamation mark (!!) after list above, is used because we know the list is NOT null

    // function now obsolete
//    fun updateTaskItem(id: UUID, name: String, desc: String, dueTime: LocalTime?)
//    {
//        val list = taskItems.value
//        val task = list!!.find { it.id == id }!!
//        task.name = name
//        task.desc = desc
//        task.dueTime = dueTime
//        taskItems.postValue(list)
//    }

    // function also now obsolete
//    fun setCompleted(taskItem: TaskItem){
//        val list = taskItems.value
//        val task = list!!.find { it.id == taskItem.id }!!
//        if (task.completedDate == null)
//            task.completedDate = LocalDate.now()
//        taskItems.postValue(list)
//    }

    fun updateTaskItem(id: TaskItem) {

    }

}