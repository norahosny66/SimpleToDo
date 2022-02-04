package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Path

class MainActivity : AppCompatActivity() {
    var listoftasks = mutableListOf<String>()
    lateinit var adapter:TasksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val onLongClickListener= object : TasksAdapter.OnLongClickListener{
            override fun OnItemLongClickled(position: Int) {
                listoftasks.removeAt(position)
                adapter.notifyDataSetChanged()
                saveitems()
            }

        }
        loaditems()
        val rv= findViewById<RecyclerView>(R.id.r) as RecyclerView
        adapter = TasksAdapter(listoftasks,onLongClickListener)
        rv.adapter = adapter
        // Set layout manager to position the items
        rv.layoutManager = LinearLayoutManager(this)
        val inputText = findViewById<EditText>(R.id.addtask)
        findViewById<Button>(R.id.ADD).setOnClickListener {
        val userTask = inputText.text.toString()
            listoftasks.add(userTask)
            adapter.notifyItemInserted(listoftasks.size-1)
            inputText.setText("")
            saveitems()
        }
    }
    fun GetDataFile():File{
        return File(filesDir,"Data.txt")
    }
    fun loaditems(){
        try {
            listoftasks = org.apache.commons.io.FileUtils.readLines(GetDataFile(), Charset.defaultCharset())
        }
        catch(ioException:IOException){
            ioException.printStackTrace()
        }
    }
    fun saveitems(){
        try {
            org.apache.commons.io.FileUtils.writeLines(GetDataFile(), listoftasks)
        }
        catch(ioException:IOException){
            ioException.printStackTrace()
        }
    }
}