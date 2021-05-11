package com.example.note

import Note
import SimpleDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_enter_note.*
import kotlinx.android.synthetic.main.activity_update_and_delete_note.*
import kotlinx.android.synthetic.main.activity_update_and_delete_note.noteContent

class UpdateAndDeleteNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_and_delete_note)

        val database: SimpleDatabase = SimpleDatabase(this)

        val intent = intent

        val id = intent.getStringExtra("id")

        noteContent.setText(intent.getStringExtra("content"))

        Log.d("Note id:", id.toString())

        updateNote.setOnClickListener {
            val content = noteContent.text.toString()
            if(content.isNotEmpty()){
               val edit =  database.editNote(Note(id!!.toLong(), content))
                Toast.makeText(this@UpdateAndDeleteNoteActivity, "Note Updated", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@UpdateAndDeleteNoteActivity, "Can't update empty note", Toast.LENGTH_LONG).show()
            }
        }

        deleteNote.setOnClickListener {
            database.deleteNote(id!!.toLong())
            Toast.makeText(this@UpdateAndDeleteNoteActivity, "Note Deleted", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}