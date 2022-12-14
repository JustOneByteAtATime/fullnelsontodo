package com.example.fullnelsontodolist

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

// Create an entity for the TaskItems
@Entity(tableName = "task_item_table")
class TaskItem(
    // replace the variables with column info
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "dueTimeString") var dueTimeString: String?,
    @ColumnInfo(name = "completedDateString") var completedDateString: String?,
    // generate a primary key for the id:
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
{
    // Errors with completedDate in isCompleted.  Need to create a function:
    // If completed date string is null, put null into local date, otherwise pass string of completed date into a local date
    private fun completedDate(): LocalDate? = if (completedDateString == null) null else LocalDate.parse(completedDateString, dateFormatter)
    // Similar method of completedDate needs to apply to dueTime()
    fun dueTime(): LocalTime? = if (dueTimeString == null) null else LocalTime.parse(dueTimeString, timeFormatter)
    fun isCompleted() = completedDate() != null
    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int = if(isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun mintCream(context: Context) = ContextCompat.getColor(context, R.color.MintCream)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
    // Use companion object ot make sure we use the same formatter
    companion object {
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}