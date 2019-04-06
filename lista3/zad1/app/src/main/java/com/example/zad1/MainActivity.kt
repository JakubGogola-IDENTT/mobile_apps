package com.example.zad1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.Spinner
import com.example.zad1.adapters.ToDoListAdapter
import com.example.zad1.containers.Task
import com.example.zad1.enums.SortType
import com.example.zad1.listeners.SortSpinnerActivity

class MainActivity : AppCompatActivity() {

    var model = Model()

    private lateinit var controller: Controller
    private lateinit var listView: ListView
    lateinit var toDoListAdapter: ToDoListAdapter
    private lateinit var taskTypesArray: Array<String>
    private lateinit var taskPrioritiesArray: Array<String>
    private lateinit var sortSpinner: Spinner
    private lateinit var checkBox: CheckBox

    lateinit var sortType: SortType
    var descSort: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller  = Controller(this, model)

        toDoListAdapter = ToDoListAdapter(this, model.listOfTask)
        listView = findViewById(R.id.tasksList)
        listView.adapter = toDoListAdapter

        taskTypesArray = resources.getStringArray(R.array.task_types)
        taskPrioritiesArray = resources.getStringArray(R.array.task_priorities)

        sortSpinner = findViewById(R.id.sortSpinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.sort_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sortSpinner.adapter = adapter
        }
        sortSpinner.onItemSelectedListener = SortSpinnerActivity(this)

        listView.setOnItemLongClickListener { _, _, position, _ ->
            model.removeTaskAt(position)
            toDoListAdapter.notifyDataSetChanged()
            true
        }

        checkBox = findViewById(R.id.descSortCheckBox)
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            descSort = !descSort
        }

        controller.loadList()
    }

    override fun onStop() {
        super.onStop()
        controller.saveList()
    }

    fun onAddTaskButtonClick(view: View) {
        val addTaskIntent = Intent(this, AddTaskActivity::class.java)
        addTaskIntent.putExtra("taskTypesArray", taskTypesArray)
        addTaskIntent.putExtra("taskPrioritiesArray", taskPrioritiesArray)
        startActivityForResult(addTaskIntent, 200)
    }

    fun onSortButtonClick(view: View) {
        println(sortType)
        controller.sort(descSort)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            val task = data?.getSerializableExtra("newTask") as Task
            model.addTask(task)
            toDoListAdapter.notifyDataSetChanged()
        }
    }
}
