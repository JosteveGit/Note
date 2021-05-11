import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.String
import java.util.*

class SimpleDatabase(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {
    // creating tables
    override fun onCreate(db: SQLiteDatabase) {
        val createDb =
            ("CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_CONTENT + " TEXT"
                    + " )")
        db.execSQL(createDb)
    }

    // upgrade db if older version exists
    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        if (oldVersion >= newVersion) return
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addNote(note: Note): Long {
        val db = this.writableDatabase
        val v = ContentValues()
        v.put(KEY_CONTENT, note.content)

        // inserting data into db
        return db.insert(TABLE_NAME, null, v)
    }

    fun getNote(id: Long): Note {
        val db = this.writableDatabase
        val query = arrayOf(
            KEY_ID,
            KEY_CONTENT

            )
        val cursor = db.query(
            TABLE_NAME,
            query,
            "$KEY_ID=?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return Note(
            cursor!!.getString(0).toLong(),
            cursor.getString(1)
        )
    }

    val allNotes: List<Note>
        get() {
            val allNotes: MutableList<Note> = ArrayList<Note>()
            val query =
                "SELECT * FROM $TABLE_NAME ORDER BY $KEY_ID DESC"
            val db = this.readableDatabase
            val cursor = db.rawQuery(query, null)
            Log.d("Log Cursor", cursor.count.toString())
            if (cursor.moveToFirst()) {
                do {
                    val note = Note()
                    note.id = (cursor.getString(0).toLong())
                    note.content = (cursor.getString(1))
                    allNotes.add(note)
                } while (cursor.moveToNext())
            }
            Log.d("Log All notes", allNotes.size.toString())

            return allNotes
        }

    fun editNote(note: Note): Int {
        val db = this.writableDatabase
        val c = ContentValues()
        Log.d(
            "Edited",
            """Edited Title: -> ${note.content.toString()}
 ID -> ${note.id}"""
        )
        c.put(KEY_CONTENT, note.content)
        return db.update(
            TABLE_NAME,
            c,
            "$KEY_ID=?",
            arrayOf(String.valueOf(note.id))
        )
    }

    fun deleteNote(id: Long) {
        val db = this.writableDatabase
        db.delete(
            TABLE_NAME,
            "$KEY_ID=?",
            arrayOf(id.toString())
        )
        db.close()
    }

    companion object {
        // declare require values
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "SimpleDB"
        private const val TABLE_NAME = "SimpleTable"

        // declare table column names
        private const val KEY_ID = "id"
        private const val KEY_CONTENT = "content"
    }
}