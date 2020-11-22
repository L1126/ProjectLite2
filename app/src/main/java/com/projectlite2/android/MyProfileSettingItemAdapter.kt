package com.projectlite2.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyProfileSettingItemAdapter(private val settings: List<MyProfileSettingItem>):
        RecyclerView.Adapter<MyProfileSettingItemAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val siName: TextView = itemView.findViewById(R.id.txtSettingItemName)
       // val siIcon: ImageView =itemView.findViewById(R.id.imgIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProfileSettingItemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.setting_item, parent, false)


        val viewHolder = ViewHolder(view)
//        viewHolder.itemView.setOnClickListener {
//            val position = viewHolder.adapterPosition
//            val item = settings[position]
//            MyApplication.showToast("${item.settingItemName} Clicked!")
//        }


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyProfileSettingItemAdapter.ViewHolder, position: Int) {
        val item = settings[position]
        holder.siName.text = item.settingItemName
        //holder.siIcon.setImageResource(item.icon) ;

    }

    override fun getItemCount(): Int = settings.size


}