package com.example.simpletodo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(val listofitems: List<String>, val LongClickListener:OnLongClickListener): RecyclerView.Adapter<TasksAdapter.ViewHolder>(){
    interface OnLongClickListener{
        fun OnItemLongClickled(position: Int)
    }
 inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
   val textView: TextView
     init{
         textView=itemView.findViewById(android.R.id.text1)
         itemView.setOnLongClickListener {
             LongClickListener.OnItemLongClickled(adapterPosition)
             true
         }
     }
 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
       return listofitems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val item = listofitems.get(position)
        holder.textView.text=item

    }
}