package com.projectlite2.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.projectlite2.android.R
import com.projectlite2.android.model.ProjectCard
import com.projectlite2.android.utils.IKotlinItemClickListener
import java.util.ArrayList


class ProjectCardAdapter(private val projects: List<ProjectCard>) :
        RecyclerView.Adapter<ProjectCardAdapter.ViewHolder>() {

    //继承点击事件接口
    private var itemClickListener: IKotlinItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //卡片背景
        val cardBg: CardView = itemView.findViewById(R.id.cardBackground)
        val pjName: TextView = itemView.findViewById(R.id.txtSettingItemName)
        val pjProgress:ProgressBar=itemView.findViewById(R.id.progressProject)
        val isLeader:TextView= itemView.findViewById(R.id.txtLeader)
        val dotMsgCount: ImageView = itemView.findViewById(R.id.dotMsgCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_card_item, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]
        holder.pjName.text = project.name
        holder.pjProgress.progress = project.progress;
        if (project.isLeader){
            holder.isLeader.visibility = View.VISIBLE
        }else{
            holder.isLeader.visibility = View.INVISIBLE
        }

        // 点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }

    }

    override fun getItemCount(): Int = projects.size

    // 提供点击事件set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }


}