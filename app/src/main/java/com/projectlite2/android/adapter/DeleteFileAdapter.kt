package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.Msg
import com.projectlite2.android.R
import com.projectlite2.android.model.DeleteFileCard

class DeleteFileAdapter(val deleteList: List<DeleteFileCard>) : RecyclerView.Adapter<DeleteFileAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cdCardBg: CardView = itemView.findViewById(R.id.WPDeleteCardBackground)
        val cdConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
        val cdName: TextView = itemView.findViewById(R.id.txtName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteFileAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workplace_deletefile_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeleteFileAdapter.ViewHolder, position: Int) {
        val card = deleteList[position]

        holder.cdName.text = card.name
    }

    override fun getItemCount() = deleteList.size
}