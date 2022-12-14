package com.example.fullnelsontodolist

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

// didn't have the repo set in the class parameters
class TaskViewModel(private val repository: TaskItemRepository): ViewModel()
{// set taskItems as LiveData.  Need to pass our repo in as an arg
    val taskItems: LiveData<List<TaskItem>> = repository.allTaskItems.asLiveData()
    // need addTaskItem to act on a coroutine now in Room
    fun addTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.insertTaskItem(taskItem)
    }
// self-explanitory functions updateTaskItem and setCompleted, now use Room
    fun updateTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.updateTaskItem(taskItem)
    }

    fun setCompleted(taskItem: TaskItem) = viewModelScope.launch {
        if (!taskItem.isCompleted())
            taskItem.completedDateString = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)
    }
}


// still need a viewModelFactory:
class TaskItemModelFactory(private val repository: TaskItemRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {   // Essentially try/catch to check if the ViewModel class is right
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repository) as T

        throw IllegalArgumentException("Incorrect ViewModel class")
    }
}