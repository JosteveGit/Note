package com.example.note

import Note
import SimpleDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_enter_note.*

class EnterNoteActivity : AppCompatActivity() {
    lateinit var database: SimpleDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_note)
        database = SimpleDatabase(this)

        saveNote.setOnClickListener {
            val content = noteContent.text.toString()
            if(content.isNotEmpty()){
                database.addNote(Note(content))
                Toast.makeText(this@EnterNoteActivity, "Note Saved", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@EnterNoteActivity, "Can't save empty note", Toast.LENGTH_LONG).show()
            }
        }
    }
}