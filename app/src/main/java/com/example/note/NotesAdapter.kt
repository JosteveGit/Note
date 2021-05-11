import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.UpdateAndDeleteNoteActivity


class NotesAdapter
    (
    private val activity: Activity,
    private var notes: List<Note>
) :
    RecyclerView.Adapter<NotesAdapter.MyView>() {
    inner class MyView(view: View) : RecyclerView.ViewHolder(view) {
        var noteView: TextView = view.findViewById<View>(R.id.noteView) as TextView
        var item: LinearLayout = view.findViewById<View>(R.id.layout) as LinearLayout

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyView {

        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.note_item,
                parent,
                false
            )

        return MyView(itemView)
    }

    override fun onBindViewHolder(
        holder: MyView,
        position: Int
    ) {

        holder.noteView.text = notes[position].content
        holder.item.setOnClickListener {
            activity.startActivity(Intent(activity, UpdateAndDeleteNoteActivity::class.java).apply {
                putExtra("id", notes[position].id.toString())
                putExtra(
                    "content", notes[position].content
                )
            })
        }
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }


    override fun getItemCount() = notes.size

}