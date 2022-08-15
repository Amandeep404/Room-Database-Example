package com.example.roomdatabasedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.databinding.ItemListBinding

class ItemAdapter(private val items:ArrayList<EmployeeEntity>,
                 // private val updateListener:(id:Int) ->Unit,
                  // private val deleteListener:(id:Int) ->Unit
):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val llMain: LinearLayout = view.findViewById(R.id.llMain)
        val tvName : TextView =  view.findViewById(R.id.tvName)
        val tvEmail : TextView =  view.findViewById(R.id.tvEmail)
        val ivEdit : ImageView = view.findViewById(R.id.ivEdit)
        val ivDelete : ImageView = view.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.tvName.text = item.name
        holder.tvEmail.text = item.email

        if (position%2 == 0){
            holder.llMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.grey))
        }else{
            holder.llMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }

        holder.ivEdit.setOnClickListener{
          //  updateListener.invoke(item.id)
        }
        holder.ivDelete.setOnClickListener{
          //  deleteListener.invoke(item.id)
        }
    }

    override fun getItemCount() = items.size
}