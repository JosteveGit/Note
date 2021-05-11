package com.example.note

import NotesAdapter
import SimpleDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_show_all_notes.*

class ShowAllNotesActivity : AppCompatActivity() {


   lateinit var adapter: NotesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_notes)
        val database: SimpleDatabase = SimpleDatabase(this)
        val allNotes = database.allNotes
        adapter = NotesAdapter(this, allNotes)


        val manager = LinearLayoutManager(
            this@ShowAllNotesActivity,
            LinearLayoutManager.VERTICAL,
            false
        )

        notes!!.layoutManager = manager

        notes!!.adapter = adapter

    }

    override fun onResume() {
        val database: SimpleDatabase = SimpleDatabase(this)
        val allNotes = database.allNotes
        adapter.setNotes(allNotes)
        super.onResume()
    }
}