package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.ui.MyProfileSettingItem
import com.projectlite2.android.utils.IKotlinItemClickListener

class MyProfileSettingItemAdapter(private val settings: List<MyProfileSettingItem>):
        RecyclerView.Adapter<MyProfileSettingItemAdapter.ViewHolder>()
{
    //继承点击事件接口
    private var itemClickListener: IKotlinItemClickListener? = null
    // 提供点击事件set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val siName: TextView = itemView.findViewById(R.id.txtSettingItemName)
        val siIcon: ImageView =itemView.findViewById(R.id.imgIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.setting_item, parent, false)

        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val item = settings[position]
            MyApplication.showToast("${item.settingItemName} Clicked!")
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = settings[position]
        holder.siName.text = item.settingItemName
        holder.siIcon.setImageResource(item.icon) ;
        // 点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int = settings.size



}