package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.app.MyApplication
import com.projectlite2.android.model.SysSettingCard
import com.projectlite2.android.utils.IKotlinItemClickListener

class SysSettingAdapter(private val settings: List<SysSettingCard>):
        RecyclerView.Adapter<SysSettingAdapter.ViewHolder>()
{

    //继承点击事件接口
    private var itemClickListener: IKotlinItemClickListener? = null
    // 提供点击事件set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val sysName: TextView = itemView.findViewById(R.id.sysSetText)
        val perName: TextView = itemView.findViewById(R.id.perCenterText)
        val isNext: ImageView = itemView.findViewById(R.id.systemSettingNext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.setting_system, parent, false)

        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val item = settings[position]
            MyApplication.showToast("${item.textSys} Clicked!")
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = settings[position]
        holder.sysName.text = item.textSys
        holder.perName.text = item.textPer
        if (item.isNext){
            holder.isNext.visibility = View.VISIBLE
        }else{
            holder.isNext.visibility = View.INVISIBLE
        }

        //点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int = settings.size
}